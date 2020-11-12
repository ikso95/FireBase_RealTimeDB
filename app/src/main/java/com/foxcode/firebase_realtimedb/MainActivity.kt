package com.foxcode.firebase_realtimedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    private lateinit var myRef : DatabaseReference
lateinit var listOfItems : ArrayList<DataBaseRow>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase = FirebaseDatabase.getInstance()
        myRef = firebase.getReference("ArrayData")

        recyclerView.layoutManager = GridLayoutManager(applicationContext,1)

        button_send.setOnClickListener {
            val imie = editText_name.text.toString()
            val nazwisko = editText_surename.text.toString()
            val firebaseInput = DataBaseRow(imie, nazwisko)
            myRef.child("${Date().time}").setValue(firebaseInput)
        }

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                listOfItems = ArrayList()
                for(i in snapshot.children)
                {
                    val newRow = i.getValue(DataBaseRow::class.java)
                    listOfItems.add(newRow!!)
                }
                setupAdapter(listOfItems)
            }
        })

    }

    private fun setupAdapter(arrayData : ArrayList<DataBaseRow>){
        recyclerView.adapter = Adapter(arrayData)
    }
}