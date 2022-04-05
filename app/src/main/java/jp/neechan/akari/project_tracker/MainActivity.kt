package jp.neechan.akari.project_tracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val textView by lazy { findViewById<TextView>(R.id.textView) }
    private val editText by lazy { findViewById<EditText>(R.id.editText) }
    private val enterButton by lazy { findViewById<Button>(R.id.enterButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()
    }

    private fun setupListeners() {
        enterButton.setOnClickListener {
            textView.text = editText.text
        }
    }
}
