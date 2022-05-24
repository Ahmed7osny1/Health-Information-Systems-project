package com.sriyank.kovid19.HOME

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.kovid19.HOME.Hand.HandAdapter
import com.sriyank.kovid19.HOME.Hand.HandData
import com.sriyank.kovid19.HOME.services.ServicesData
import com.sriyank.kovid19.HOME.services.servicesAdapter
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nav_view.bringToFront()

        nav_viwer()

        services_Data()

        ADVICE_Data()

        Hand_Wash()

        SYMPTOMS_data()

        spread_data()

    }

    private fun spread_data() {

        var MyArrayList = ArrayList<ServicesData>()

        MyArrayList.add(
            ServicesData(R.drawable.spreada,
            "Person-to-person spread as close contact with infected",
            "The coronavirus is thought to spread mainly from person to person. This can happen between people who are in close contact with one another."
            )
        )

        MyArrayList.add(
            ServicesData(R.drawable.spreadb,
                "Touching or contact with infected surfaces or objects",
                "A person can get COVID-19 by touching a surface or object that has the virus on it and then touching their own mouth, nose, or possibly their eyes."
            )
        )

        MyArrayList.add(
            ServicesData(R.drawable.spreadc,
                "Droplets that from infected person coughs or sneezes",
                "The coronavirus is thought to spread mainly from person to person. This can happen between people who are in close contact with one another."
            )
        )

        rec_TRANSMISSION.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)

        var TransmissionAdapter = TransmissionAdapter(MyArrayList)

        rec_TRANSMISSION.adapter = TransmissionAdapter


    }

    private fun SYMPTOMS_data() {

        var myArrayList = ArrayList<ServicesData>()

        myArrayList.add(
            ServicesData(R.drawable.symptoma,
                "High Fever",
                "This means you feel hot to touch on your chest or back (you do not need to measure your temperature). It is a common sign and also may appear in 2-10 days if you affected."
            )
        )

        myArrayList.add(
            ServicesData(R.drawable.symptomb,
                "Continuous cough",
                "This means coughing a lot for more than an hour, or 3 or more coughing episodes in 24 hours (if you usually have a cough, it may be worse than usual)."
            )
        )

        myArrayList.add(
            ServicesData(R.drawable.symptomc,
                "Shortness of breath",
                "Around 1 out of every 6 people who gets COVID-19 becomes seriously ill and develops difficulty breathing or shortness of breath."
            )
        )

        rec_SYMPTOMS.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)

        var AdviceAdapter = AdviceAdapter(myArrayList)
        rec_SYMPTOMS.adapter = AdviceAdapter

    }


    private fun Hand_Wash() {

        var myArrayList =ArrayList<HandData>()

        myArrayList.add(
            HandData (
                R.drawable.handa,
            "Soap on Hand")
        )

        myArrayList.add(
            HandData (
                R.drawable.handb,
                "Palm to Palm")
        )

        myArrayList.add(
            HandData (
                R.drawable.handc,
                "Between Fingers")
        )

        myArrayList.add(
            HandData (
                R.drawable.handd,
                "Back to Hands")
        )

        myArrayList.add(
            HandData (
                R.drawable.hande,
                "Clean with Water")
        )

        myArrayList.add(
            HandData (
                R.drawable.handf,
                "Focus on Wrist")
        )

        rec_wash.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)

        var HandAdapter = HandAdapter(myArrayList)
        rec_wash.adapter = HandAdapter

    }

    private fun ADVICE_Data() {

        var myArrayList = ArrayList<ServicesData>()

        myArrayList.add(
            ServicesData(R.drawable.advicea,
                "Wash your hands frequently",
                "Regularly and thoroughly clean your hands with an alcohol-based hand rub or wash them with soap and water for at least 20 seconds.")
        )

        myArrayList.add(
            ServicesData(R.drawable.adviceb,
                "Maintain social distancing",
                "Maintain at least 1 metre (3 feet) distance between yourself & anyone who is coughing or sneezing. If you are too close, get chance to infected.")
        )

        myArrayList.add(
            ServicesData(R.drawable.advicec,
                "Avoid touching face",
                "Hands touch many surfaces and can pick up viruses. So, hands can transfer the virus to your eyes, nose or mouth and can make you sick.")
        )

        myArrayList.add(
            ServicesData(R.drawable.adviced,
            "Practice respiratory hygiene",
            "Maintain good respiratory hygiene as covering your mouth & nose with your bent elbow or tissue when cough or sneeze.")
        )

        rec_ADVICE.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)

        var AdviceAdapter = AdviceAdapter(myArrayList)
        rec_ADVICE.adapter = AdviceAdapter

    }

    private fun services_Data() {

        var myArrayList = ArrayList<ServicesData>()
        myArrayList.add(
            ServicesData(R.drawable.vaccine,
            "Vaccine Reservation",
            "The Coronavirus (COVID-19) was first reported in Wuhan, Hubei, China in December 2019")
        )

        myArrayList.add(
            ServicesData(R.drawable.test,
            "Reserve PCR Analysis",
            "The Coronavirus (COVID-19) was first reported in Wuhan, Hubei, China in December 2019")
        )

        myArrayList.add(
            ServicesData(R.drawable.contact,
            "Contact With Doctor",
            "The Coronavirus (COVID-19) was first reported in Wuhan, Hubei, China in December 2019")
        )

        rec_services.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)

        var servicesAdapter = servicesAdapter(myArrayList)
        rec_services.adapter = servicesAdapter
    }

    fun nav_viwer(){

        nav_view.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.home -> Toast.makeText(this,"HOME",Toast.LENGTH_LONG).show()
                R.id.settings -> Toast.makeText(this,"settings",Toast.LENGTH_LONG).show()
            }
            false
        }

    }

    fun openNavigationDrawer(view: View) {

        if (drawable_layout.isDrawerOpen(GravityCompat.START)) {
            drawable_layout.closeDrawer(GravityCompat.START)
        } else {
            drawable_layout.openDrawer(GravityCompat.START)
        }

    }
}