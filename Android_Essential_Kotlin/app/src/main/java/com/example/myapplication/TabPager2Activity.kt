package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.databinding.ActivityTabPagerBinding
import com.google.android.material.tabs.TabLayout

class TabPager2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTabPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ONE"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TWO"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("THREE"))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Tab이 선택됬을때 Pager를 해당 칸으로 이동
                binding.viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        val adapter = ThreePageAdapter(LayoutInflater.from(this@TabPager2Activity))
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
    }
}

class ThreePageAdapter(
    val layoutInflater: LayoutInflater
) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // 뷰를 실제로 그려주는 부분
        when (position) {
            0 -> {
                val view = layoutInflater.inflate(R.layout.fragement_one, container, false)
                container.addView(view)
                // 여기의 view가 destroyItem, isViewFromObject의 `object`로 들어온다
                return view
            }
            1 -> {
                val view = layoutInflater.inflate(R.layout.fragement_two, container, false)
                container.addView(view)

                return view
            }
            2 -> {
                val view = layoutInflater.inflate(R.layout.fragement_three, container, false)
                container.addView(view)
                return view
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.fragement_one, container, false)
                container.addView(view)
                // 여기의 view가 destroyItem, isViewFromObject의 `object`로 들어온다
                return view
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // 뷰가 가려질때 호출
        // `object` 얘가 뷰임 그래서 뷰로 캐스팅후 떼어줌
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        // 내가 보고있는 화면과 리턴받은 화면이 같은지 비교하는 함수
        // 왜해야하는가?? PagerAdapter 만든사람이 그랬다고함ㅎㅎ...
        // == 두개는 값비교 / === 세개는 저 명확한 주소 비교
        return view === `object` as View
    }

}