package com.makhabatusen.kotlin1_hw1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.makhabatusen.kotlin1_hw1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var ui: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(ui.root)
        init()
    }

    private fun init() {
        ui.textViewSecond.text = intent?.getStringExtra(KEY_TEXT)
        ui.btnConfirmSecond.setOnClickListener {
            if (ui.editTextSecond.text.isNullOrBlank()) {
                Toast.makeText(this, getString(R.string.cant_be_empty), Toast.LENGTH_SHORT).show()
            } else {
                setResult(Activity.RESULT_OK, MainActivity.newIntent(this, ui.editTextSecond.text.toString()))
                finish()
            }
        }
    }

    companion object {
        private const val KEY_TEXT = "key_second"
        fun newIntent(
            context: Context?, message: String
        ): Intent? {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(KEY_TEXT, message)
            return intent
        }
    }
}