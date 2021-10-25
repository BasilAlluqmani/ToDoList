package com.albasil.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.text.InputType
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {


    // Date
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val formatted = current.format(formatter)


   private lateinit var priority:String

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




        Save.setOnClickListener {
            if (ed_taksTitle.text.isNotEmpty() && ed_taskDescription.text.isNotEmpty() &&
                    priority.isNotEmpty()) {
                Toast.makeText(
                    this, " Title task : ${ed_taksTitle.text}" +
                            " \n Description ${ed_taskDescription.text} " +
                            "\n Prplaty $priority \n" +
                            " $formatted", Toast.LENGTH_SHORT
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


/*
    fun addTask(){
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Add Task")

        builder.setMessage("Date : $formatted")

// Set up the input  description
      var  taskTitle = EditText(this)
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        taskTitle.setHint("Enter Task Title")
        taskTitle.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(taskTitle)


        //Description title
      var  description = EditText(this)
        description.setHint("Enter Text")
        description.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(description)


// Set up the buttons
        builder.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var _taskTitle = taskTitle.text.toString()
            var _taskDescription = taskTitle.text.toString()

            Toast.makeText(this," Title task : $_taskTitle , Description $_taskDescription $formatted",Toast.LENGTH_SHORT).show()
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }*/

}