package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.hava.fithub.BaseAdapter
import dev.hava.fithub.models.PostModel
import dev.hava.fithub.R
import kotlinx.android.synthetic.main.fragment_course.view.*
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*

class CourseFragment : Fragment() {

    private val args: CourseFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course, container, false)
        view.posts.adapter =
            BaseAdapter(
                listOf(
                    PostModel(
                        1,
                        "hi",
                        Date().time.toInt()
                    )
                ),
                R.layout.item_post
            ) { model, itemView ->
                itemView.text.text = model.text
                itemView.setOnClickListener {
                    val action =
                        CourseFragmentDirections.actionCourseFragmentToPostFragment(model)
                    findNavController().navigate(action)
                }
            }
        view.settings.setOnClickListener {
            val action =
                CourseFragmentDirections.actionCourseFragmentToCourseSettingsFragment(args.course)
            findNavController().navigate(action)
        }
        return view
    }
}