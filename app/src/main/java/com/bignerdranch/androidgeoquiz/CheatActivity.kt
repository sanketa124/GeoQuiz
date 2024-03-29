package com.bignerdranch.androidgeoquiz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.bignerdranch.androidgeoquiz.databinding.ActivityMainBinding

private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private var answerIsTrue = false
    private lateinit var showAnswer: Button
    private lateinit var answerTextView: TextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showAnswer = findViewById<Button>(R.id.show_answer_button)
        answerTextView  = findViewById<TextView>(R.id.answer_text_view)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        showAnswer.setOnClickListener{
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding. q
        }
    }

    companion object {
        fun newIntent (packageContext: Context, answerIsTrue: Boolean): Intent
        {
            return Intent(packageContext, CheatActivity::class.java).apply{
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}