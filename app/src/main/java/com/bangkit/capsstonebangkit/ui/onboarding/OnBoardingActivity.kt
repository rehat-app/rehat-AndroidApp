package com.bangkit.capsstonebangkit.ui.onboarding

import android.content.Intent
import android.os.Bundle
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.databinding.ActivityOnBoardingBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.login.LoginActivity
import com.bangkit.capsstonebangkit.ui.register.RegisterActivity
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {

    override fun getViewBinding() = ActivityOnBoardingBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val listImage = listOf(
            R.drawable.onboarding_image1,
            R.drawable.onboarding_image2,
            R.drawable.onboarding_image3,
        )

        val adapter = OnBoardingAdapter(listImage)
        binding.vpOnboarding.adapter = adapter


        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        TabLayoutMediator(binding.tabOnboarding, binding.vpOnboarding) { _, _ ->}.attach()

    }
}