package com.example.projektniniop

import android.content.Context
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projektniniop.R
import java.util.ArrayList

class CustomAdapter(private val context: Context, private val gnom_id: ArrayList<*>, private val gnom_name: ArrayList<*>, private val gnom_price: ArrayList<*>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.gnom_id_txt.text = gnom_id[position].toString()
        holder.gnom_name_txt.text = gnom_name[position].toString()
        holder.gnom_price_txt.text = gnom_price[position].toString()
    }

    override fun getItemCount(): Int {
        return gnom_id.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gnom_id_txt: TextView = itemView.findViewById(R.id.gnom_id_txt)
        var gnom_name_txt: TextView = itemView.findViewById(R.id.gnom_name_txt)
        var gnom_price_txt: TextView = itemView.findViewById(R.id.gnom_price_txt)
    }
}
