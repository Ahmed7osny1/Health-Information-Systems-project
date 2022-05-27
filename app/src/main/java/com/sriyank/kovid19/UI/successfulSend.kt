package com.sriyank.kovid19.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_forget_password.*

class successfulSend : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successful_send)

        repasslottie.visibility = View.VISIBLE
        Handler().postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
        },3500)

    }
}