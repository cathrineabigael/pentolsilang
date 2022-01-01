package com.ubaya.pentolsilang_160719015

import android.content.Intent
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val playerO = 1
    val playerX = 2
    var activePlayer = playerO
    lateinit var playerOName: String
    lateinit var playerXName: String
    var iconO = 1
    var iconX = 1
    var playerOColor = ""
    var playerXColor = ""
    var colorO = 0
    var colorX = 0
    lateinit var filledPositions: IntArray
    var gameActive = true

    var date = SimpleDateFormat("dd MMMM yyyy kk:mm")
    var currentTime = Calendar.getInstance().getTime()
    var dateNow = date.format(currentTime.time)

    var win = 0
    //0 for draw, 1 for playerO, 2 for playerX

    companion object {
        val EXTRA_PLAYERO_NAME = "EXTRA_PLAYERO_NAME"
        val EXTRA_PLAYERO_COLOR = "EXTRA_PLAYERO_COLOR"
        val EXTRA_PLAYERX_NAME = "EXTRA_PLAYERX_NAME"
        val EXTRA_PLAYERX_COLOR = "EXTRA_PLAYERX_COLOR"
        val EXTRA_WINNER = "EXTRA_WINNER"
    }

    //Quit Game
    override fun onBackPressed() {
        AlertDialog.Builder(this).setMessage("Are you sure want to quit the game")
            .setTitle("Quit Game")
            .setPositiveButton(
                "Quit", DialogInterface.OnClickListener { dialog, which ->
                    var intent = Intent(this, PlayersActivity::class.java)
                    startActivity(intent)
                }
            )
            .setNegativeButton("Keep playing", null)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filledPositions = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

        playerOName = intent.getStringExtra(PlayersActivity.EXTRA_PLAYERO_NAME).toString()
        playerOColor = intent.getStringExtra(PlayersActivity.EXTRA_PLAYERO_COLOR).toString()
        playerXName = intent.getStringExtra(PlayersActivity.EXTRA_PLAYERX_NAME).toString()
        playerXColor = intent.getStringExtra(PlayersActivity.EXTRA_PLAYERX_COLOR).toString()

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

        cardViewX.setBackgroundColor(ContextCompat.getColor(this, colorX))
        cardViewO.setBackgroundColor(ContextCompat.getColor(this, colorO))
        textViewPlayerX.setText("$playerXName (X)")
        textViewPlayerO.setText("$playerOName (O)")
        textViewCardX.setText("$playerOName's turn")
        textViewCardO.setText("Your turn")

        iconO = if (playerOColor == "Red") {
            R.drawable.o_red
        } else if (playerOColor == "Green") {
            R.drawable.o_green
        } else if (playerOColor == "Blue") {
            R.drawable.o_blue
        } else {
            R.drawable.o_purple
        }

        iconX = if (playerXColor == "Red") {
            R.drawable.x_red
        } else if (playerXColor == "Green") {
            R.drawable.x_green
        } else if (playerXColor == "Blue") {
            R.drawable.x_blue
        } else {
            R.drawable.x_purple
        }

        imageButton0.setOnClickListener() {
            clickedButton(imageButton0)

        }

        imageButton1.setOnClickListener() {
            clickedButton(imageButton1)

        }

        imageButton2.setOnClickListener() {
            clickedButton(imageButton2)

        }

        imageButton3.setOnClickListener() {
            clickedButton(imageButton3)

        }

        imageButton4.setOnClickListener() {
            clickedButton(imageButton4)

        }

        imageButton5.setOnClickListener() {
            clickedButton(imageButton5)

        }

        imageButton6.setOnClickListener() {
            clickedButton(imageButton6)

        }

        imageButton7.setOnClickListener() {
            clickedButton(imageButton7)

        }

        imageButton8.setOnClickListener() {
            clickedButton(imageButton8)

        }


    }

    fun checkActivePlayer() {
        if (activePlayer == playerO) {
            textViewCardX.setText("$playerOName's turn")
            textViewCardO.setText("Your turn")
        } else {
            textViewCardX.setText("Your turn")
            textViewCardO.setText("$playerXName's turn")
        }
    }

    private fun clickedButton(btn: ImageButton) {


        //kalau ternyata game sudah selesai
        if (!gameActive)
            return

        var btnTag = Integer.parseInt(btn.tag.toString())

        //kalau ternyata posisi itu sidah terisi ya ndak akan dilakukan apa2
        if (filledPositions[btnTag] != -1) {
            return
        }

        filledPositions[btnTag] = activePlayer

        if (activePlayer == playerO) {
            btn.setImageResource(iconO)
            activePlayer = playerX
            btn.isEnabled = false
        } else {
            btn.setImageResource(iconX)
            activePlayer = playerO
            btn.isEnabled = false
        }

        checkForWin()
        checkActivePlayer()
    }

    fun checkForWin() {
        var winPositions = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )

        for (i in 0 until winPositions.size) {
            var val0 = winPositions[i][0]
            var val1 = winPositions[i][1]
            var val2 = winPositions[i][2]

            //jika pada 3 index tersebut isinya sama
            if (filledPositions[val0] == filledPositions[val1] && filledPositions[val1] == filledPositions[val2]) {
                //cek dulu jangan2 isinya sama itu sama sama -1 (sama sama belum diisi)
                if (filledPositions[val0] != -1) {
                    //permainan dihentikan karena sudah ada pemenangnya
                    gameActive = false
                    if (filledPositions[val0] == playerO) {
                        win = 1
                        showMessage("win", "Congratulation, $playerOName win!")
//                        goToResult()
                    } else {
                        win = 2
                        showMessage("win", "Congratulation, $playerXName win!")
//                        goToResult()
                    }
                    return
                }
            }
        }

        //Cek seri
        var count = 0
        for (i in 0 until filledPositions.size) {
            //kalau ternyata masih ada yg kosong
            if (filledPositions[i] == -1) {
                count++
            }
        }
        if (count == 0) {
            showMessage("draw", "It's draw")
            goToResult()
            return

        }
    }

    fun showMessage(res: String, message: String) {
        AlertDialog.Builder(this).setMessage(message).setTitle("Game Over")
            .setPositiveButton(
                if (res == "win") {
                    "Hooray!"
                } else {
                    "Oh no..."
                }, DialogInterface.OnClickListener { dialog, which ->
                    goToResult()
                })
            .show()
    }

    fun goToResult() {
        addToArrayList()

        var intent2 = Intent(this, ResultActivity::class.java)
        intent2.putExtra(EXTRA_PLAYERO_NAME, playerOName)
        intent2.putExtra(EXTRA_PLAYERO_COLOR, playerOColor)
        intent2.putExtra(EXTRA_PLAYERX_NAME, playerXName)
        intent2.putExtra(EXTRA_PLAYERX_COLOR, playerXColor)
        intent2.putExtra(EXTRA_WINNER, win)
        startActivity(intent2)
    }

    fun addToArrayList() {
//        val playerO: String,
//        val playerX: String,
//        val colorO: Int,
//        val colorX: Int,
//        val winner: Int,
//        val date: String,
//        val gameSession: String

//        GlobalData.session.plus(1)


        var history =
            History(playerOName, playerXName, colorO, colorX, win, dateNow, GlobalData.session)
        GlobalData.history.add(history)
        GlobalData.session++
        finish()
    }
}



