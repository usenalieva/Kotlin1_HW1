package com.makhabatusen.kotlin1_hw1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.makhabatusen.kotlin1_hw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding
    private lateinit var activityForResult: ActivityResultLauncher<Intent>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        init()
    }

    private fun init() {

        activityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK)  {
                val intent = it.data
                ui.textViewMain.text = intent?.getStringExtra(KEY_TEXT_MAIN)
            }
        }

        ui.btnConfirmMain.setOnClickListener {
            if (ui.editTextMain.text.isNullOrBlank()) {
                Toast.makeText(this, getString(R.string.cant_be_empty), Toast.LENGTH_SHORT).show()
            } else {
               activityForResult.launch(SecondActivity.newIntent(this, ui.editTextMain.text.toString()))
            }
        }
    }

    companion object {
        private const val KEY_TEXT_MAIN: String = "key_main"
        fun newIntent(
            context: Context?, message: String
        ): Intent? {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(KEY_TEXT_MAIN, message)
            return intent
        }
    }
}