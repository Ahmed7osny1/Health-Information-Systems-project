package com.sriyank.kovid19.HOME

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.sriyank.kovid19.APIAuth.MyRequestArray
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_contact_with_doctor.*
import kotlinx.android.synthetic.main.activity_reserve_pcranalysis.*
import org.json.JSONObject

class ContactWithDoctor : AppCompatActivity() {

    private lateinit var DoctorSelected: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_with_doctor)

        backBtn.setOnClickListener { onBackPressed() }

        val items1 = getData()
        val adapter1 = ArrayAdapter(this, R.layout.drop_down_list_item, items1)
        textViewDoctor.setOnClickListener {
            spinnerDoctor.adapter = adapter1
            spinnerDoctor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    DoctorSelected = items1[p2]
                    textViewDoctor.text = items1[p2]
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        sendDoctor.setOnClickListener {

            if(mEditMassage.text!!.isNotEmpty() && messageContent.text!!.isNotEmpty()
                && textViewDoctor.text.isNotEmpty()) {
                Toast.makeText(this, "SEND SUCCESSFUL", Toast.LENGTH_LONG).show()
            }else{

                if(textViewDoctor.text.isEmpty())
                    Toast.makeText(this, "Choose your Doctor", Toast.LENGTH_LONG).show()

                if(mEditMassage.text!!.isEmpty())
                    Toast.makeText(this, "Enter your Title Message", Toast.LENGTH_LONG).show()

                if(messageContent.text!!.isEmpty())
                    Toast.makeText(this, "Enter your Content Message", Toast.LENGTH_LONG).show()

            }
        }


    }

    private fun getData(): ArrayList<String> {

        val arrayList = arrayListOf<String>()

        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this)
        val request = MyRequestArray(
            this,
            Request.Method.GET,
            "/available-doctors",
            null,
            { response ->

                Log.d("mytag", "$response")

                for (i in 0 until response.length()) {

                    val student: JSONObject = response.getJSONObject(i)


                    // get the current student (json object) data
                    var fname = student.getString("doc_fname")
                    var lname = student.getString("doc_lname")

                    arrayList.add("$fname $lname")
                }
                Log.d("mytag", "$arrayList")

            },
            { error ->
                Log.e("mytag", "Error: $error - Status Code = ${error.networkResponse?.statusCode}")
                Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
        return arrayList
    }

}