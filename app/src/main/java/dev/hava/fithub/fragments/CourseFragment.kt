package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import dev.hava.fithub.BaseAdapter
import dev.hava.fithub.R
import dev.hava.fithub.api.DefaultCallback
import dev.hava.fithub.api.Instance
import dev.hava.fithub.models.PostModel
import dev.hava.fithub.toast
import kotlinx.android.synthetic.main.fragment_course.*
import kotlinx.android.synthetic.main.fragment_course.view.*
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*

class CourseFragment : Fragment() {

    private val args: CourseFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course, container, false)
        refresh(view.posts)
        return view
    }

    private fun refresh(rv: RecyclerView) {
        Instance.getPosts(
            requireContext(),
            args.course.courseId,
            DefaultCallback(requireContext(), {
                val items = ArrayList<PostModel>()
                it.body()?.posts?.let { posts ->
                    for (post in posts) {
                        val item = post.asJsonObject
                        val fields = item.get("fields").asJsonObject
                        items.add(
                            PostModel(
                                item.get("pk").asInt,
                                fields.get("text").asString,
                                fields.get("order").asInt
                            )
                        )
                    }
                }
                rv.adapter =
                    BaseAdapter(
                        items,
                        R.layout.item_post
                    ) { model, itemView ->
                        itemView.text.text = model.text
                        itemView.setOnClickListener {
                            val action =
                                CourseFragmentDirections.actionCourseFragmentToPostFragment(model)
                            findNavController().navigate(action)
                        }
                    }
            }, {
                toast("خطایی رخ داد.")
            })
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.refresh_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> {
                refresh(posts)
                true
            }
            R.id.settings -> {
                val action =
                    CourseFragmentDirections.actionCourseFragmentToCourseSettingsFragment(args.course)
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}