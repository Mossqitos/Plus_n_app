package com.example.plus_n.System

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plus_n.R

class RankingAdapter(private val players: MutableList<Pair<String,Int>>) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameList)
        val scoreTextView: TextView = itemView.findViewById(R.id.scoreList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rankinglist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = players[position]

        // Customize styling for name and score as needed
        holder.nameTextView.text = player.first
        holder.scoreTextView.text = player.second.toString()
    }

    override fun getItemCount(): Int {
        return players.size
    }
}
