package com.albasil.todolist

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {


    // Date
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val formatted = current.format(formatter)


   private lateinit var priority:String
   private lateinit var dueDate :String

    private lateinit var addTask:ImageButton
    private lateinit var taskTitle:EditText
    private lateinit var description:EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addTask=findViewById(R.id.btnTask)

        addTask.setOnClickListener {
            addTaskDailog()


        }

    }



    fun addTaskDailog(){
        //Inflate the dialog with custom view
        val addTask = android.app.AlertDialog.Builder(this)
        val myView: View = layoutInflater.inflate(R.layout.add_tasks,null)
       addTask.setView(myView)
        addTask.setTitle("Add Task")

        val Save:Button=myView.findViewById(R.id.btnSave)
        val cancel:Button=myView.findViewById(R.id.btnCancel)

        val dateAlerAddTask:TextView=myView.findViewById(R.id.tvDateToday)
        dateAlerAddTask.setText("Date  $formatted")

        val ed_taksTitle:EditText=myView.findViewById(R.id.edtTitleTask)
        val ed_taskDescription:EditText=myView.findViewById(R.id.edtDescription)

        val calendarTask:TextView=myView.findViewById(R.id.id_calendar)

        calendarTask.setOnClickListener {

            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)
          val datePickerDialog=  DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                    view, y, m, d ->
                dueDate = "$y/${m+1}/$d"

                calendarTask.setText(dueDate)

            }, year, month, day)
            datePickerDialog.datePicker.minDate =c.timeInMillis
                datePickerDialog.show()
        }




        Save.setOnClickListener {
            if (ed_taksTitle.text.isNotEmpty() && ed_taskDescription.text.isNotEmpty() &&
                    priority.isNotEmpty()) {
                Toast.makeText(
                    this, " Title task : ${ed_taksTitle.text}" +
                            " \n Description ${ed_taskDescription.text} " +
                            "\n Prplaty $priority \n" +
                            " $formatted \n Due Date $dueDate", Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(
                    this, " Please Compleat  ",Toast.LENGTH_SHORT).show()
            }

        }



            addTask.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })



        addTask.show().window?.setBackgroundDrawableResource(R.drawable.ic_launcher_background)

    }
//            Toast.makeText(this," Title task : ${} ",Toast.LENGTH_SHORT).show()


    fun onRadioButtonClicked(view: View) {
        //priority
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_l ->
                    if (checked) {


                        priority= "Low"
                        Toast.makeText(this," Priority : ${priority} ",Toast.LENGTH_SHORT).show()


                    }
                R.id.radio_m ->
                    if (checked) {
                        priority="Mid"
                        Toast.makeText(this," Priority : ${priority} ",Toast.LENGTH_SHORT).show()

                        // Ninjas rule
                    }

                R.id.radio_h ->
                    if (checked) {

                        priority="Height"

                        Toast.makeText(this," Priority : ${priority} ",Toast.LENGTH_SHORT).show()

                        // Ninjas rule
                    }
            }
        }
    }





}