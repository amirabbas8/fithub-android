package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.hava.fithub.R
import dev.hava.fithub.api.DefaultCallback
import dev.hava.fithub.api.Instance
import dev.hava.fithub.toast
import kotlinx.android.synthetic.main.fragment_course_settings.view.*

class CourseSettingsFragment : Fragment() {

    private val args: CourseSettingsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course_settings, container, false)
        view.courseName.text = args.course.name
        view.addPost.setOnClickListener {
            val action =
                CourseSettingsFragmentDirections.actionCourseSettingsFragmentToAddPostFragment(
                    null,
                    args.course
                )
            findNavController().navigate(action)
        }
        view.leaveCourse.setOnClickListener {
            Instance.leftCourse(
                requireContext(),
                args.course.courseStudentId,
                DefaultCallback(requireContext(), {
                    toast("با موفقیت انجام شد.")
                    findNavController().popBackStack()
                    findNavController().popBackStack()
                }, {
                    toast("خطایی رخ داد.")
                })
            )
        }
        return view
    }
}