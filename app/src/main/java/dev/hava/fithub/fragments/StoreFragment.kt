package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.*
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
import kotlinx.android.synthetic.main.fragment_store.*
import kotlinx.android.synthetic.main.fragment_store.view.*
import kotlinx.android.synthetic.main.item_store.view.*

class StoreFragment : Fragment() {
    private val args: StoreFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
                    items.add(
                        CourseModel(
                            item.get("course_id").asInt,
                            item.get("name").asString,
                            item.get("price").asInt,
                            item.get("course_student_id")?.asInt ?: 0
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.refresh_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> {
                refresh(courses)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}