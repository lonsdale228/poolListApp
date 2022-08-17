package com.example.poollistapp.fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poollistapp.DetailedActivity
import com.example.poollistapp.R
import com.example.poollistapp.adapters.UserAdapter
import com.example.poollistapp.database.SQLiteHelper
import com.example.poollistapp.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PoolListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var poolList:ArrayList<User>
    private lateinit var userAdapter: UserAdapter
    private var adapter: UserAdapter?=null
    private lateinit var sqLiteHelper: SQLiteHelper



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = null
        view = inflater.inflate(R.layout.fragment_pool_list, container, false)
        val btnAdd=view.findViewById<FloatingActionButton>(R.id.floatingbtnAdd)


        sqLiteHelper=SQLiteHelper(view.context)

        recyclerView=view.findViewById(R.id.listView)

        registerForContextMenu(recyclerView);

        recyclerView.hasFixedSize()
//        recyclerView.layoutManager=LinearLayoutManager(view.context)
//
        poolList= ArrayList()
//
//        poolList.add(User("Alex","Centralnaya",123,132))
//
//        recyclerView.adapter=userAdapter
//
//


        recyclerView.layoutManager=LinearLayoutManager(view.context)
        adapter= UserAdapter()
        //sqLiteHelper.insertUser(User("Наталья","Центральная","682312193","12312312"))
        poolList.addAll(sqLiteHelper.getAllUsers())
        adapter?.addItems(poolList)

        adapter!!.onItemClick={
            val intent= Intent(getActivity(), DetailedActivity::class.java)
            intent.putExtra("user",it)
            startActivity(intent)
        }

        recyclerView.adapter=adapter





        class CustomDialogClass(context: Context): Dialog(context){
            init{
                setCancelable(true)
            }


            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.activity_dialog)


                val btnSave=findViewById<Button>(R.id.btnSave)

                btnSave.setOnClickListener(){

                    val edName=findViewById<EditText>(R.id.edName).text.toString()
                    val edStreet=findViewById<EditText>(R.id.edStreet).text.toString()
                    val edPhoneNumber=findViewById<EditText>(R.id.edPhoneNumber).text.toString()


                    if (edName.isEmpty() or edStreet.isEmpty() or edPhoneNumber.isEmpty()){
                        Toast.makeText(context,"Enter all field!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        sqLiteHelper=SQLiteHelper(view.context)
                        sqLiteHelper.insertUser(User(name=edName, street = edStreet, phone_number = edPhoneNumber,""))

//                        poolList.addAll(sqLiteHelper.getAllUsers())
//                        adapter?.addItems(poolList)
//
//                        adapter!!.onItemClick={
//                            val intent= Intent(getActivity(), DetailedActivity::class.java)
//                            intent.putExtra("user",it)
//                            startActivity(intent)
//                       // }
//
//                        recyclerView.adapter=adapter

                    poolList.clear()
                    poolList.addAll(sqLiteHelper.getAllUsers())


                    recyclerView.adapter=adapter

                        Toast.makeText(context,"Added!", Toast.LENGTH_SHORT).show()
                        onBackPressed()
                    }

                }
            }

        }

        btnAdd.setOnClickListener(){
            CustomDialogClass(view.context).show()
        }




        return view
    }

}