package com.example.cloudauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tx_singup: TextView = findViewById(R.id.tx_singup)
        val login_email : EditText = findViewById(R.id.login_email)
        val login_password : EditText = findViewById(R.id.login_password)
        val btn_login : Button =findViewById(R.id.btn_login)
        var email = login_email .text
        var password = login_password.text
        auth = Firebase.auth

        tx_singup.setOnClickListener {
            var i = Intent(this, SingUp::class.java)
            startActivity(i)
        }

      btn_login.setOnClickListener {
          logNewAccount(email.toString(),password.toString())
      }
}
   private fun logNewAccount(email: String,password: String) {
       auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var i = Intent(this, Home::class.java)
                    startActivity(i)
                    Toast.makeText(baseContext, "Authentication success .", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }


        }


    }

