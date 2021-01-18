package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.databinding.ActivityTabPagerBinding
import com.google.android.material.tabs.TabLayout

class TabPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ONE"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TWO"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("THREE"))

        val pagerAdapter = FragmentPagerAdapter(supportFragmentManager, 3)
        binding.viewPager.adapter = pagerAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
            // Tab이 선택됬을때 Pager를 해당 칸으로 이동
                binding.viewPager.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        // -> Pager 이동에 따라서 Tab을 이동하게 하는 코드
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

    }
}


class FragmentPagerAdapter(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return Fragment1()
            }
            1 -> {
                return Fragment2()
            }
            2 -> {
                return Fragment3()
            }
            else -> return Fragment1()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}