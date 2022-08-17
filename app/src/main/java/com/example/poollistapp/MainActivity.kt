package com.example.poollistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.example.poollistapp.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val paymentsFragment = PaymentsFragment()
    private val poolListFragment = PoolListFragment()
    private val timetableFragment = TimetableFragment()
    private val chemistryFragment = ChemistryFragment()
    private val servicesFragment = ServicesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //choose main fragment
        replaceFragment(timetableFragment)

        //init bottomnavmenu
        val bot = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        //on bottomnav click choose fragment
        bot.setOnItemSelectedListener {
            when(it.itemId){
                R.id.timetable->replaceFragment(timetableFragment)
                R.id.poollist->replaceFragment(poolListFragment)
                R.id.payments->replaceFragment(paymentsFragment)
                R.id.chemistry->replaceFragment(chemistryFragment)
                R.id.services->replaceFragment(servicesFragment)
            }
            true
        }

        //disable bottomNavMenu hints on long tap
        bot
            .menu.forEach {
                TooltipCompat.setTooltipText(findViewById<BottomNavigationView>(it.itemId), null)
            }


    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }
}