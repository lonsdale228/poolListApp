package com.example.poollistapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.poollistapp.R
import com.example.poollistapp.database.SQLiteHelper
import com.example.poollistapp.models.User

class UserAdapter:RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var usrList:ArrayList<User> = ArrayList()
    var onItemClick:((User)-> Unit)?=null
    var onItemLongClick: ((User) -> Unit)?= null


    fun addItems(items:ArrayList<User>){
        this.usrList=items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)


    )


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val usr=usrList[position]
        holder.bindVIew(usr)

        holder.name.text=usr.name
        holder.street.text=usr.street
        holder.last_visit.text=usr.last_visit
        holder.phone_number.text=usr.phone_number



        holder.itemView.setOnClickListener{
            onItemClick?.invoke(usr)
        }

        holder.itemView.setOnLongClickListener{

            val pop= PopupMenu(holder.view.context,holder.phone_number)
            pop.inflate(R.menu.longclickmenu)

            pop.setOnMenuItemClickListener {item->

                when(item.itemId)

                {
                    R.id.menuEdit ->{
                        Toast.makeText( holder.view.context,"EDIT", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuDelete ->{
                        val sqliteHelper=SQLiteHelper(holder.view.context)

                        sqliteHelper.deleteUser(holder.phone_number.text.toString())
                    }


                }
                true
            }
            pop.show()


            //Toast.makeText( holder.view.context,holder.name.text.toString(), Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun getItemCount(): Int {
        return usrList.size
    }

    class UserViewHolder(var view: View):RecyclerView.ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.itemTitle)
        var street = view.findViewById<TextView>(R.id.itemStreet)
        var phone_number = view.findViewById<TextView>(R.id.itemNumber)
        var last_visit = view.findViewById<TextView>(R.id.itemLastVisit)


        fun bindVIew(usr: User){
            name.text=usr.name
            street.text=usr.street
            phone_number.text=usr.phone_number
            last_visit.text=usr.last_visit
        }
    }
}