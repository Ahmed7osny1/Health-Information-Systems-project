package com.sriyank.kovid19.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        repasslottie.visibility = View.VISIBLE

        send.setOnClickListener {
            startActivity(Intent(this,successfulSend::class.java))
        }

    }
}