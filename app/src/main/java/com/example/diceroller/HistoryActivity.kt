package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.list_history.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_history)
        val whatever = intent.getStringExtra("HISTORY_LIST")
        tvHistoryHeader.text = whatever
    }
}
