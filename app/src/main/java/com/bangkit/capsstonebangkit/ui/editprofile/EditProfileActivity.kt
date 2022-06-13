package com.bangkit.capsstonebangkit.ui.editprofile

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.ProfileEditRequest
import com.bangkit.capsstonebangkit.data.api.model.ProfileResponse
import com.bangkit.capsstonebangkit.databinding.ActivityEditProfileBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.dashboard.DashboardViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileActivity : BaseActivity<ActivityEditProfileBinding>() {

    override fun getViewBinding() = ActivityEditProfileBinding.inflate(layoutInflater)
    private val editProfileViewModel : EditProfileViewModel by viewModel()
    private val dashboardViewModel : DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnFinanceBackhome.setOnClickListener {
            onBackPressed()
        }

        dashboardViewModel.getProfile()
        dashboardViewModel.profileResponse.observe(this){
            when(it.status){
                Status.LOADING->{}
                Status.SUCCESS->{
                    showDetailProfile(it.data?.body()!!)
                    Log.d("test edit profile", it.data.body()!!.id.toString())
                }
                Status.ERROR->{
                    Log.d("test edit profile",it.status.toString())
                }
            }
        }




    }

    private fun showDetailProfile(data : ProfileResponse){

        Glide.with(this)
            .load(data.image)
            .circleCrop()
            .into(binding.ivEditprofileProfile)

        binding.etinputEmailEditprofile.setText(data.email)
        binding.etinputUsernameEditprofile.setText(data.username)


        binding.btnEditComplete.setOnClickListener {
            editProfile(data)
        }
    }

    private fun editProfile(data : ProfileResponse){
        val requestEdit = ProfileEditRequest(
            data.id,
            binding.etinputUsernameEditprofile.toString(),
            data.image,
        )

        editProfileViewModel.postEditProfile(requestEdit)
        editProfileViewModel.editProfileResponse.observe(this){
            when(it.status){
                Status.LOADING->{}
                Status.SUCCESS->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                Status.ERROR->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }


    }

}