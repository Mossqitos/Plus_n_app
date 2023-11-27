package com.example.plus_n.Start_page

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.plus_n.R
import kotlin.random.Random as Random

class GameActivity : FragmentActivity(){

}

fun generateRandomExcludingValues(min: Int, max: Int, excludedValues: Set<Int>): Int {
    val random = Random.Default
    var randomValue: Int

    do {
        randomValue = random.nextInt(min, max)
    } while (randomValue in excludedValues)

    return randomValue
}

