package com.albasil.todolist.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.albasil.todolist.DB.DataTask
import com.albasil.todolist.InformationTaskViewModel
import com.albasil.todolist.R

class InformationTaskFragment : Fragment() {

    companion object {
        fun newInstance() = InformationTaskFragment()
    }

    private lateinit var viewModel: InformationTaskViewModel

    private lateinit var taskTitleInfo: EditText
    private lateinit var taskDescriptionInfo: EditText

    private lateinit var taskCreationTV: TextView
    private lateinit var taskDueDateTV: TextView


    private lateinit var deleteTaskButton: Button
    private lateinit var editTaskButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.information_task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(InformationTaskViewModel::class.java)

        taskTitleInfo = view.findViewById(R.id.edTaskTitle)
        taskDescriptionInfo = view.findViewById(R.id.edTaskDescription)
        taskCreationTV = view.findViewById(R.id.tvCreationDate)
        taskDueDateTV = view.findViewById(R.id.tvDueDate)
        deleteTaskButton = view.findViewById(R.id.btnDeleteTask)
        editTaskButton = view.findViewById(R.id.btnEditTask)

        taskDescriptionInfo = view.findViewById(R.id.edTaskDescription)

        val args = arguments


        val inputTask = args?.getParcelable<DataTask>("taskKey")



        taskTitleInfo.setText(inputTask?.titleTask)
        taskDescriptionInfo.setText(inputTask?.descTask)
        taskCreationTV.setText(inputTask?.creation_date)
        taskDueDateTV.setText(inputTask?.due_date)



        deleteTaskButton.setOnClickListener {

            inputTask?.let { it->
                viewModel.deleteTask(it.idTask)


                activity?.apply {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MainFragment.newInstance())
                        .addToBackStack("back")
                        .commit()
                }



            }
        }

        editTaskButton.setOnClickListener {
            Toast.makeText(view.context,"Before",Toast.LENGTH_SHORT).show()
            inputTask?.let { dataTask ->
                viewModel.editTask(
                    dataTask.idTask,
                    "${taskTitleInfo.text}",
                    "${taskDescriptionInfo.text}",
                     dataTask.ifCheck

                )

                activity?.apply {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MainFragment.newInstance())
                        .addToBackStack("back")
                        .commit()
                }

            }


        }

    }
}

