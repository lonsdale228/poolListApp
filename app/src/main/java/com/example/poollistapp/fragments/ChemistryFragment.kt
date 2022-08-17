package com.example.poollistapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poollistapp.R
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import kotlin.concurrent.thread


class ChemistryFragment : Fragment() {

    private var docu:String=""
    private lateinit var recyclerView: RecyclerView


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = null
        view = inflater.inflate(R.layout.fragment_chemistry, container, false)


        var lblKurs:TextView=view.findViewById(R.id.kursText)



        recyclerView=view.findViewById(R.id.chemistry_grid)
        recyclerView.layoutManager=GridLayoutManager(view.context,2)



//        val doc = Jsoup.connect("https://kurs.com.ua/").get()

//        val getCourse=CoroutineScope(Main).launch{
//            if networkrq
//
//            val doc = Jsoup.connect("https://kurs.com.ua/").get()
//            docu=doc.getElementsByClass("course").get(2).text().split(" ")[0]
//        }

        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch(CoroutineExceptionHandler { _, throwable ->
            println("$throwable")
        }) {
            val a = async {
                delay(0)
                val doc = Jsoup.connect("https://kurs.com.ua/").get()
                docu=doc.getElementsByClass("course").get(2).text().split(" ")[0]

                throw Exception("excep a")
                2
            }
            val b = async {
                delay(0)
                3
            }
            println(a.await() + b.await())
        }

        fun CourseText(){
            if (docu.isEmpty()) {
                activity?.runOnUiThread {
                    lblKurs.text ="Курс $ - "
                }
            } else {
                activity?.runOnUiThread{
                    lblKurs.text="Курс $ - "+docu+" UAH"
                }

            }
        }

        thread {
                while (true){
                    CourseText()
                    Thread.sleep(1000)
                }
        }

//        thread {
//            try {
//
//
//                activity?.runOnUiThread{
//                    if (doc!=null){
//
//                        lblKurs.text=docu.toString()
//
//
//                    }
//                }
//            } catch (e:android.os.NetworkOnMainThreadException){
//                Toast.makeText(view.context,"No internet connection!",Toast.LENGTH_SHORT).show()
//            }
//
//        }






        return view
    }



//    private fun setNewCourse(input:String,tv:TextView){
//        tv.text=input
//
//    }
//
//    private suspend fun setTextOnMainThread(input: String,tv:TextView){
//        withContext(Main){
//            setNewCourse(input,tv)
//        }
//    }
//
//    private suspend fun getCourseFromWeb(tv:TextView):String{
//        val doc = Jsoup.connect("https://kurs.com.ua/").get()
//        var docu=doc.getElementsByClass("course").get(2).text().split(" ")[0]
//
//        setTextOnMainThread("abeba",tv)
//        delay(1000)
//        return docu
//    }

}
