package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.hava.fithub.BaseAdapter
import dev.hava.fithub.R
import dev.hava.fithub.models.CourseModel
import kotlinx.android.synthetic.main.fragment_store.view.*
import kotlinx.android.synthetic.main.item_store.view.*

class StoreFragment : Fragment() {
    private val args: StoreFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store, container, false)

        view.courses.adapter =
            BaseAdapter(
                listOf(CourseModel(1, "hi", 1000)),
                R.layout.item_store
            ) { model, itemView ->
                itemView.courseName.text = model.name
                itemView.coursePrice.text = model.price.toString()
                itemView.setOnClickListener {
                    val action =
                        if (true) StoreFragmentDirections.actionStoreFragmentToCourseFragment(model)
                        else StoreFragmentDirections.actionStoreFragmentToBuyCourseFragment(model)
                    findNavController().navigate(action)
                }
            }

        view.addCourse.visibility = if (args.isStore) View.VISIBLE else View.GONE
        view.addCourse.setOnClickListener {
            val action =
                StoreFragmentDirections.actionStoreFragmentToAddCourseFragment()
            findNavController().navigate(action)
        }
        return view
    }
}