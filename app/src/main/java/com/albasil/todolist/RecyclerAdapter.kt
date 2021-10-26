package com.albasil.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter (private var titleList:List<String>, private var details:List<String>, private var image:List<Int>):
RecyclerView.Adapter<RecyclerAdapter.ViewHoder>(){
//inner
     class ViewHoder(itemView: View):RecyclerView.ViewHolder(itemView){

        val itemTile: TextView = itemView.findViewById(R.id.tv_title)
        val itemDetail: TextView =itemView.findViewById(R.id.tv_description)
        val itemPicture: ImageView =itemView.findViewById(R.id.iv_image)


        init {
            itemView.setOnClickListener{ v:View ->
                val position :Int= adapterPosition
                Toast.makeText(itemView.context,"Title ${position+1}",Toast.LENGTH_SHORT).show()
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHoder {


        //ارجع افهمها مره ثانيه
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.task_item_layout,parent, false)

       return ViewHoder(v)

    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHoder, position: Int) {

        holder.itemTile.text= titleList[position]
        holder.itemDetail.text = details[position]
        holder.itemPicture.setImageResource(image[position])


      /*  holder.itemTile.setOnClickListener { object :View.OnClickListener{
           override fun onClick(v:View?){
                val activity=v!!.context as AppCompatActivity
                val taskInfo_fragment =task_info()
                activity.supportFragmentManager.beginTransaction().addToBackStack(null).
                replace(R.id.res,taskInfo_fragment).commit()
            }  }
        }*/


    }

    override fun getItemCount(): Int {

        return titleList.size

    }
}