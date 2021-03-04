package com.example.diceroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

const val HISTORY_LIST = "com.example.diceroller.MESSAGE"

class MainActivity : AppCompatActivity() {


    val mGenerator = Random()

    val diceIds = arrayOf(0, R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                                R.drawable.dice4, R.drawable.dice5, R.drawable.dice6 )

    val mHistory = mutableListOf<Pair<Int, Int>>()
    val hi = "Hello World"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickRoll(v: View){
        val d1 = mGenerator.nextInt(6) + 1
        val d2 = mGenerator.nextInt(6) + 1

        imgDice1.setImageResource(diceIds[d1])
        imgDice2.setImageResource(diceIds[d2])

        if (mHistory.size >= 5)
            mHistory.removeAt(0)
        mHistory.add(Pair(d1, d2))
        updateHistory()
    }

    private fun updateHistory(){
        var s = ""
        mHistory.forEach{ p -> val e1 = p.first; val e2 = p.second
                                    s += "$e1 - $e2\n"}
        tvHistory.text = s
    }



    fun onClickClear(v: View){
        val i = Intent(this, HistoryActivity::class.java)
        intent.putExtra("HISTORY_LIST", hi)
        startActivity(i)
    }

}