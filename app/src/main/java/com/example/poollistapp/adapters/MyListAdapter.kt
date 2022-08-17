package com.example.poollistapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poollistapp.R
import com.example.poollistapp.models.User


class MyListAdapter(private val items:List<User>):
    RecyclerView.Adapter<MyListAdapter.UserViewHolder>(){

    var onItemClick:((User)-> Unit)?=null




    class UserViewHolder(view:View):RecyclerView.ViewHolder(view){
        val itemtitle:TextView = view.findViewById(R.id.itemTitle)
        val itemstreet:TextView = view.findViewById(R.id.itemStreet)
        val itemlastvisit:TextView = view.findViewById(R.id.itemLastVisit)
        val itemphonenumber:TextView=view.findViewById(R.id.itemNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = items[position]
        holder.itemtitle.text=user.name
        holder.itemstreet.text=user.street
        holder.itemlastvisit.text=user.last_visit
        holder.itemphonenumber.text=user.phone_number
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(user)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


//class MyListAdapter(var mCtx:Context,var resource:Int, var items:List<User>):ArrayAdapter<User>(mCtx,resource, items) {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        //inflate layout
//        val layoutInflater:LayoutInflater=LayoutInflater.from(mCtx)
//
//        val view:View=layoutInflater.inflate(resource,null)
//
//        val itemtitle:TextView = view.findViewById(R.id.itemTitle)
//        val itemstreet:TextView = view.findViewById(R.id.itemStreet)
//        val itemlastvisit:TextView = view.findViewById(R.id.itemLastVisit)
//
//        var mItems:User = items[position]
//
//        itemtitle.text=mItems.name
//        itemstreet.text=mItems.street
//
//        itemlastvisit.text=mItems.lastvisit.toString()
//
//        return view
//    }
//}