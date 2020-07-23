package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.hava.fithub.R
import kotlinx.android.synthetic.main.fragment_store.view.*

class StoreFragment : Fragment() {
    private val args: StoreFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store, container, false)
        view.addCourse.visibility = if (args.isStore) View.VISIBLE else View.GONE
        view.addCourse.setOnClickListener {
            val action =
                StoreFragmentDirections.actionStoreFragmentToAddCourseFragment()
            findNavController().navigate(action)
        }
        return view
    }
}