package com.example.cloudauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SingUp : AppCompatActivity() {
private lateinit var auth: FirebaseAuth
   public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
       if(currentUser != null){
        updateUI()
    }}
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.sing_up)
        val tx_login: TextView = findViewById(R.id.tx_login)
        val edt_Email : EditText = findViewById(R.id.edt_email)
        val edt_password : EditText = findViewById(R.id.edt_password)
        val btn_singup :Button=findViewById(R.id.btn_singup)
        auth = Firebase.auth
        var email = edt_Email.text
        var password = edt_password.text



   tx_login.setOnClickListener {
    var i = Intent(this, MainActivity::class.java)
    startActivity(i)
}
        btn_singup.setOnClickListener {
            createNewAccount(email.toString(), password.toString())
        }

    }
fun updateUI(){
    var i = Intent(this, MainActivity::class.java)
    startActivity(i)
}
    private fun createNewAccount(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Authentication Success", Toast.LENGTH_SHORT).show()
                 //   val user = auth.currentUser
                    updateUI()
                }else if (email.equals(email)&&password.equals(password)){
                    Toast.makeText(baseContext, "Email is already exists", Toast.LENGTH_SHORT).show()
            }else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }
}