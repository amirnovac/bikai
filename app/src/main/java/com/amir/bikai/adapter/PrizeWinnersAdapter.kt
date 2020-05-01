package com.amir.bikai.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amir.bikai.R
import com.amir.bikai.model.PrizeModel
import java.lang.Exception

class PrizeWinnersAdapter(val winners: ArrayList<PrizeModel.Laureate>) :
    RecyclerView.Adapter<PrizeWinnersAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_prize_winners, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return winners.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        try {
            holder.tvFname.text = winners.get(position).firstname
            holder.tvLname.text = winners.get(position).surname
            holder.tvMotivation.text = winners.get(position).motivation
        }
        catch (ex:Exception){
            ex.printStackTrace()
        }
    }


    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val tvFname = view.findViewById<TextView>(R.id.tvFnameValue)
        val tvLname = view.findViewById<TextView>(R.id.tvLNameValue)
        val tvMotivation = view.findViewById<TextView>(R.id.tvMotiveValue)

    }
}