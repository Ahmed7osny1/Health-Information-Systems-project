package com.sriyank.kovid19.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        con.setOnClickListener {
            startActivity(Intent(this,ContinueRegister::class.java))
        }

        backBtn.setOnClickListener{ onBackPressed() }

        noAccountTv.setOnClickListener { onBackPressed() }


    }
}