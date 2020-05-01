package com.amir.bikai.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amir.bikai.R
import com.amir.bikai.interfaces.OnItemCLick
import com.amir.bikai.model.PrizeModel

class PrizesAdapter(val onItemCLick: OnItemCLick, val prize: ArrayList<PrizeModel.Prize>) :
    RecyclerView.Adapter<PrizesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_priz, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return prize.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {
            holder.tvYear.text = prize.get(position).year
            holder.tvCategory.text = prize.get(position).category
            holder.tvLaureates.text = "" + (prize.get(position).laureates?.size ?: 0) + "winners"

            if (prize.get(position).overallMotivation != null) {
                holder.tvOverallMotivation.text = prize.get(position).overallMotivation
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        holder.itemView.setOnClickListener(View.OnClickListener { onItemCLick.onItemClick(position,0) })
    }

    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val tvYear = view.findViewById<TextView>(R.id.tvYearValue)
        val tvCategory = view.findViewById<TextView>(R.id.tvCategoryValue)
        val tvOverallMotivation = view.findViewById<TextView>(R.id.tvMotivationValue)
        val tvLaureates = view.findViewById<TextView>(R.id.tvLaureatesValue)
    }
}