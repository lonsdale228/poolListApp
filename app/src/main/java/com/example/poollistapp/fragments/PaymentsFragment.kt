package com.example.poollistapp.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.poollistapp.R
import java.util.*
import java.util.Calendar.*

class PaymentsFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? = null
        view = inflater.inflate(R.layout.fragment_payments, container, false)

        val lbl:TextView=view.findViewById(R.id.lbl)
        var day = Calendar.getInstance().get(DAY_OF_MONTH).toString()
        var month=(Calendar.getInstance().get(MONTH)+1).toString()
        var year=Calendar.getInstance().get(YEAR).toString()
        var date="$day $month $year".split(" ")


        lbl.text= date[1]



        return view
    }

}