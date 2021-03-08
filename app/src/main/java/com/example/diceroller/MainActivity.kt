package com.example.diceroller

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList

const val HISTORY_LIST = "com.example.diceroller.MESSAGE"

class MainActivity : AppCompatActivity() {

    val diceIds = arrayOf(0, R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                                R.drawable.dice4, R.drawable.dice5, R.drawable.dice6 )

    val diceResults: ArrayList<Int> = ArrayList()
    val diceHistory = ArrayList<String>()
    private val mHistory = ArrayList<String>()
    val recentRoll = ArrayList<Int>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val orientation = this.getResources().getConfiguration().orientation
        val message = if (orientation == Configuration.ORIENTATION_PORTRAIT) "Portrait" else "Landscape"
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()

        val dices = resources.getIntArray(R.array.numberOfDices)
        val spinner: Spinner = findViewById<Spinner>(R.id.planets_spinner)
        if (spinner != null){
            val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, dices.toList())
            spinner.adapter = adapter
        }

        if (savedInstanceState != null)
        {
            val history = savedInstanceState.getStringArrayList("historyListRolls") as ArrayList<String>
            history.forEach { p -> mHistory.add(p) }
            val historyRoll = savedInstanceState.getIntegerArrayList("historyRecentRoll") as ArrayList<Int>
            historyRoll.forEach { i -> recentRoll.add(i) }
            if (recentRoll.size > 0) {
                for (i in 0..recentRoll.size) {
                    changeDiceImage(i, recentRoll[i])
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateHistory() {
        var time = LocalTime.now().toString()
        diceHistory.add(time + diceResults.toString())
        println(diceHistory.toString())
        diceResults.clear()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickRoll(v: View){

        val spinner: Spinner = findViewById(R.id.planets_spinner)
        var number = 0

        for (i in 1..spinner.selectedItem as Int){
            number = Dice(6).roll()
            changeDiceImage(i, number)
            recentRoll.add(number)
        }
        updateHistory()
    }

    private fun changeDiceImage(i: Int, number: Int) {
        if (i == 1) {
            imgDice1.setImageResource(diceIds[number])
            diceResults.add(number)
        }
        if (i == 2) {
            imgDice2.setImageResource(diceIds[number])
            diceResults.add(number)
        }
        if (i == 3) {
            imgDice3.setImageResource(diceIds[number])
            diceResults.add(number)
        }
        if (i == 4) {
            imgDice4.setImageResource(diceIds[number])
            diceResults.add(number)
        }
        if (i == 5) {
            imgDice5.setImageResource(diceIds[number])
            diceResults.add(number)
        }
        if (i == 6) {
            imgDice6.setImageResource(diceIds[number])
            diceResults.add(number)
        }
    }

    fun onClickHistory(v: View){
        val i = Intent(this, HistoryActivity::class.java)
        i.putStringArrayListExtra("List", diceHistory)
        startActivity(i)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList("historyListRolls", mHistory)
        outState.putIntegerArrayList("historyRecentRoll", recentRoll)
        super.onSaveInstanceState(outState)
    }
}

class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}