package com.bangkit.capsstonebangkit.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.cookiesKey
import com.bangkit.capsstonebangkit.databinding.ActivitySplashScreenBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.dashboard.DashboardActivity
import com.bangkit.capsstonebangkit.ui.onboarding.OnBoardingActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun getViewBinding() = ActivitySplashScreenBinding.inflate(layoutInflater)

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //styling screen
        val backgroundColor = ContextCompat.getColor(this, R.color.dark_2)
        this.window.statusBarColor = backgroundColor
        this.window.navigationBarColor = backgroundColor

        val cookies = HashSet<String>(
            PreferenceManager
            .getDefaultSharedPreferences(this)
            .getStringSet(cookiesKey, HashSet()) as HashSet<String>)

        cookies.forEach {
            Log.d("sharedpreference cookies", "cookies $it")
        }

        splashViewModel.sessionResponse.observe(this){
            when(it.status){
                Status.LOADING->{}
                Status.SUCCESS->{
                    Handler(Looper.getMainLooper()).postDelayed({
                        when(it.data?.code()){
                            200->{
                                val intent = Intent(this, DashboardActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else->{
                                val intent = Intent(this, OnBoardingActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }, 2000)
                }
                Status.ERROR->{}
            }
        }

        splashViewModel.checkSession()

    }


}