package dev.tiagosilva.finalprojectclassroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance();

        findViewById<View>(R.id.textEntrar).setOnClickListener{
            returnToLogin()
        }

        findViewById<View>(R.id.btnRecuperar).setOnClickListener{
            val emailAddress = findViewById<EditText>(R.id.etEmail).text.toString()

            firebaseAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email enviado com sucesso!", Toast.LENGTH_SHORT).show()
                    returnToLogin()
                }
            }
        }
    }

    fun returnToLogin(){
        val activity = Intent(this, LoginScreen::class.java);
        startActivity(activity)
        finish()
    }
}