package com.ubaya.pentolsilang_160719015

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_players.*
import java.text.SimpleDateFormat
import java.util.*

class PlayersActivity : AppCompatActivity() {
    companion object {
        val EXTRA_PLAYERO_NAME = "EXTRA_PLAYERO_NAME"
        val EXTRA_PLAYERO_COLOR = "EXTRA_PLAYERO_COLOR"
        val EXTRA_PLAYERX_NAME = "EXTRA_PLAYERX_NAME"
        val EXTRA_PLAYERX_COLOR = "EXTRA_PLAYERX_COLOR"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)

        //Create Adapter for the Spinner
        val adapter = ArrayAdapter(this, R.layout.myspinner_layout, GlobalData.color)
        adapter.setDropDownViewResource(R.layout.myspinner_item_layout)
        spinnerColorO.adapter = adapter
        spinnerColorX.adapter = adapter

        buttonStart.setOnClickListener() {

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_PLAYERO_NAME, editTextPlayerO.text.toString())
            intent.putExtra(EXTRA_PLAYERO_COLOR, spinnerColorO.selectedItem.toString())
            intent.putExtra(EXTRA_PLAYERX_NAME, editTextPlayerX.text.toString())
            intent.putExtra(EXTRA_PLAYERX_COLOR, spinnerColorX.selectedItem.toString())

            startActivity(intent)
        }

    }

}