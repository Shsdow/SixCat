package com.sixcat.views.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sixcat.R
import com.sixcat.adapter.TaskAdapter
import com.sixcat.base.BaseRxLazyFragment
import com.sixcat.jetpack.viewmodel.TaskViewModel
import com.sixcat.model.bean.Task
import com.sixcat.utils.obtainViewModel
import com.sixcat.utils.showEditDialog
import kotlinx.android.synthetic.main.fragment_todo_list.*

/**
 * @author liguoying
 * @date 2017/12/11.
 */

class TodoListFragment : BaseRxLazyFragment() {

    private lateinit var taskViewModel: TaskViewModel
    private var list: List<Task>? = emptyList()
    private var title: String? = null
    private var content: String? = null

    private val taskAdapter by lazy {
        TaskAdapter(context!!, list!!)
    }

    override fun getLayoutResId() =
            R.layout.fragment_todo_list


    override fun initView() {
        initViewModel()
        initAdapter()
        addTask.setOnClickListener {
            //            taskViewModel.insertTaskToDatabase(Task)
            showEditDialog(context!!, "title", "message", View.OnClickListener {
                val text = it.tag as String
                title = text.split("&")[0]
                content = text.split("&")[1]
            })
        }
    }

    private fun initAdapter() {

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        todoList.layoutManager = linearLayoutManager
        todoList.adapter = taskAdapter

    }

    private fun initViewModel() {
        taskViewModel = getTaskViewModel().apply {
            tasks.observe(this@TodoListFragment, Observer {
                list = it
                taskAdapter.notifyDataSetChanged()
            })
        }

    }

    override fun lazyLoad() {

    }


    private fun getTaskViewModel() = obtainViewModel(TaskViewModel::class.java)

    companion object {

        fun newInstance(): TodoListFragment {
            return TodoListFragment()
        }
    }
}
