package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.hava.fithub.R
import dev.hava.fithub.api.DefaultCallback
import dev.hava.fithub.api.Instance
import dev.hava.fithub.toast
import kotlinx.android.synthetic.main.fragment_add_course.view.*

class AddCourseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_course, container, false)
        view.ok.setOnClickListener {
            Instance.addCourse(
                requireContext(),
                view.name.text.toString(),
                view.price.text.toString().toInt(),
                DefaultCallback(requireContext(), {
                    toast("با موفقیت انجام شد.")
                    findNavController().popBackStack()
                }, {
                    toast("خطایی رخ داد.")
                })
            )
        }
        return view
    }
}