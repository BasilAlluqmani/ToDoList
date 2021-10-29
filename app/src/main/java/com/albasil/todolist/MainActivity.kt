package com.albasil.todolist

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.albasil.todolist.Fragment.MainFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,MainFragment.newInstance()).commitNow()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.id_abuotApp -> {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.abuot_todolist))
                    .setIcon(R.drawable.ic_baseline_app_shortcut_24)
                    .setMessage("تم تطوير تطبيق ToDoList من قبل المطور باسل بأشراف من أ/ شادي سليم  و أ/ سمية الطويرقي .... ")
                    .setPositiveButton(getString(R.string.ok)){
                            dialog,_ ->

                        dialog.dismiss()
                    }.create().show()
                return true
            }R.id.id_localizations -> {

                Toast.makeText(this,"Loclizition",Toast.LENGTH_SHORT).show()
                return true
            }else -> return true
        }
    }





}