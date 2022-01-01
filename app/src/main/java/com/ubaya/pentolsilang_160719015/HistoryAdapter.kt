package com.ubaya.s160719015_movieapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.pentolsilang_160719015.GlobalData
import com.ubaya.pentolsilang_160719015.R
import kotlinx.android.synthetic.main.history_card.view.*
import java.util.zip.Inflater

class HistoryAdapter(val context: Context) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.history_card, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = GlobalData.history[position]
        with(holder.view) {
            textViewGame.text = "Game Session #"+history.gameSession
            textViewDate.text = history.date
            if (history.winner == 0) {
                textViewResO.text = history.playerO + "(O)"
                textViewResX.text = history.playerX + "(X)"
            } else if (history.winner == 1) {
                textViewResO.text = history.playerO + "(O) WIN"
                textViewResX.text = history.playerX + "(X)"
            } else {
                textViewResO.text = history.playerO + "(O)"
                textViewResX.text = history.playerX + "(X) WIN"
            }

            textViewResO.setBackgroundColor(ContextCompat.getColor(context, history.colorO))
            textViewResX.setBackgroundColor(ContextCompat.getColor(context, history.colorX))

        }
    }

    override fun getItemCount()=GlobalData.history.size
}