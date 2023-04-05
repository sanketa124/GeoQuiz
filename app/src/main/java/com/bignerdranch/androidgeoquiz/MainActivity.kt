package com.bignerdranch.androidgeoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.bignerdranch.androidgeoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var coordinator: CoordinatorLayout

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var currentScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(Companion.TAG, "onCreate(Bundle?) called")

        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Gor a QuizViewModel: $quizViewModel")

        trueButton = findViewById<Button>(R.id.true_button)
        falseButton = findViewById<Button>(R.id.false_button)
        coordinator = findViewById<CoordinatorLayout>(R.id.layout)

        binding.trueButton.setOnClickListener{
            //Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
            updateScore(true)
            checkAnswer(true)
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        }

        binding.falseButton.setOnClickListener{
            updateScore(false)
            checkAnswer(false)
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
            //val snackbar = Snackbar.make(coordinator, "Did Something", Snackbar.LENGTH_LONG)
            //snackbar.show()
            //Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }

        binding.nextButton.setOnClickListener{
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
            if(currentIndex == questionBank.size - 1) {
                val currentPercentage = String.format("%.3f", ((currentScore.toDouble()/6) * 100.0)).toDouble()
                Toast.makeText(this, "The Total Percentage Score is $currentPercentage%", Toast.LENGTH_SHORT).show()
                currentScore = 0
            }
            //currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.cheatButton.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = Intent(this, CheatActivity::class.java)
            //val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivity(intent)
        }

        binding.previousButton.setOnClickListener{
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
            if(currentIndex == 0) {
                currentScore = 0
                currentIndex = questionBank.size
            }
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }

        binding.questionTextView.setOnClickListener{
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
            if(currentIndex == questionBank.size - 1) {
                val currentPercentage = String.format("%.3f", ((currentScore.toDouble()/6) * 100.0)).toDouble()
                Toast.makeText(this, "The Total Percentage Score is $currentPercentage%", Toast.LENGTH_SHORT).show()
                currentScore = 0
            }
            //currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()

    }

    override fun onStart(){
        super.onStart()
        Log.d(Companion.TAG, "onStart(Bundle?) called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(Companion.TAG, "onResume(Bundle?) called")
    }

    override fun onPause(){
        super.onPause()
        Log.d(Companion.TAG, "onPause(Bundle?) called")
    }

    override fun onStop(){
        super.onStop()
        Log.d(Companion.TAG, "onStop(Bundle?) called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Companion.TAG, "onDestroy(Bundle?) called")
    }

    private fun updateQuestion(){
        //val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun updateScore(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if(userAnswer == correctAnswer) {
            currentScore += 1
        }else{

        }

    }

    private fun checkAnswer(userAnswer: Boolean) {
        //val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if(userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

    }

    companion object {
        private const val TAG = "MainActivity"
    }

}