package com.bangkit.capsstonebangkit.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.CommunityResponse
import com.bangkit.capsstonebangkit.databinding.ActivityDashboardBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.RehatPlaceholderActivity
import com.bangkit.capsstonebangkit.ui.camera.CameraActivity
import com.bangkit.capsstonebangkit.ui.community.CommunityActivity
import com.bangkit.capsstonebangkit.ui.community.create.CreateCommunityActivity
import com.bangkit.capsstonebangkit.ui.editprofile.EditProfileActivity
import com.bangkit.capsstonebangkit.ui.login.LoginActivity
import com.bangkit.capsstonebangkit.utils.HorizontalMarginItemDecoration
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override fun getViewBinding() = ActivityDashboardBinding.inflate(layoutInflater)

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dashboardViewModel.profileResponse.observe(this){

            when(it.status){

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when(it.data?.code()){
                        //sukses
                        200 ->{
                            Glide.with(this)
                                .load(it.data.body()?.image)
                                .circleCrop()
                                .into(binding.ivProfile)

                            binding.tvName.text = it.data.body()?.username
                        }

                    }
                }

                Status.ERROR ->{}

            }

        }

        dashboardViewModel.communityResponse.observe(this){
            when(it.status){

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when(it.data?.code()){
                        //sukses
                        200 ->{
                            showCommunityList(it.data.body()?.communities)
                        }

                    }
                }

                Status.ERROR ->{}

            }
        }

        dashboardViewModel.getProfile()
        dashboardViewModel.getCommunities()


        binding.btnLogout.setOnClickListener {
            PreferenceManager
                .getDefaultSharedPreferences(this)
                .edit()
                .clear()
                .apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val listImage = listOf(
            R.drawable.dashboard_banner_image_1,
            R.drawable.adsense,
            R.drawable.adsense,
        )


        val adapter = DashboardBannerAdapter(listImage)
        binding.vpDashboardBanner.adapter = adapter
        binding.vpDashboardBanner.offscreenPageLimit = 1
        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin
        )

        binding.vpDashboardBanner.addItemDecoration(itemDecoration)
        TabLayoutMediator(binding.tabDashboardBanner, binding.vpDashboardBanner) { _, _ ->}.attach()

        Glide
            .with(this)
            .load(R.drawable.default_avatar)
            .circleCrop()
            .into(binding.ivProfile)

        binding.btnCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        binding.btnRehatHelp.setOnClickListener {
            val intent = Intent(this, RehatPlaceholderActivity::class.java)
            intent.putExtra("image" , R.drawable.app_flow)
            intent.putExtra("from" , 1)
            startActivity(intent)
        }

        binding.btnRehatEdukasi.setOnClickListener {
            val intent = Intent(this, RehatPlaceholderActivity::class.java)
            intent.putExtra("image" , R.drawable.edukasi_placeholder)
            intent.putExtra("from" , 2)
            startActivity(intent)
        }

        binding.cvNewCommunity.setOnClickListener {
            val intent = Intent(this, CreateCommunityActivity::class.java)
            startActivity(intent)
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onRestart() {
        super.onRestart()
        dashboardViewModel.getCommunities()
    }

    private fun showCommunityList(communities: List<CommunityResponse.Community>?) {

        val adapter= CommunityAdapter {
            val intent = Intent(this, CommunityActivity::class.java)
            intent.putExtra("community_id", it.id)
            intent.putExtra("user_role", it.userRole)
            startActivity(intent)
        }
        adapter.submitList(communities)
        binding.rvCommunity.adapter = adapter
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}