package com.example.cmss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmss.Adapter.ImageAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private var ticket = mutableListOf<String>()
    private var pics = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        events();


        lists.layoutManager = LinearLayoutManager(this)
        lists.adapter = ImageAdapter(ticket,pics)


    }
    private fun addtolist(ticketlist:String,picslist:Int){
        ticket.add(ticketlist)
        pics.add((picslist))
    }
    private fun events(){
        addtolist("Starts at 2:00pm", R.drawable.dsc_2021_header)
        addtolist("Starts at 3.00am",R.drawable.dsc_2021_header)
        addtolist("starts at 5:00pm",R.drawable.dsc_2021_header)

    }
}