package com.ubaya.pentolsilang_160719015

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.s160719015_movieapp.HistoryAdapter
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
//    companion object {
//        val EXTRA_PLAYERO_NAME = "EXTRA_PLAYERO_NAME"
//        val EXTRA_PLAYERO_COLOR = "EXTRA_PLAYERO_COLOR"
//        val EXTRA_PLAYERX_NAME = "EXTRA_PLAYERX_NAME"
//        val EXTRA_PLAYERX_COLOR = "EXTRA_PLAYERX_COLOR"
//        val EXTRA_WINNER = "EXTRA_WINNER"
//
//    }

//    val playerOName = intent.getStringExtra(ResultActivity.EXTRA_PLAYERO_NAME).toString()
//    val playerOColor = intent.getStringExtra(ResultActivity.EXTRA_PLAYERO_COLOR).toString()
//    val playerXName = intent.getStringExtra(ResultActivity.EXTRA_PLAYERX_NAME).toString()
//    val playerXColor = intent.getStringExtra(ResultActivity.EXTRA_PLAYERX_COLOR).toString()
//    val winner = intent.getIntExtra(ResultActivity.EXTRA_WINNER, 0)

    override fun onBackPressed() {
        var intent = Intent(this, PlayersActivity::class.java)
//        intent.putExtra(EXTRA_PLAYERO_NAME, playerOName)
//        intent.putExtra(EXTRA_PLAYERO_COLOR, playerOColor)
//        intent.putExtra(EXTRA_PLAYERX_NAME, playerXName)
//        intent.putExtra(EXTRA_PLAYERX_COLOR, playerXColor)
//        intent.putExtra(EXTRA_WINNER, winner)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        //Setup the recyclerView using the Adapter

        //linear
        val lm = LinearLayoutManager(this)

        with(recyclerView) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = HistoryAdapter(this@HistoryActivity)
        }

    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }
}