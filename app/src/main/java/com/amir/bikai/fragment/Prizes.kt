package com.amir.bikai.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amir.bikai.R
import com.amir.bikai.activity.PrizeWinners
import com.amir.bikai.adapter.PrizesAdapter
import com.amir.bikai.core.BaseFragment
import com.amir.bikai.interfaces.ApiListener
import com.amir.bikai.interfaces.OnItemCLick
import com.amir.bikai.model.PrizeModel
import com.amir.bikai.viewModel.PrizesViewModel
import kotlinx.android.synthetic.main.fragment_prizes.*
import java.io.Serializable
import java.time.Year
import java.util.ArrayList


class Prizes : BaseFragment(),OnItemCLick,ApiListener,View.OnClickListener {



    private lateinit var prizesAdapter :PrizesAdapter
    private var prizes= ArrayList<PrizeModel.Prize>()
    private var prizesCopy = ArrayList<PrizeModel.Prize>()
    private var ctg= ArrayList<String>()

    private lateinit var prizesViewModel:PrizesViewModel
    private var filter = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prizes, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // loader = loaderView.rootView as ConstraintLayout?

        implementListener()
        setEmptyAdapter()
        getData()
    }

    fun implementListener(){
        imgFilter.setOnClickListener(this)
    }
    fun setEmptyAdapter(){
        layoutManager = LinearLayoutManager(mContext)
        rv.layoutManager = layoutManager

        prizesAdapter = PrizesAdapter(this,prizes)
        rv.adapter = prizesAdapter
    }

    fun getData(){
        showingLoader()
        prizesViewModel = ViewModelProvider(this).get(PrizesViewModel::class.java)
       // prizesViewModel.getListener(this)

        prizesViewModel.getPrizes().observe(this,
            Observer<List<PrizeModel.Prize>> { results ->
                prizes.addAll(results)
                prizesCopy.addAll(results)

                for (i in 0 until prizes.size) {
                    prizes[i].category?.let { ctg.add(it) }
                }


                prizesAdapter?.notifyDataSetChanged()
                hidingLoader()
            })

    }

    override fun onItemClick(position: Int, type: Int) {
        val intent = Intent(mContext, PrizeWinners::class.java)
        intent.putExtra("winners",prizes.get(position) as Serializable)
        startActivity(intent)

    }

    override fun onStarting() {
      //  showLoader()
    }

    override fun onSucces() {
     //   hideLoader()
    }

    override fun onFailure(message: String) {
       // hideLoader()
        showToast(message)
    }

    override fun onClick(v: View?) {
        if (v== imgFilter){
            if (!filter){
                filter = true
                dialogSelectImage()
            }
        }
    }
    fun dialogSelectImage() {
        val dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (dialog.window != null)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.diaolog_filter)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()

        val btnApply: Button
        val tvCancel: TextView
        val spnCategory: Spinner
        val spnYear : Spinner

        btnApply = dialog.findViewById(R.id.btnApply)
        tvCancel = dialog.findViewById(R.id.tvBack)
        spnCategory = dialog.findViewById(R.id.spnCategory)
        spnYear = dialog.findViewById(R.id.spnYear)

         val years = ArrayList<String>()

        for (i in 1900..2018) {
            years.add(i.toString())
        }
        val aa = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, years)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spnYear) {
            adapter = aa
            setSelection(0, true)
            prompt = "Select year"
            setPopupBackgroundResource(R.color.white)
        }

        val category = ctg.distinct()

        val arrayAdapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, category)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spnCategory) {
            adapter = arrayAdapter
            setSelection(0, true)
            prompt = "Select Category"
            setPopupBackgroundResource(R.color.white)

        }
        btnApply.setOnClickListener {
            setFilter(spnYear.selectedItem.toString(),spnCategory.selectedItem.toString())
            dialog.dismiss()
            filter = false
        }

        tvCancel.setOnClickListener {
            dialog.dismiss()
            filter = false
        }

    }


    fun setFilter(year: String, category: String) {
        prizes.clear()
        for (i in 0 until prizesCopy.size) {
            if(prizesCopy[i].category.equals(category) && prizesCopy[i].year.equals(year)){
               prizes.add(prizesCopy[i])
            }
        }
        prizesAdapter.notifyDataSetChanged()
    }

    fun showingLoader(){
        progressLoader.visibility = View.VISIBLE
    }

    fun hidingLoader(){
       progressLoader.visibility = View.GONE
    }
}
