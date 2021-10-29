package com.albasil.todolist.Fragment

//                        Log.e("checked",priority)
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albasil.todolist.DB.AppRepo
import com.albasil.todolist.DB.DataTask
import com.albasil.todolist.R
import com.albasil.todolist.RecyclerAdapter
import com.albasil.todolist.TaskVM
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }


    // Date
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatted = current.format(formatter)


    //due date
    val c = Calendar.getInstance()
    val day = c.get(Calendar.DAY_OF_MONTH)
    val month = c.get(Calendar.MONTH)
    val year = c.get(Calendar.YEAR)


    private lateinit var dueDate: String

    private lateinit var linearLayoutBtnTask: LinearLayout
    private lateinit var ed_taksTitle: EditText
    private lateinit var ed_taskDescription: EditText
    private lateinit var calendarTask: TextView


    //Insert to list
    private lateinit var insertTask: DataTask

    private lateinit var _taskTitle: String
    private lateinit var _taskDecriotion: String
    private lateinit var _creationTask: String
    private lateinit var _dueDateTask: String


    private lateinit var rv_recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel = ViewModelProvider(this).get(TaskVM::class.java)

        linearLayoutBtnTask = view.findViewById(R.id.linearLayoutBtnTask)

        mainViewModel.fillDB()

        rv_recyclerView = view.findViewById(R.id.rv_recyclerView)



        mainViewModel.getAllTasks().observe(viewLifecycleOwner, {
            rv_recyclerView.adapter = RecyclerAdapter(it,mainViewModel)
        })



        rv_recyclerView.layoutManager = LinearLayoutManager(view.context)

        linearLayoutBtnTask.setOnClickListener {


            addTaskDailog(mainViewModel)

            mainViewModel.getAllTasks().observe(viewLifecycleOwner, {
                rv_recyclerView.adapter = RecyclerAdapter(it,mainViewModel)
            })

            //update list after add
            // rv_recyclerView.adapter = RecyclerAdapter(mainViewModel.getAllTaskFromList())

        }


        //show all data
        //   mainViewModel.getAllTaskFromList()
        // Log.e("checked", "1 ${mainViewModel.getAllTaskFromList()[0].titleTask}")


    }


    //function to insert to database
    fun insertDateToDatabase(mainViewModel: TaskVM, _taskTitle: String, _taskDecriotion: String) {
        //  val _taskTitle=ed_taksTitle.text.toString()

        // _taskTitle=ed_taksTitle.text.toString()
        // _taskDecriotion=ed_taskDescription.text.toString()
        _creationTask = formatted.toString()
        _dueDateTask = dueDate.toString()

        if (inputCheck(_taskTitle, _dueDateTask)) {

            val task = DataTask(
                titleTask = "$_taskTitle",
                descTask = "$_taskDecriotion",
                creation_date = "$_creationTask",
                due_date = "$_dueDateTask",
                ifCheck = false
            )

            mainViewModel.addTask(task)
            Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()

        }
    }

    fun inputCheck(_taskTitle: String, _dueDateTask: String): Boolean {

        return !(TextUtils.isEmpty(_taskTitle) && TextUtils.isEmpty(_dueDateTask))
    }


    fun addTaskDailog(mainViewModel: TaskVM) {
        //Inflate the dialog with custom view
        val addTask = android.app.AlertDialog.Builder(view?.context)

        val myView: View = layoutInflater.inflate(R.layout.add_tasks, null)

        addTask.setView(myView)
        addTask.setTitle("Add Task")

        val Save: Button = myView.findViewById(R.id.btnSave)
        val cancel: Button = myView.findViewById(R.id.btnCancel)


        val dateAlerAddTask: TextView = myView.findViewById(R.id.tvDateToday)
        dateAlerAddTask.setText("Date  $formatted")

        var count = AppRepo.nextIdList
        ed_taksTitle = myView.findViewById(R.id.edtTitleTask)
        ed_taskDescription = myView.findViewById(R.id.edtDescription)
        calendarTask = myView.findViewById(R.id.id_calendar)


        calendarTask.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireView().context,
                DatePickerDialog.OnDateSetListener { view, y, m, d ->
                    dueDate = "$y/${m + 1}/$d"
                    calendarTask.setText(dueDate)
                },
                year,
                month,
                day
            )
            datePickerDialog.datePicker.minDate = c.timeInMillis
            datePickerDialog.show()

        }




        Save.setOnClickListener {

            if (ed_taksTitle.text.isNotEmpty()
                && calendarTask.text.isNotEmpty()
            ) {
                //delete...
                Toast.makeText(
                    context,
                    " Title task : ${ed_taksTitle.text}" + " \n Description ${ed_taskDescription.text} \n" + " $formatted \n Due Date $dueDate",
                    Toast.LENGTH_SHORT
                ).show()

                //Insert to list var insertTask
                insertTask = DataTask(
                    count,
                    "${ed_taksTitle.text}",
                    "${ed_taskDescription.text}",
                    "$formatted",
                    "$dueDate",
                    false
                )

                //mainViewModel.insertTask(insertTask)


                //to insert to database
                insertDateToDatabase(
                    mainViewModel,
                    ed_taksTitle.text.toString(),
                    ed_taskDescription.text.toString()
                )



                mainViewModel.getAllTasks()




                //رتب الكود
                mainViewModel.getAllTasks().observe(viewLifecycleOwner,  {
                    rv_recyclerView.adapter=RecyclerAdapter(it,mainViewModel)})
                //search about notifyDataSetChanged
                rv_recyclerView.adapter?.notifyDataSetChanged()

                //fun Clear
                clearEditText()
                count++
            } else {
                Toast.makeText(context, " Please Complete  ", Toast.LENGTH_SHORT).show()
            }







        }

        addTask.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        addTask.show()//.window?.setBackgroundDrawableResource(R.drawable.ic_launcher_foreground)

    }

    private fun clearEditText() {
        ed_taksTitle.setText(null)
        ed_taskDescription.setText(null)

        calendarTask.setText(null)

    }


}