package dev.hava.fithub.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.hava.fithub.BaseAdapter
import dev.hava.fithub.R
import dev.hava.fithub.api.Auth
import dev.hava.fithub.api.DefaultCallback
import dev.hava.fithub.api.Instance
import dev.hava.fithub.models.HistoryModel
import dev.hava.fithub.toast
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.item_history.view.*

class MainFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (Instance.getUserId(requireContext()) ?: 0 == 0) {
            val action =
                MainFragmentDirections.actionMainFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        view.insertHistory.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToInsertHistoryFragment()
            findNavController().navigate(action)
        }
        view.myCourses.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToStoreFragment(
                    false
                )
            findNavController().navigate(action)
        }
        view.store.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToStoreFragment(
                    true
                )
            findNavController().navigate(action)
        }
        refresh(view.history)
        return view
    }

    private fun refresh(rv: RecyclerView) {
        Instance.getHistory(requireContext(), DefaultCallback(requireContext(), {
            val items = ArrayList<HistoryModel>()
            it.body()?.histories?.let { histories ->
                for (history in histories) {
                    val item = history.asJsonObject
                    val fields = item.get("fields").asJsonObject
                    items.add(
                        HistoryModel(
                            item.get("pk").asInt,
                            fields.get("history_type").asString.toInt(),
                            fields.get("details").asString,
                            fields.get("value").asString
                        )
                    )
                }
            }
            rv.adapter =
                BaseAdapter(
                    items,
                    R.layout.item_history
                ) { model, itemView ->
                    itemView.historyType.text =
                        resources.getStringArray(R.array.history_types_array)[model.historyType]
                    itemView.historyDetail.text = model.details
                    itemView.historyValue.text = model.value
                    itemView.historyTypeIV.setImageResource(
                        when (model.historyType) {
                            0 -> {
                                R.drawable.ic_baseline_category_24
                            }
                            1 -> {
                                R.drawable.ic_baseline_healing_24
                            }
                            2 -> {
                                R.drawable.ic_baseline_height_24
                            }
                            3 -> {
                                R.drawable.ic_baseline_face_24
                            }
                            4 -> {
                                R.drawable.ic_baseline_fastfood_24
                            }
                            5 -> {
                                R.drawable.ic_baseline_airline_seat_individual_suite_24
                            }
                            6 -> {
                                R.drawable.ic_baseline_emoji_food_beverage_24
                            }
                            7 -> {
                                R.drawable.ic_baseline_emoji_food_beverage_24
                            }
                            8 -> {
                                R.drawable.ic_baseline_emoji_food_beverage_24
                            }
                            9 -> {
                                R.drawable.ic_baseline_directions_walk_24
                            }
                            10 -> {
                                R.drawable.ic_baseline_directions_bike_24
                            }
                            11 -> {
                                R.drawable.ic_baseline_waves_24
                            }
                            12 -> {
                                R.drawable.ic_baseline_image_24
                            }
                            13 -> {
                                R.drawable.ic_baseline_directions_walk_24
                            }
                            14 -> {
                                R.drawable.ic_baseline_directions_walk_24
                            }
                            else -> {
                                R.drawable.ic_baseline_category_24
                            }
                        }
                    )
                    itemView.setOnClickListener {
                    }
                }
        }, {
            toast("خطایی رخ داد.")
        }))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> {
                refresh(history)
                true
            }
            R.id.logout -> {
                Auth.logout(requireContext())
                val action =
                    MainFragmentDirections.actionMainFragmentToLoginFragment()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}