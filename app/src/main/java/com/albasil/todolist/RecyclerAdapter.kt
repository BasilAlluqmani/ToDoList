package com.albasil.todolist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter (private var titles:List<String>, private var details:List<String>, private var image:List<Int>):
RecyclerView.Adapter<RecyclerAdapter.ViewHoder>(){

    inner class ViewHoder(itemView: View):RecyclerView.ViewHolder(itemView){

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

        holder.itemTile.text= titles[position]
        holder.itemDetail.text = details[position]
        holder.itemPicture.setImageResource(image[position])
    }

    override fun getItemCount(): Int {

        return titles.size

    }
}