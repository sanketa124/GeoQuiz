package com.bignerdranch.androidgeoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var coordinator: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById<Button>(R.id.true_button)
        falseButton = findViewById<Button>(R.id.false_button)
        coordinator = findViewById<CoordinatorLayout>(R.id.layout)

        trueButton.setOnClickListener{
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }

        falseButton.setOnClickListener {
            val snackbar = Snackbar.make(coordinator, "Did Something", Snackbar.LENGTH_LONG)
            snackbar.show()
            //Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }

    }
}