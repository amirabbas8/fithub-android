package dev.hava.fithub


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.hava.fithub.api.Auth
import dev.hava.fithub.api.DefaultCallback
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {
    private var isSignUp = true
    private lateinit var defaultCallback: DefaultCallback<Auth>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.change.setOnClickListener {
            isSignUp = !isSignUp
            view.change.setText(if (isSignUp) R.string.login else R.string.register)
            view.ok.setText(if (isSignUp) R.string.sign_up else R.string.sign_in)
            if (isSignUp) view.phone.visible() else phone.gone()
            if (isSignUp) view.email.visible() else email.gone()
            if (isSignUp) view.license.visible() else license.gone()
        }
        view.ok.setOnClickListener {
            view.loading.visible()
            if (!isSignUp) {
                login()
            } else {
                signUp()
            }

        }
        defaultCallback = DefaultCallback(requireContext(), {
            if (it.isSuccessful) {
                it.body()?.save(requireContext())
                val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                findNavController().navigate(action)
            }
        }, {
            error()
        })
        return view
    }


    private fun login() {
        Auth.login(
            username.text.toString(), password.text.toString(),
            defaultCallback
        )
    }

    private fun error() {
        loading.gone()
        toast("ERROR")
    }

    private fun signUp() {
        Auth.signUp(
            username.text.toString(),
            password.text.toString(),
            email.text.toString(),
            phone.text.toString(),
            license.text.toString(),
            defaultCallback
        )
    }
}