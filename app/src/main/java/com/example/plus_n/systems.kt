package com.example.plus_n

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.plus_n.Start_page.generateRandomExcludingValues
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [systems.newInstance] factory method to
 * create an instance of this fragment.
 */
class systems : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var view: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_systems, container, false)
        val btnBacketohome = view.findViewById<Button>(R.id.btnBacktohome)
        val resetButton= view.findViewById<Button>(R.id.Reset)
        val redo = view.findViewById<Button>(R.id.redoIcon)
        resetButton.setOnClickListener {
            recolor()
            selectedButtons.clear()
            rerandomizeValue()
        }
        btnBacketohome.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navSecond)
        }
        redo.setOnClickListener {
            recolor()
        }
        rerandomizeValue()
        return view

    }

    private val selectedButtons = mutableListOf<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private  fun rerandomizeValue(){
        val random = Random.Default
        var la = IntArray(2) { random.nextInt(2) }
        var lb=IntArray(2) { random.nextInt(2) }
        val n = random.nextInt(6,50)
        val a = random.nextInt(0,n)
        val b=n-a

        while(la[0]==lb[0]&&la[1]==lb[1])
        {
            lb=IntArray(2) { random.nextInt(2) }

        }
        var arr: Array<Array<Int>> = Array(2) { Array(2) { 0 } }
        var exclude = setOf<Int>(a,b)
        for(i in 0..1)
        {
            for(j in 0..1)
            {
                if(la[0]==i&&la[1]==j)
                {
                    arr[i][j]=a
                }
                else if(lb[0]==i&&lb[1]==j)
                {
                    arr[i][j]=b
                }
                else
                {
                    arr[i][j]= generateRandomExcludingValues(0, n, exclude)
                    exclude += arr[i][j]
                }
            }
        }
        val show =view.findViewById<TextView>(R.id.nvalue)
        val key00 = view.findViewById<Button>(R.id.key00)
        val key01= view.findViewById<Button>(R.id.key01)
        val key10= view.findViewById<Button>(R.id.key10)
        val key11= view.findViewById<Button>(R.id.key11)

        show.text = n.toString()
        key00.text = arr[0][0].toString()
        key01.text = arr[0][1].toString()
        key10.text = arr[1][0].toString()
        key11.text = arr[1][1].toString()



        val result:TextView =view.findViewById<TextView>(R.id.Result)
        result.text="Waiting"
        setClickListener(key00, result,n)
        setClickListener(key01, result,n)
        setClickListener(key10, result,n)
        setClickListener(key11, result,n)
    }

    private fun setClickListener(button: Button, resultTextView: TextView, n:Int) {
        button.setOnClickListener {
            // Toggle button selection (change color)
            toggleButtonSelection(button)

            // Check if two buttons are selected
            if (selectedButtons.size == 2) {
                // Calculate the sum of the selected buttons' values
                val sum = selectedButtons.sumBy { it.text.toString().toInt() }

                // Display the result
                resultTextView.text = if (sum == n)
                {
                    "Correct!"
                } else
                {
                    "Incorrect"
                }
                if(sum == n)
                {
                    selectedButtons[0].setBackgroundResource(R.drawable.custom_buttoncorrect)
                    selectedButtons[1].setBackgroundResource(R.drawable.custom_buttoncorrect)
                }
                else
                {
                    selectedButtons[0].setBackgroundResource(R.drawable.custom_buttonuncorrect)
                    selectedButtons[1].setBackgroundResource(R.drawable.custom_buttonuncorrect)
                }

            }
        }
    }
    private fun toggleButtonSelection(button: Button) {
        if (button in selectedButtons) {
            // Button is already selected, unselect it (change color back to default)
            selectedButtons.remove(button)
            button.setBackgroundResource(R.drawable.custom_buttonslice)// Light gray
        } else {
            // Button is not selected, select it (change color to indicate selection)
            selectedButtons.add(button)
            button.setBackgroundResource(R.drawable.custom_buttonclicked) // Green
        }
    }

    fun recolor()
    {
        for(i in selectedButtons)
        {
            i.setBackgroundResource(R.drawable.custom_buttonslice)
        }
        val result:TextView =view.findViewById<TextView>(R.id.Result)
        result.text="Waiting"
        selectedButtons.clear()
    }

    fun generateRandomExcludingValues(min: Int, max: Int, excludedValues: Set<Int>): Int {
        val random = Random.Default
        var randomValue: Int

        do {
            randomValue = random.nextInt(min, max)
        } while (randomValue in excludedValues)

        return randomValue
    }
}