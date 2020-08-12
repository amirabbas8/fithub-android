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
import kotlinx.android.synthetic.main.fragment_add_post.view.*
import java.util.*
import kotlin.math.abs

class AddPostFragment : Fragment() {

    private val args: AddPostFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_post, container, false)
        view.ok.setOnClickListener {
            args.post?.let {
                //todo edit post
            } ?: args.course?.courseId?.let { courseId ->
                Instance.addPost(
                    requireContext(),
                    courseId,
                    view.text.text.toString(),
                    abs(Date().time.toInt()),
                    DefaultCallback(requireContext(), {
                        toast("با موفقیت انجام شد.")
                        findNavController().popBackStack()
                    }, {
                        toast("خطایی رخ داد.")
                    })
                )
            }
        }
        return view
    }
}