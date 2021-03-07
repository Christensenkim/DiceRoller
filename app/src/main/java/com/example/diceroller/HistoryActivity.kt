package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.list_history.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_history)

        var diceHistory = ArrayList<String>()

        diceHistory = intent.getStringArrayListExtra("List") as ArrayList<String>

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, diceHistory)

        val listView: ListView = findViewById<ListView>(R.id.listviewHistory)

        if (listView != null) {
            listView.adapter = adapter
        }
    }
}
