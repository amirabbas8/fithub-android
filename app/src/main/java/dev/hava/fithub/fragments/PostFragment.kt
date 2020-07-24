package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dev.hava.fithub.R
import kotlinx.android.synthetic.main.fragment_post.view.*

class PostFragment : Fragment() {

    private val args: PostFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        view.text.text = args.post.text
        return view
    }
}