package com.example.movieapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.databinding.ActivityFormLoginBinding
import com.google.firebase.auth.FirebaseAuth


class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#000000")
        binding.editEmail.requestFocus()

        binding.btSingIn.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val pass = binding.editPassword.text.toString()

            when{
                email.isEmpty() -> {
                    binding.containerEmail.helperText = "Please enter yor e-mail address"
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#FF9800")

                }
                pass.isEmpty() -> {
                    binding.containerPassword.helperText = "Please enter your password"
                    binding.containerPassword.boxStrokeColor = Color.parseColor("#FF9800")
                }
                else -> {
                    auth(email, pass)
                }
            }
        }

        binding.txtSingUp.setOnClickListener{
            val intent = Intent(this, FormSingUp::class.java)
            startActivity(intent)
        }
    }

    private fun auth(email : String, pass : String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener { auth ->
            if(auth.isSuccessful){
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                navMainView()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Login failure", Toast.LENGTH_SHORT).show()

        }
    }

    private fun navMainView(){
        val intent = Intent(this, MainView::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            navMainView()
        }
    }
}