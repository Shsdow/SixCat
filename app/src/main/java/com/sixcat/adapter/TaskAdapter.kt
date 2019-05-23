package com.sixcat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sixcat.R
import com.sixcat.model.bean.Task
import com.sixcat.utils.LogUtil
import kotlinx.android.synthetic.main.item_task_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 */
class TaskAdapter(private val context: Context, private val list: ArrayList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskAdapterViewHolder>() {

    var completeCount = 0
    private lateinit var clickListener: OnTaskClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapterViewHolder =
            TaskAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_task_layout, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TaskAdapterViewHolder, position: Int) {
        list[position].apply {
            holder.checkBox.isChecked = complete
            if (!complete) completeCount++
            holder.title.text = title
            holder.content.text = content
            holder.taskId.text = "id:$id"
            holder.taskDate.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(date)
            holder.cardView.setOnLongClickListener {
                clickListener.longClick(id)
                true
            }
            holder.cardView.setOnClickListener {
                clickListener.editClick(id, position)
            }
            holder.checkBox.setOnClickListener {
                clickListener.checkoutClick(id, holder.checkBox.isChecked)
            }
        }
    }


    fun newData(newList: ArrayList<Task>) {
        completeCount = 0
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class TaskAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox = itemView.checkbox
        val title = itemView.title
        val content = itemView.content
        val cardView = itemView.cardView
        val taskId = itemView.taskId
        val taskDate = itemView.taskDate
    }

    fun setTaskClickListener(listener: OnTaskClickListener) {
        this.clickListener = listener
    }

    interface OnTaskClickListener {
        fun editClick(id: Int, position: Int)
        fun longClick(id: Int)
        fun checkoutClick(id: Int, boolean: Boolean)
    }
}