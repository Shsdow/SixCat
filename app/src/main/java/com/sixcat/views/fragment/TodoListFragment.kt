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
import com.sixcat.utils.LogUtil
import com.sixcat.utils.obtainViewModel
import com.sixcat.utils.showDelete
import com.sixcat.utils.showEditDialog
import kotlinx.android.synthetic.main.fragment_todo_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * @author liguoying
 * @date 2017/12/11.
 */

class TodoListFragment : BaseRxLazyFragment() {

    private lateinit var taskViewModel: TaskViewModel
    private var arraylist: ArrayList<Task>? = arrayListOf()
    private var title: String? = null
    private var content: String? = null

    private val taskAdapter by lazy {
        TaskAdapter(context!!, arraylist!!)
    }

    override fun getLayoutResId() =
            R.layout.fragment_todo_list


    override fun initView() {
        initViewModel()
        initAdapter()
        addTask.setOnClickListener {
            showEditDialog(context!!, "title", "message", View.OnClickListener {
                val text = it.tag as StringBuilder
                title = text.split("&")[0]
                content = text.split("&")[1]
                LogUtil.e("$title  $content")
                taskViewModel.insertTaskToDatabase(Task(title!!, content!!, false, Date()))
            })
        }

    }

    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        todoList.layoutManager = linearLayoutManager
        todoList.adapter = taskAdapter
        taskAdapter.setTaskClickListener(object : TaskAdapter.OnTaskClickListener {
            override fun editClick(id: Int) {
                LogUtil.d("editClick $id")
            }

            /**
             * 删除指定 id 的 Task
             */
            override fun longClick(id: Int) {
                LogUtil.d("longClick $id")

                showDelete(context!!, View.OnClickListener { taskViewModel.deleteTaskWithId(id) })
            }

            override fun checkoutClick(id: Int, complete: Boolean) {
                GlobalScope.launch {
                    taskViewModel.updateTaskWithComplete(id, complete)
                }
            }

        })

    }

    private fun initViewModel() {
        taskViewModel = getTaskViewModel().apply {
            tasks.observe(this@TodoListFragment, Observer {
                LogUtil.d("num is ${it.size}")
                arraylist = it as ArrayList<Task>
                taskAdapter.newData(it)
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
