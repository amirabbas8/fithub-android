package dev.hava.fithub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView


class BaseAdapter<T>(
    private val items: List<T>,
    @LayoutRes private val resId: Int,
    val listener: ((model: T, view: View) -> Unit)
) :
    RecyclerView.Adapter<BaseAdapter<T>.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                resId,
                parent,
                false
            )
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: T) {
            listener.invoke(model, view)
        }
    }
}