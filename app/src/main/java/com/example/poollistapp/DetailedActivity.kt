package com.example.poollistapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.poollistapp.models.User

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)


        val user = intent.getParcelableExtra<User>("user")
        if (user!=null){
            val titleDetailed:TextView=findViewById(R.id.titleDetailed)
            val streetDetailed:TextView=findViewById(R.id.streetDetailed)
            val phoneDetailed:TextView=findViewById(R.id.phoneNumberDetailed)
           // val lastVisitDetailed:TextView=findViewById(R.id.)


            titleDetailed.text=user.name
            streetDetailed.text=user.street
            phoneDetailed.text=user.phone_number
        }
    }
}