package com.example.plus_n

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plus_n.System.RankingAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class homepage : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_homepage, container, false)
        val showMaxscore = view.findViewById<TextView>(R.id.maxScore)
        val rankingScore = view.findViewById<Button>(R.id.btnRanking)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvRankingList)
        val closeRanking = view.findViewById<Button>(R.id.closeRankingpage)
        val rankingPage = view.findViewById<FrameLayout>(R.id.rankingPage)
        showMaxscore.text=MaxScore.toString()
        rankingScore.setOnClickListener {
            listScore.sortByDescending { it.second }
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = RankingAdapter(listScore)
            rankingPage.visibility = View.VISIBLE
        }
        closeRanking.setOnClickListener {
            rankingPage.visibility = View.GONE
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnStart).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.firstnav)
        }
    }



}