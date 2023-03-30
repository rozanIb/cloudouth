package com.example.cloudauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        val tx_Email : TextView = findViewById(R.id.txEmail)
        val tx_Pass : TextView = findViewById(R.id.txPass)
        val btn_logout : Button = findViewById(R.id.btn_logout)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser==null){
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }else{
            tx_Email.setText(currentUser.email)
            tx_Pass.setText(currentUser.uid)
        }
      btn_logout.setOnClickListener {
          Firebase.auth.signOut()
          var i = Intent(this, MainActivity::class.java)
          startActivity(i)
          Toast.makeText(baseContext, "Log out success .", Toast.LENGTH_SHORT).show()

      }
    }

}