package com.example.diceroller

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalTime
import kotlin.collections.ArrayList

const val HISTORY_LIST = "com.example.diceroller.MESSAGE"

class MainActivity : AppCompatActivity() {

    val diceIds = arrayOf(0, R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                                R.drawable.dice4, R.drawable.dice5, R.drawable.dice6 )

    val diceResults: MutableList<Int> = ArrayList()
    val diceHistory = mutableListOf<String>()

    val hi = "Hello World"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dices = resources.getIntArray(R.array.numberOfDices)
        val spinner: Spinner = findViewById<Spinner>(R.id.planets_spinner)
        if (spinner != null){
            val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, dices.toList())
            spinner.adapter = adapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickRoll(v: View){

        val spinner: Spinner = findViewById(R.id.planets_spinner)
        var number = 0

        for (i in 1..spinner.selectedItem as Int){
            number = Dice(6).roll()
            changeDiceImage(i, number)
        }
        var time = LocalTime.now().toString()

        diceHistory.add(time + diceResults.toString())
        println(diceHistory.toString())
        diceResults.clear()
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
        intent.putExtra("List", diceHistory.toString())
        startActivity(i)
    }

}

class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}