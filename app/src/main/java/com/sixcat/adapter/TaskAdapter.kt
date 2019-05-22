package com.sixcat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sixcat.R
import com.sixcat.model.bean.Task
import kotlinx.android.synthetic.main.item_task_layout.view.*

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 */
class TaskAdapter(private val context: Context, private val list: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapterViewHolder =
            TaskAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_task_layout, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TaskAdapterViewHolder, position: Int) {
        list[position].apply {
            holder.checkBox.isChecked = this.complete
            holder.title.text = this.title
            holder.content.text = this.content
        }
    }


    class TaskAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox = itemView.checkbox
        val title = itemView.title
        val content = itemView.content
    }
}