package com.albasil.todolist

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.albasil.todolist.DB.AppRepo
import com.albasil.todolist.DB.DataTask
import com.albasil.todolist.Fragment.InformationTaskFragment


class RecyclerAdapter(private var taskList: List<DataTask>) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item_layout, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val task = taskList[position]
        holder.taskTitle.text = task.titleTask
        holder.taskCreaiton.text = task.creation_date
        holder.taskDue.text =task.due_date

        //-----------------------------------------------------------------------------
        holder.clickCheckBox.setOnClickListener { clicked->


            if (holder.clickCheckBox.isChecked){
                task.ifCheck=true
                holder.itemIdXML.setBackgroundColor(Color.GRAY)
                AppRepo(holder.itemView.context).editTask(task.idTask,task.titleTask,task.descTask,true,task.due_date)

            }else{
                task.ifCheck=true
                holder.itemIdXML.setBackgroundColor(Color.WHITE)
                AppRepo(holder.itemView.context).editTask(task.idTask,task.titleTask,task.descTask,false,task.due_date)
            }
//---------------------------------------------------------------------------------------



        }
        holder.itemView.setOnClickListener { view->

            val activity:AppCompatActivity = view.context as AppCompatActivity
            val bundle = Bundle()

            val fragment = InformationTaskFragment.newInstance()
            fragment.arguments = bundle
            bundle.putParcelable("taskKey",task)
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack("Test")
                .commit()
        }


    }

    override fun getItemCount(): Int {
      return taskList.size
    }


}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val taskTitle : TextView =  itemView.findViewById(R.id.tv_title)
    val taskCreaiton : TextView =  itemView.findViewById(R.id.tv_creationDate)//اعدل
    val taskDue:TextView= itemView.findViewById(R.id.tv_dueDate)//اعدل

    var clickCheckBox : CheckBox=itemView.findViewById(R.id.checkBoxClick)

    var itemIdXML:ConstraintLayout= itemView.findViewById(R.id.itemId)


    init {
        itemView.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        Toast.makeText(itemView.context, "Task Title: ${taskTitle.text} ", Toast.LENGTH_SHORT).show()
    }
  }