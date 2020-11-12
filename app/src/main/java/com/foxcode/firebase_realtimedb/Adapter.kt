package com.foxcode.firebase_realtimedb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dataArray : ArrayList<DataBaseRow>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.database_row,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imie.text = dataArray[holder.adapterPosition].name
        holder.nazwisko.text = dataArray[holder.adapterPosition].surename
    }

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val imie = view.findViewById(R.id.textView_name) as TextView
        val nazwisko = view.findViewById<TextView>(R.id.textView_surename)
    }
}