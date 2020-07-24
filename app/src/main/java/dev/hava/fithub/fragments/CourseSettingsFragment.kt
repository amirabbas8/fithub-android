package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.hava.fithub.R
import kotlinx.android.synthetic.main.fragment_course_settings.view.*

class CourseSettingsFragment : Fragment() {

    private val args: CourseSettingsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course_settings, container, false)
        view.addPost.setOnClickListener {
            val action =
                CourseSettingsFragmentDirections.actionCourseSettingsFragmentToAddPostFragment(
                    null,
                    args.course
                )
            findNavController().navigate(action)
        }
        return view
    }
}