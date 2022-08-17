package com.example.poollistapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poollistapp.R
import com.example.poollistapp.models.Services

class ServicesAdapter(private val items:List<Services>):RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {

    var onItemClick:((Services)-> Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.services_single_item,parent,false)
        return ServicesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ServicesViewHolder(view: View):RecyclerView.ViewHolder(view){
        val itemtitle: TextView = view.findViewById(R.id.itemTitle)
        val itemstreet: TextView = view.findViewById(R.id.itemStreet)
        val itemlastvisit: TextView = view.findViewById(R.id.itemLastVisit)
        val itemphonenumber: TextView =view.findViewById(R.id.itemNumber)
    }

}
