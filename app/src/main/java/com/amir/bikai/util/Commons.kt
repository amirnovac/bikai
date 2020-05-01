package com.amir.bikai.util

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.amir.bikai.R

object Commons {

    fun setToolbarWithBackAndTitle(
        ctx: Context,
        title: String,
        value: Boolean?,
        backResource: Int
    ) {
        val toolbar = (ctx as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)
        ctx.setSupportActionBar(toolbar)
        val title_tv = toolbar.findViewById<TextView>(R.id.title_tv)
        val actionBar = ctx.supportActionBar
        if (actionBar != null) {
            if (backResource != 0) {
                toolbar.setNavigationIcon(backResource)
                toolbar.setNavigationOnClickListener(View.OnClickListener { ctx.onBackPressed() })
            }

            actionBar.setDisplayShowHomeEnabled(value!!)
            actionBar.setDisplayShowTitleEnabled(false)
            title_tv.setText(title)
        }
    }
}