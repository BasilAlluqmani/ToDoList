package com.albasil.todolist.Fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.albasil.todolist.DB.DataTask
import com.albasil.todolist.R
import kotlinx.android.synthetic.main.information_task_fragment.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class InformationTaskFragment : Fragment() {

    companion object {
        fun newInstance() = InformationTaskFragment()
    }



    //due date
    val c = Calendar.getInstance()
    val day = c.get(Calendar.DAY_OF_MONTH)
    val month = c.get(Calendar.MONTH)
    val year = c.get(Calendar.YEAR)

    // Date
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatted = current.format(formatter)

    //--------------------------------------------------------------------


    private lateinit var viewModel: InformationTaskViewModel

    private lateinit var taskTitleInfo: EditText
    private lateinit var taskDescriptionInfo: EditText

    private lateinit var taskCreationTV: TextView
    private lateinit var taskDueDateTV: TextView


    private lateinit var deleteTaskButton: ImageButton
    private lateinit var updateTaskButton: ImageButton

    private lateinit var upDate_DueDate: ImageButton




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.information_task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // viewModel
        viewModel = ViewModelProvider(this).get(InformationTaskViewModel::class.java)

        //to pass data
        val args = arguments
        var inputTask = args?.getParcelable<DataTask>("taskKey")


        taskTitleInfo = view.findViewById(R.id.edTaskTitle)
        taskDescriptionInfo = view.findViewById(R.id.edTaskDescription)
        taskCreationTV = view.findViewById(R.id.tvCreationDate)
        taskDueDateTV = view.findViewById(R.id.tvDueDate)
        deleteTaskButton = view.findViewById(R.id.btnDeleteTask)
        updateTaskButton = view.findViewById(R.id.btnEditTask)
        upDate_DueDate=view.findViewById(R.id.btnUpDateDueDate)


        taskDescriptionInfo = view.findViewById(R.id.edTaskDescription)


        taskTitleInfo.setText(inputTask?.titleTask)
        taskDescriptionInfo.setText(inputTask?.descTask)
        taskCreationTV.setText(inputTask?.creation_date)
        taskDueDateTV.setText(inputTask?.due_date)

        var dueDate =inputTask?.due_date

        if (formatted>=dueDate.toString()){
            dueDate
            formatted
            taskDueDateTV.text
            Toast.makeText(context,"its working",Toast.LENGTH_SHORT).show()
        }

        upDate_DueDate.setOnClickListener{

    calendar()

        }

        deleteTaskButton.setOnClickListener {

            if (inputTask != null) {
                deletButton(inputTask)
            }

        }

        updateTaskButton.setOnClickListener {
            if (taskTitleInfo.text.isNotEmpty()){
              inputTask?.let { upDateTask ->

                  inputTask.titleTask=taskTitleInfo.text.toString()
                  inputTask.due_date=taskDueDateTV.text.toString()

                  inputTask.descTask=taskDescriptionInfo.text.toString()

                  viewModel.updateTask(inputTask)


                    activity?.apply {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, MainFragment.newInstance())
                            .addToBackStack("back")
                            .commit()
                    }

                }
            }else{

                Toast.makeText(view.context,"الرجاء عدم ترك الحقل فارغ",Toast.LENGTH_SHORT).show()

            }



        }






    }


    fun deletButton(deleteTask:DataTask) {
        AlertDialog.Builder(context)
            .setTitle("Delete")
            .setIcon(R.drawable.ic_baseline_delete_24)
            .setMessage("Do want delete this task?")
            .setPositiveButton("Yes"){
                    dialog,_ ->

                deleteTask?.let { it->

                    viewModel.deleteTask(deleteTask)

                    Toast.makeText(context,"Deleted ${taskTitleInfo.text}",Toast.LENGTH_SHORT).show()


                    activity?.apply {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, MainFragment.newInstance())
                            .addToBackStack("back")
                            .commit()
                    }



                }

                dialog.dismiss()
            }
            .setNegativeButton("No"){
                    dialog,_ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }


    private fun calendar() {
        val datePickerDialog = DatePickerDialog(
            requireView().context,
            DatePickerDialog.OnDateSetListener { view, y, m, d ->
                // dueDate = "$y/${m + 1}/$d"
                tvDueDate.setText("$y/${m + 1}/$d")
            }, year, month, day)
        datePickerDialog.datePicker.minDate = c.timeInMillis
        datePickerDialog.show()
    }

}

