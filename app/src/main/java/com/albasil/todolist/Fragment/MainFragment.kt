package com.albasil.todolist.Fragment

//                        Log.e("checked",priority)
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
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
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val formatted = current.format(formatter)


    //due date
    val c = Calendar.getInstance()
    val day = c.get(Calendar.DAY_OF_MONTH)
    val month = c.get(Calendar.MONTH)
    val year = c.get(Calendar.YEAR)


    private lateinit var priority: String
    private lateinit var dueDate: String

   private lateinit var addTask: ImageButton
    private lateinit var  ed_taksTitle: EditText
    private lateinit var ed_taskDescription: EditText
    private lateinit var calendarTask: TextView

    //Insert to list
   private lateinit var insertTask: DataTask


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

        addTask= view.findViewById(R.id.btnTask)

        val mainViewModel = ViewModelProvider(this).get(TaskVM::class.java)


        rv_recyclerView = view.findViewById(R.id.rv_recyclerView)
        rv_recyclerView.adapter = RecyclerAdapter(mainViewModel.getAllTaskFromList())
        rv_recyclerView.layoutManager = LinearLayoutManager(view.context)



        addTask.setOnClickListener {

            addTaskDailog(mainViewModel)
            //update list after add
            rv_recyclerView.adapter = RecyclerAdapter(mainViewModel.getAllTaskFromList())

        }



        //show all data
        mainViewModel.getAllTaskFromList()
        Log.e("checked", "1 ${mainViewModel.getAllTaskFromList()[0].titleTask}")

    }





    fun addTaskDailog(mainViewModel:TaskVM) {
        //Inflate the dialog with custom view
        val addTask = android.app.AlertDialog.Builder(view?.context)
        val myView: View = layoutInflater.inflate(R.layout.add_tasks, null)

        addTask.setView(myView)
        addTask.setTitle("Add Task")

        val Save: Button = myView.findViewById(R.id.btnSave)
        val cancel: Button = myView.findViewById(R.id.btnCancel)
        val dateAlerAddTask: TextView = myView.findViewById(R.id.tvDateToday)
        dateAlerAddTask.setText("Date  $formatted")

        var count=AppRepo.nextIdList
         ed_taksTitle = myView.findViewById(R.id.edtTitleTask)
         ed_taskDescription = myView.findViewById(R.id.edtDescription)
         calendarTask = myView.findViewById(R.id.id_calendar)
        priority = "low"


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
                && ed_taskDescription.text.isNotEmpty()
                && priority.isNotEmpty() && calendarTask.text.isNotEmpty()
            ) {
                //delete...
                Toast.makeText(
                    context, " Title task : ${ed_taksTitle.text}" +
                            " \n Description ${ed_taskDescription.text} " +
                            "\n Prplaty $priority \n" +
                            " $formatted \n Due Date $dueDate", Toast.LENGTH_SHORT).show()


                //Insert to list var insertTask
                 insertTask = DataTask(count,"${ed_taksTitle.text}",
                    "${ed_taskDescription.text}",
                     "$formatted",
                     "$dueDate",
                     "${priority}"
                 ,false)

                mainViewModel.insertTask(insertTask)

                clearEdit()
                //fun Clear
                count++

            } else {
                Toast.makeText(
                    context, " Please Compleat  ", Toast.LENGTH_SHORT
                ).show()
            }
        }

        addTask.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        addTask.show()//.window?.setBackgroundDrawableResource(R.drawable.ic_launcher_foreground)

    }

    private fun clearEdit() {
        ed_taksTitle.setText(null)
        ed_taskDescription.setText(null)

        calendarTask.setText(null)

    }

/*
    fun priority(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_l ->
                    if (checked) {

                        priority = "Low"
                        Log.e("Priority", priority)

                    }
                R.id.radio_m ->
                    if (checked) {
                        priority = "Mid"

                        Log.e("Priority", priority)

                    }

                R.id.radio_h ->
                    if (checked) {

                        priority = "Height"


                        Log.e("checked", priority)
                        // Ninjas rule
                    }
            }
        }
    }

*/
}