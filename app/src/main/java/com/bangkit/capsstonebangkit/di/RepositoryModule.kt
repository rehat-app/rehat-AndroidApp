package com.bangkit.capsstonebangkit.di

import com.bangkit.capsstonebangkit.repository.AnalysisRepository
import com.bangkit.capsstonebangkit.repository.CommunityRepository
import com.bangkit.capsstonebangkit.repository.DashboardRepository
import com.bangkit.capsstonebangkit.repository.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::UserRepository)
    singleOf(::CommunityRepository)
    singleOf(::DashboardRepository)
    singleOf(::AnalysisRepository)
}