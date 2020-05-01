package com.amir.bikai.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.amir.bikai.R
import com.amir.bikai.core.BaseActivity
import com.amir.bikai.fragment.Prizes
import com.amir.bikai.fragment.Special
import com.amir.bikai.util.Commons
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTab()
        setTabSelectedListner()
        switchFragment(Prizes())
        setToolbar("Prizes")
    }

    private fun setUpTab() {
        setCustomTabView(R.drawable.prizeselector)
        setCustomTabView(R.drawable.special_selector)
    }

    private fun setCustomTabView(icon: Int) {
        val customView = LayoutInflater.from(mContext).inflate(
            R.layout.item_custom_tab,
            null
        ) as LinearLayout
        (customView.findViewById(R.id.ivIcon) as ImageView).setImageResource(icon)

        tabs.addTab(tabs.newTab().setCustomView(customView))
    }

    private fun switchFragment(fragment: Fragment?) {
        if (fragment == null)
            return
        else if (fragment === supportFragmentManager.findFragmentById(flContainer.id)) {
            return
        }
        supportFragmentManager.beginTransaction().replace(flContainer.id, fragment)
            .commit()
    }

    private fun setTabSelectedListner() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        switchFragment(Prizes())
                        setToolbar("Prizes")
                    }
                    1 -> {
                        switchFragment(Special())
                        setToolbar("Special Achievers")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun setToolbar(title: String) {
        Commons.setToolbarWithBackAndTitle(mContext, title, false, 0)
    }
}
