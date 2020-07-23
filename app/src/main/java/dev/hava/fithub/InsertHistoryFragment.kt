package dev.hava.fithub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.hava.fithub.api.DefaultCallback
import dev.hava.fithub.api.Instance
import kotlinx.android.synthetic.main.fragment_insert_history.view.*

class InsertHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_insert_history, container, false)

        view.ok.setOnClickListener {
            Instance.insertHistory(
                requireContext(),
                view.type.selectedItemId.toInt(),
                view.details.text.toString(),
                view.value.text.toString(),
                DefaultCallback(requireContext(), {
                    toast("با موفقیت انجام شد.")
//                    findNavController().
//                    finish()
                }, {
                    toast("خطایی رخ داد.")
                })
            )
        }
        return view
    }
}