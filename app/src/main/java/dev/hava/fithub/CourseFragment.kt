package dev.hava.fithub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_course.view.*

class CourseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course, container, false)
        view.settings.setOnClickListener {
            val action = CourseFragmentDirections.actionCourseFragmentToCourseSettingsFragment()
            findNavController().navigate(action)
        }
        return view
    }
}