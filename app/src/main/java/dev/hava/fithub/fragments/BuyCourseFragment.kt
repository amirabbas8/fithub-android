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
import kotlinx.android.synthetic.main.fragment_buy_course.view.*

class BuyCourseFragment : Fragment() {

    private val args: BuyCourseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buy_course, container, false)
        view.courseName.text = args.course.name
        view.coursePrice.text = args.course.price.toString()
        view.buy.setOnClickListener {
            Instance.enrollCourse(
                requireContext(),
                args.course.courseId,
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