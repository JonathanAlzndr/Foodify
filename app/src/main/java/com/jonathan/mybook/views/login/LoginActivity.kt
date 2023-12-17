package com.jonathan.mybook.views.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.jonathan.mybook.R
import com.jonathan.mybook.databinding.ActivityLoginBinding
import com.jonathan.mybook.views.home.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.ltLogin.setAnimation(R.raw.login)
        binding.ltLogin.repeatCount = LottieDrawable.INFINITE

        binding.loginButton.setOnClickListener {
            val email = binding.edtEmail.text
            val password = binding.edtPassword.text

            if (email != null && password != null) {
                if (email.isEmpty() || email.isBlank()) {
                    binding.edtEmail.error = "Please fill the email correctly!"
                }
                if (password.isEmpty() || password.isBlank()) {
                    binding.edtPassword.error = "Please fill the password correctly"
                } else {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
            }
        }
    }
}