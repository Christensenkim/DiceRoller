package com.example.diceroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlin.collections.ArrayList

class HistoryActivity : AppCompatActivity() {

    var diceHistory = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_history)

        diceHistory = intent.getStringArrayListExtra("List") as ArrayList<String>

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, diceHistory)

        val listView: ListView = findViewById<ListView>(R.id.listviewHistory)

        if (listView != null) {
            listView.adapter = adapter
        }
    }

    fun onCLickBack(view: View) {
        //todo
    }


    fun onClickClearAndReturn(view: View) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}
