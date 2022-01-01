package com.ubaya.pentolsilang_160719015

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_players.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    companion object {
        val EXTRA_PLAYERO_NAME = "EXTRA_PLAYERO_NAME"
        val EXTRA_PLAYERO_COLOR = "EXTRA_PLAYERO_COLOR"
        val EXTRA_PLAYERX_NAME = "EXTRA_PLAYERX_NAME"
        val EXTRA_PLAYERX_COLOR = "EXTRA_PLAYERX_COLOR"
        val EXTRA_WINNER = "EXTRA_WINNER"

    }

    var colorO=0
    var colorX=0

    //Quit Game
    override fun onBackPressed() {
        var intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val playerOName = intent.getStringExtra(MainActivity.EXTRA_PLAYERO_NAME).toString()
        val playerOColor = intent.getStringExtra(MainActivity.EXTRA_PLAYERO_COLOR).toString()
        val playerXName = intent.getStringExtra(MainActivity.EXTRA_PLAYERX_NAME).toString()
        val playerXColor = intent.getStringExtra(MainActivity.EXTRA_PLAYERX_COLOR).toString()
        val winner = intent.getIntExtra(MainActivity.EXTRA_WINNER, 0)

        colorO =
            if (playerOColor == "Red") {
                R.color.red
            } else if (playerOColor == "Green") {
                R.color.green
            } else if (playerOColor == "Blue") {
                R.color.blue
            } else {
                R.color.purple
            }

        colorX =
            if (playerXColor == "Red") {
                R.color.red
            } else if (playerXColor == "Green") {
                R.color.green
            } else if (playerXColor == "Blue") {
                R.color.blue
            } else {
                R.color.purple
            }

        cardViewX2.setBackgroundColor(ContextCompat.getColor(this, colorX))
        cardViewO2.setBackgroundColor(ContextCompat.getColor(this, colorO))

        textViewPlayerX2.setText("$playerXName (X)")
        textViewPlayerO2.setText("$playerOName (O)")

        if(winner==0){
            textViewCardO2.setText("Draw")
            textViewCardX2.setText("Draw")
        }else if(winner==1){
            textViewCardO2.setText("You win")
            textViewCardX2.setText("You lose")
        }else{
            textViewCardO2.setText("You lose")
            textViewCardX2.setText("You win")
        }

        buttonHistory.setOnClickListener(){
            var intent2 = Intent(this, HistoryActivity::class.java)
//            intent2.putExtra(EXTRA_PLAYERO_NAME, playerOName)
//            intent2.putExtra(EXTRA_PLAYERO_COLOR, playerOColor)
//            intent2.putExtra(EXTRA_PLAYERX_NAME, playerXName)
//            intent2.putExtra(EXTRA_PLAYERX_COLOR, playerXColor)
//            intent2.putExtra(EXTRA_WINNER, winner)
            startActivity(intent2)
        }

        buttonPlayAgain.setOnClickListener(){
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_PLAYERO_NAME, playerOName)
            intent.putExtra(EXTRA_PLAYERO_COLOR, playerOColor)
            intent.putExtra(EXTRA_PLAYERX_NAME, playerXName)
            intent.putExtra(EXTRA_PLAYERX_COLOR, playerXColor)
            startActivity(intent)
        }
    }
}