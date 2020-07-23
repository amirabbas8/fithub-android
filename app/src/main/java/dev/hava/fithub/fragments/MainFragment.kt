package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.hava.fithub.R
import dev.hava.fithub.api.Instance
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Instance.getUserId(requireContext()) ?: 0 == 0) {
            val action =
                MainFragmentDirections.actionMainFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.insertHistory.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToInsertHistoryFragment()
            findNavController().navigate(action)
        }
        view.myCourses.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToStoreFragment(
                    false
                )
            findNavController().navigate(action)
        }
        view.store.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToStoreFragment(
                    true
                )
            findNavController().navigate(action)
        }

        return view
    }
}