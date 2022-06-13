package com.bangkit.capsstonebangkit.di

import com.bangkit.capsstonebangkit.ui.analysis.AnalysisResultViewModel
import com.bangkit.capsstonebangkit.ui.community.CommunityViewModel
import com.bangkit.capsstonebangkit.ui.community.create.CreateCommunityViewModel
import com.bangkit.capsstonebangkit.ui.dashboard.DashboardViewModel
import com.bangkit.capsstonebangkit.ui.editprofile.EditProfileViewModel
import com.bangkit.capsstonebangkit.ui.login.LoginViewModel
import com.bangkit.capsstonebangkit.ui.password.forget.ForgetPasswordViewModel
import com.bangkit.capsstonebangkit.ui.password.update.UpdatePasswordViewModel
import com.bangkit.capsstonebangkit.ui.register.RegisterViewModel
import com.bangkit.capsstonebangkit.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::ForgetPasswordViewModel)
    viewModelOf(::UpdatePasswordViewModel)
    viewModelOf(::DashboardViewModel)
    viewModelOf(::CreateCommunityViewModel)
    viewModelOf(::CommunityViewModel)
    viewModelOf(::AnalysisResultViewModel)
    viewModelOf(::EditProfileViewModel)
}

