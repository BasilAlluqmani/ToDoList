package com.albasil.todolist.Fragment

//                        Log.e("checked",priority)
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albasil.todolist.R
import com.albasil.todolist.RecyclerAdapter
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


    private lateinit var priority:String
    private lateinit var dueDate :String

    private lateinit var addTask:ImageButton
    private lateinit var taskTitle:EditText
    private lateinit var description:EditText

    private  var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList= mutableListOf<Int>()

    private lateinit var rv_recyclerView: RecyclerView

    val tasks = arrayListOf<String>()


    override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View? {

       val view = inflater.inflate(R.layout.fragment_main, container, false)

         var addTask: ImageButton = view.findViewById(R.id.btnTask)

       addTask.setOnClickListener {
           addTaskDailog()


       }

           rv_recyclerView=view.findViewById(R.id.rv_recyclerView)
           rv_recyclerView.layoutManager = LinearLayoutManager(view.context)
           rv_recyclerView.adapter = RecyclerAdapter(titleList,descList,imageList)


           postToList()


       return view
   }

    private fun addTitleList(title:String, description :String, image:Int){

        titleList.add(title)
        descList.add(description)
        imageList.add(image)
    }

    private fun postToList(){

        val array = arrayListOf("Task","Task","Task","Task","Task")
        for (i in 1 ..tasks.size){
            addTitleList(title = "Task ${tasks[i-1]}","Descripiton $i ", R.drawable.ic_baseline_bookmark_24)


        }
    }


   fun addTaskDailog(){
       //Inflate the dialog with custom view
       val addTask = android.app.AlertDialog.Builder(view?.context)
       val myView: View = layoutInflater.inflate(R.layout.add_tasks,null)
       addTask.setView(myView)
       addTask.setTitle("Add Task")

       val Save:Button=myView.findViewById(R.id.btnSave)
       val cancel:Button=myView.findViewById(R.id.btnCancel)

       val dateAlerAddTask: TextView =myView.findViewById(R.id.tvDateToday)
       dateAlerAddTask.setText("Date  $formatted")

       val ed_taksTitle:EditText=myView.findViewById(R.id.edtTitleTask)
       val ed_taskDescription:EditText=myView.findViewById(R.id.edtDescription)

       var calendarTask: TextView =myView.findViewById(R.id.id_calendar)

       calendarTask.setOnClickListener {

           val c = Calendar.getInstance()
           val day = c.get(Calendar.DAY_OF_MONTH)
           val month = c.get(Calendar.MONTH)
           val year = c.get(Calendar.YEAR)
           val datePickerDialog=  DatePickerDialog(requireView().context, DatePickerDialog.OnDateSetListener {
                   view, y, m, d ->
               dueDate = "$y/${m+1}/$d"

               calendarTask.setText(dueDate)

           }, year, month, day)
           datePickerDialog.datePicker.minDate =c.timeInMillis
           datePickerDialog.show()


       }




       Save.setOnClickListener {
           if (ed_taksTitle.text.isNotEmpty()
               && ed_taskDescription.text.isNotEmpty()
/* &&
        priority.isNotEmpty()*/) {
               Toast.makeText(
                   context, " Title task : ${ed_taksTitle.text}" +
                           " \n Description ${ed_taskDescription.text} " +
                           "\n Prplaty  \n" +
                           " $formatted \n Due Date $dueDate", Toast.LENGTH_SHORT
               ).show()


               tasks.add("${ed_taksTitle.text}")


               postToList()

               //fun Clear
               ed_taksTitle.setText(null)
               ed_taskDescription.setText(null)
               calendarTask.setText(null)
               ed_taksTitle.setText(null)

           }else{
               Toast.makeText(
                   context, " Please Compleat  ", Toast.LENGTH_SHORT).show()
           }

       }



       addTask.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })



       addTask.show().window?.setBackgroundDrawableResource(R.drawable.ic_launcher_background)

   }

/*
   fun onRadioButtonClicked(view: View) {
       if (view is RadioButton) {
           // Is the button now checked?
           val checked = view.isChecked

           // Check which radio button was clicked
           when (view.getId()) {
               R.id.radio_l ->
                   if (checked) {


                       priority= "Low"
                       //Log.e("Priority",priority)


                   }
               R.id.radio_m ->
                   if (checked) {
                       priority="Mid"

                     //  Log.e("Priority",priority)

                       // Ninjas rule
                   }

               R.id.radio_h ->
                   if (checked) {

                       priority="Height"


                     //  Log.e("checked",priority)
                       // Ninjas rule
                   }
           }
       }
   }


*/



}