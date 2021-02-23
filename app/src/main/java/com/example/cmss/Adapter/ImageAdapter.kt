package com.example.cmss.Adapter




import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cmss.R
import com.example.cmss.ticket_number

class ImageAdapter(private var date:List<String>, private var pics:List<Int>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>(){
    inner class ViewHolder(itemview:View) : RecyclerView.ViewHolder(itemview){
        var itemticket: TextView = itemview.findViewById(R.id.date)
        var itempic : ImageView =itemview.findViewById(R.id.imageView)
        init {
            itemview.setOnClickListener { View -> val position: Int = adapterPosition
                //  Toast.makeText(itemview.context,"successfull" +itemrating.text, Toast.LENGTH_LONG).show()
                val intent = Intent(itemview.context, ticket_number::class.java)
                itemview.context.startActivity(intent)



            }
        }



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_events,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return date.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemticket.text = date[position]
        holder.itempic.setImageResource(pics[position])

    }




}