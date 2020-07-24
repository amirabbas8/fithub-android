package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import dev.hava.fithub.BaseAdapter
import dev.hava.fithub.R
import dev.hava.fithub.api.CoursesRes
import dev.hava.fithub.api.DefaultCallback
import dev.hava.fithub.api.Instance
import dev.hava.fithub.models.CourseModel
import dev.hava.fithub.toast
import kotlinx.android.synthetic.main.fragment_store.view.*
import kotlinx.android.synthetic.main.item_store.view.*

class StoreFragment : Fragment() {
    private val args: StoreFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store, container, false)

        refresh(view.courses)

        view.addCourse.visibility =
            if (args.isStore && Instance.isCoach(requireContext())) View.VISIBLE else View.GONE
        view.addCourse.setOnClickListener {
            val action =
                StoreFragmentDirections.actionStoreFragmentToAddCourseFragment()
            findNavController().navigate(action)
        }
        return view
    }

    private fun refresh(rv: RecyclerView) {
        val callback = DefaultCallback<CoursesRes>(requireContext(), {
            val items = ArrayList<CourseModel>()
            it.body()?.courses?.let { courses ->
                for (course in courses) {
                    val item = course.asJsonObject
                    val fields = item.get("fields").asJsonObject
                    items.add(
                        CourseModel(
                            item.get("pk").asInt,
                            fields.get("name").asString,
                            fields.get("price").asInt
                        )
                    )
                }
            }
            rv.adapter =
                BaseAdapter(
                    items,
                    R.layout.item_store
                ) { model, itemView ->
                    itemView.courseName.text = model.name
                    itemView.coursePrice.text = model.price.toString()
                    itemView.setOnClickListener {
                        val action =
                            if (args.isStore && !Instance.isCoach(requireContext()))
                                StoreFragmentDirections.actionStoreFragmentToBuyCourseFragment(model)
                            else StoreFragmentDirections.actionStoreFragmentToCourseFragment(model)

                        findNavController().navigate(action)
                    }
                }
        }, {
            toast("خطایی رخ داد.")
        })
        if (args.isStore)
            Instance.getCourses(requireContext(), callback)
        else
            Instance.getMyCourses(requireContext(), callback)
    }
}