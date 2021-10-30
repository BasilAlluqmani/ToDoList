package com.albasil.todolist.Fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
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


    private lateinit var deleteTaskButton: ImageView
    private lateinit var updateTaskButton: ImageView

    private lateinit var upDate_DueDate: ImageView
    private lateinit var linearLayout_duedete: LinearLayout


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
        upDate_DueDate = view.findViewById(R.id.btnUpDateDueDate)
        taskDescriptionInfo = view.findViewById(R.id.edTaskDescription)

        linearLayout_duedete = view.findViewById(R.id.linearLayout_duedete)



        taskTitleInfo.setBackgroundColor(Color.WHITE)
        taskDescriptionInfo.setBackgroundColor(Color.WHITE)
        taskDueDateTV.setBackgroundColor(Color.WHITE)
        taskCreationTV.setBackgroundColor(Color.WHITE)
        linearLayout_duedete.setBackgroundColor(Color.WHITE)


        taskTitleInfo.setText(inputTask?.titleTask)
        taskDescriptionInfo.setText(inputTask?.descTask)
        taskCreationTV.setText(inputTask?.creation_date)
        taskDueDateTV.setText(inputTask?.due_date)


        if (inputTask!!.due_date < formatted) {



            disableEditText()
        }


        upDate_DueDate.setOnClickListener {

            calendar()

        }

        deleteTaskButton.setOnClickListener {

            if (inputTask != null) {
                deletTask(inputTask)
            }

        }

        updateTaskButton.setOnClickListener {

            inputTask?.let { it1 -> updateTask(it1) }
        }


    }

    private fun updateTask(inputTask: DataTask) {


        if (taskTitleInfo.text.isNotEmpty()) {
            inputTask.let { upDateTask ->

                inputTask.titleTask = taskTitleInfo.text.toString()
                inputTask.due_date = taskDueDateTV.text.toString()
                inputTask.descTask = taskDescriptionInfo.text.toString()

                viewModel.updateTask(inputTask)


                activity?.apply {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MainFragment.newInstance())
                        .addToBackStack("back")
                        .commit()
                }

            }
        } else {

            Toast.makeText(context, getString(R.string.Please_enter_task_title), Toast.LENGTH_SHORT)
                .show()

        }

    }


    private fun deletTask(deleteTask: DataTask) = AlertDialog.Builder(context)
        .setTitle(getString(R.string.delete))
        .setIcon(R.drawable.ic_baseline_delete_24)
        .setMessage(getString(R.string.Do_want_delete_this_task))
        .setPositiveButton(getString(R.string.yes)) { dialog, _ ->

            deleteTask.let { it ->

                viewModel.deleteTask(deleteTask)

                Toast.makeText(
                    context,
                    "${getString(R.string.delete)} ${taskTitleInfo.text}",
                    Toast.LENGTH_SHORT
                ).show()


                activity?.apply {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MainFragment.newInstance())
                        .addToBackStack("back")
                        .commit()
                }


            }

            dialog.dismiss()
        }
        .setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }.create().show()


    private fun calendar() {
        val datePickerDialog = DatePickerDialog(
            requireView().context,
            DatePickerDialog.OnDateSetListener { view, y, m, d ->
                tvDueDate.setText("$y-${m + 1}-$d")
            }, year, month, day
        )
        datePickerDialog.datePicker.minDate = c.timeInMillis
        datePickerDialog.show()
    }




    fun disableEditText() {
        //fun
        updateTaskButton.isEnabled = false
        taskTitleInfo.isEnabled = false
        taskDescriptionInfo.isEnabled = false
        upDate_DueDate.isEnabled = false

        taskTitleInfo.setBackgroundColor(Color.GRAY)
        taskDescriptionInfo.setBackgroundColor(Color.GRAY)
        taskDueDateTV.setBackgroundColor(Color.GRAY)
        taskCreationTV.setBackgroundColor(Color.GRAY)
        linearLayout_duedete.setBackgroundColor(Color.GRAY)

        AlertDialog.Builder(view?.context)
            .setTitle(getString(R.string.Sorry))

            .setIcon(R.drawable.ic_baseline_back_hand_24)
            .setMessage(getString(R.string.message))
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->

                dialog.dismiss()
            }.create().show()
    }

}

