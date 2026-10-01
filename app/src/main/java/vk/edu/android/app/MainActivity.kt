package vk.edu.android.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val regex = Regex("^[\\w\\s+-]{1,40}$")

        val lable = findViewById<TextView>(R.id.main_lable)
        val userText: EditText = findViewById<EditText>(R.id.user_text)
        val button: Button = findViewById<Button>(R.id.b_to_second_activity)
        val callButton: Button = findViewById<Button>(R.id.b_to_call)
        val shareButton = findViewById<Button>(R.id.b_to_share)

        button.setOnClickListener { // для перехода на вторую активити

            val text = userText.text.toString().trim() // обработка входящего текста

            if (text.isBlank() || text.isEmpty()) {
                userText.error = "Input text"
            } else if (!regex.matches(text)) {
                userText.error = "Only digits and letters fewer then 40 characters"
            } else {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("text_key", text)
                startActivity(intent)
            }

        }

        callButton.setOnClickListener { // для перехода к приложению для звонков

            val phoneRegex = Regex("^[0-9+\\-\\s()]{3,20}$")
            val phoneNumber = userText.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                userText.error = "Input phone number"
                return@setOnClickListener
            }

            if (!phoneNumber.matches(phoneRegex)) {
                userText.error = "Invalid number"
                return@setOnClickListener
            }

            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }

            if (dialIntent.resolveActivity(packageManager) != null) {
                startActivity(dialIntent)
            } else {
                userText.error = "The App not found"
            }
        }

        shareButton.setOnClickListener { // системный intent
            val shareText = userText.text.toString().trim()

            if (shareText.isBlank() || shareText.isEmpty()) {
                userText.error = "Input text"
                return@setOnClickListener
            }

            if (!regex.matches(shareText)) {
                userText.error = "Only digits and letters fewer then 40 characters"
                return@setOnClickListener
            }

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }

            startActivity(Intent.createChooser(shareIntent, "Share with..."))
        }


    }
}