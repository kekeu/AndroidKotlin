package com.dev.clevertonsantos.viewpagerexample

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingItemAdapter: OnboardingItemAdapter
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var buttonStarted: AppCompatButton
    private lateinit var onboardingViewPager: ViewPager2
    private val PAGE_INITIAL = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setOnboardingItens()
        setUpIndicators()
        setCurrentIndicator(PAGE_INITIAL)
        setCurrentTextButton(PAGE_INITIAL)
    }

    private fun setOnboardingItens() {
        onboardingItemAdapter = OnboardingItemAdapter(
            listOf(
                OnboardingItemFragment(R.layout.onboarding_item_0),
                OnboardingItemFragment(R.layout.onboarding_item_1),
                OnboardingItemFragment(R.layout.onboarding_item_2)
            ),
            this
        )

        onboardingViewPager = findViewById(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemAdapter
        onboardingViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                setCurrentTextButton(position)
            }
        })
        (onboardingViewPager.getChildAt(PAGE_INITIAL) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        buttonStarted = findViewById(R.id.buttonStarted)
        buttonStarted.setOnClickListener {
            if (onboardingViewPager.currentItem + 1 == onboardingItemAdapter.itemCount) {
                navigateToHome()
            } else {
                onboardingViewPager.currentItem += 1
            }
        }
    }

    private fun setUpIndicators() {
        indicatorContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(ContextCompat.getDrawable(
                    applicationContext, R.drawable.indicator_inactive_background))
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                    applicationContext, R.drawable.indicator_active_background))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                    applicationContext, R.drawable.indicator_inactive_background))
            }
        }
    }

    private fun setCurrentTextButton(position: Int) {
        if (position == PAGE_INITIAL || position + 1 == onboardingItemAdapter.itemCount) {
            buttonStarted.text = "VAMOS LÁ!"
        } else {
            buttonStarted.text = "CONTINUAR"
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (onboardingViewPager.currentItem == PAGE_INITIAL) {
            super.onBackPressed()
        } else {
            onboardingViewPager.currentItem -= 1
        }
    }

}