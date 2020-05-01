package com.amir.bikai.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.amir.bikai.R
import com.amir.bikai.adapter.PrizeWinnersAdapter
import com.amir.bikai.core.BaseActivity
import com.amir.bikai.model.PrizeModel
import com.amir.bikai.util.Commons
import kotlinx.android.synthetic.main.activity_prize_winners.*
import java.util.*

class PrizeWinners : BaseActivity() {


    private lateinit var prizesWinnerAdapter: PrizeWinnersAdapter
    private var winners = ArrayList<PrizeModel.Laureate>()
    private lateinit var prize: PrizeModel.Prize

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prize_winners)

        Commons.setToolbarWithBackAndTitle(
            mContext,
            "Laureates",
            true,
            R.drawable.ic_chevron_left_black_24dp
        )

        setEmptyAdapter()
        getData()
        setData()
    }

    fun setEmptyAdapter() {
        layoutManager = LinearLayoutManager(mContext)
        rvSpcl.layoutManager = layoutManager

        prizesWinnerAdapter = PrizeWinnersAdapter(winners)
        rvSpcl.adapter = prizesWinnerAdapter
    }

    fun getData() {
        prize = intent.getSerializableExtra("winners") as PrizeModel.Prize
    }

    fun setData() {
        prize.laureates?.let { winners.addAll(it) }

        prizesWinnerAdapter.notifyDataSetChanged()
    }


}
