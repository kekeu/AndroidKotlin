package com.dev.clevertonsantos.pocdotsindicator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)

        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager2)
        val adapter = DotIndicatorPager2Adapter()
        viewPager2.adapter = adapter

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        viewPager2.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
        }

        dotsIndicator.setViewPager2(viewPager2)
    }
}