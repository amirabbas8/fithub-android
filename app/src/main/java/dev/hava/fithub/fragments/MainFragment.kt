package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.hava.fithub.BaseAdapter
import dev.hava.fithub.models.HistoryModel
import dev.hava.fithub.R
import dev.hava.fithub.api.Instance
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.item_history.view.*

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
        view.history.adapter =
            BaseAdapter(
                listOf(HistoryModel(1, 1, "hi", "hi")),
                R.layout.item_history
            ) { model, itemView ->
                itemView.historyType.text = model.historyType.toString()
                itemView.historyDetail.text = model.details
                itemView.historyValue.text = model.value
                itemView.setOnClickListener {
                }
            }
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