package com.example.plus_n


import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.plus_n.Start_page.generateRandomExcludingValues
import kotlin.random.Random


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class systems : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var view: View
    var Score: Int = 0
    private var timeprogress=0
    private var timeCountDown: CountDownTimer?=null
    private var timeSelected : Int =0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         view= inflater.inflate(R.layout.fragment_systems, container, false)
        val btnBacketohome = view.findViewById<Button>(R.id.btnBacktohome)
        val resetButton= view.findViewById<Button>(R.id.redoIcon)
        val rerandom = view.findViewById<Button>(R.id.reRandom)
        val redo = view.findViewById<Button>(R.id.redoIcon)
        Score=0
        resetButton.setOnClickListener {
            recolor()
            selectedButtons.clear()
            val hiddenpage = view.findViewById<FrameLayout>(R.id.hiddenLayout)
            val btnReset = view.findViewById<Button>(R.id.redoIcon)
            hiddenpage.visibility = View.GONE
            btnReset.visibility = View.GONE
            rerandomizeValue()
        }
        rerandom.setOnClickListener {

            recolor()
            selectedButtons.clear()
            val hiddenpage = view.findViewById<FrameLayout>(R.id.hiddenLayout)
            val btnReset = view.findViewById<Button>(R.id.redoIcon)
            hiddenpage.visibility = View.GONE
            btnReset.visibility = View.GONE
            rerandomizeValue()
        }
        btnBacketohome.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navSecond)
        }
        redo.setOnClickListener {
            recolor()
            val hiddenpage = view.findViewById<FrameLayout>(R.id.hiddenLayout)
            val btnReset = view.findViewById<Button>(R.id.redoIcon)
            hiddenpage.visibility = View.GONE
            btnReset.visibility = View.GONE
        }
        rerandomizeValue()
        timeSelected=30
        startTimer(timeSelected)
        return view

    }
    private fun startTimer(time:Int)
    {
        val progressBar = view.findViewById<ProgressBar>(R.id.TimerIcon)
        progressBar.progress=timeprogress
        timeCountDown=object :CountDownTimer(
            (time*1000).toLong(),1000)
        {
            override fun onTick(p0: Long) {
                timeprogress++
                progressBar.progress=time-timeprogress
                val timeLeftTv:TextView = view.findViewById(R.id.Point)
                timeLeftTv.text = (time - timeprogress).toString()
            }

            override fun onFinish() {
                val result:TextView =view.findViewById<TextView>(R.id.Result)
                result.text = "Stop"
            }
        }.start()
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
        val hiddenpage = view.findViewById<FrameLayout>(R.id.hiddenLayout)
        val btnReset = view.findViewById<Button>(R.id.redoIcon)
        hiddenpage.visibility = View.GONE
        btnReset.visibility = View.GONE

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

        val scoreview:TextView = view.findViewById<TextView>(R.id.Score)
        scoreview.text=Score.toString()

        val result:TextView =view.findViewById<TextView>(R.id.Result)
        result.text="Waiting"
        setClickListener(key00, result,n)
        setClickListener(key01, result,n)
        setClickListener(key10, result,n)
        setClickListener(key11, result,n)
    }

    private fun setClickListener(button: Button, resultTextView: TextView, n:Int) {
        button.setOnClickListener {
            toggleButtonSelection(button)

            if (selectedButtons.size == 2) {
                val sum = selectedButtons.sumBy { it.text.toString().toInt() }

                resultTextView.text = if (sum == n)
                {
                    "Correct!"
                } else
                {
                    "Incorrect"
                }
                if(sum == n)
                {
                    val hiddenpage = view.findViewById<FrameLayout>(R.id.hiddenLayout)
                    val btnReset = view.findViewById<Button>(R.id.redoIcon)
                    hiddenpage.visibility = View.VISIBLE
                    btnReset.visibility = View.GONE
                    selectedButtons[0].setBackgroundResource(R.drawable.custom_buttoncorrect)
                    selectedButtons[1].setBackgroundResource(R.drawable.custom_buttoncorrect)
                    Score++;
                    val scoreview:TextView = view.findViewById<TextView>(R.id.Score)
                    scoreview.text=Score.toString()

                }
                else
                {
                    val hiddenpage = view.findViewById<FrameLayout>(R.id.hiddenLayout)
                    val btnReset = view.findViewById<Button>(R.id.redoIcon)
                    hiddenpage.visibility = View.VISIBLE
                    btnReset.visibility = View.VISIBLE
                    selectedButtons[0].setBackgroundResource(R.drawable.custom_buttonuncorrect)
                    selectedButtons[1].setBackgroundResource(R.drawable.custom_buttonuncorrect)

                }

            }
        }
    }
    private fun toggleButtonSelection(button: Button) {
        if (button in selectedButtons) {

            selectedButtons.remove(button)
            button.setBackgroundResource(R.drawable.custom_buttonslice)
        } else {

            selectedButtons.add(button)
            button.setBackgroundResource(R.drawable.custom_buttonclicked)
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