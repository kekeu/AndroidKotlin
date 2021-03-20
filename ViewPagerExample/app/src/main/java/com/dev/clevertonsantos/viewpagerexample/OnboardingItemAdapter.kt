package com.dev.clevertonsantos.viewpagerexample

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingItemAdapter(
        private val onboardingItens: List<Fragment>,
        activity: AppCompatActivity
): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return onboardingItens.size
    }

    override fun createFragment(position: Int): Fragment {
        return onboardingItens[position]
    }

}