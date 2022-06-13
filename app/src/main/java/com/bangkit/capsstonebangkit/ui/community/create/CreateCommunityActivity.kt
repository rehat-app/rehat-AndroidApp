package com.bangkit.capsstonebangkit.ui.community.create

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.CommunityCreateRequest
import com.bangkit.capsstonebangkit.data.api.model.CommunityJoinRequest
import com.bangkit.capsstonebangkit.databinding.ActivityCreateCommunityBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.community.CommunityActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateCommunityActivity : BaseActivity<ActivityCreateCommunityBinding>() {

    override fun getViewBinding() = ActivityCreateCommunityBinding.inflate(layoutInflater)

    private val createCommunityViewModel by viewModel<CreateCommunityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imvBack.setOnClickListener {
            onBackPressed()
        }


        createCommunityViewModel.createCommunityResponse.observe(this) {
            when (it.status) {

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when (it.data?.code()) {
                        //sukses
                        200 -> {
                            Toast.makeText(this, "Komunitas berhasil dibuat", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this, CommunityActivity::class.java)
                            intent.putExtra("community_id", it.data.body()?.idCommunity)
                            intent.putExtra("user_role", "admin")
                            startActivity(intent)
                            finish()
                            //go to community page
                        }

                    }
                }

                Status.ERROR -> {}

            }
        }

        createCommunityViewModel.joinCommunityResponse.observe(this) {
            when (it.status) {

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when (it.data?.code()) {
                        //sukses
                        200 -> {
                            Toast.makeText(this, "Berhasil join komunitas", Toast.LENGTH_SHORT)
                                .show()
                            //go to community page
                            val intent = Intent(this, CommunityActivity::class.java)
                            intent.putExtra("community_id", it.data.body()?.idCommunity)
                            intent.putExtra("user_role", "member")
                            startActivity(intent)
                            finish()
                        }

                        403 -> {
                            Toast.makeText(this, "Anda adalah admin!", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
                }

                Status.ERROR -> {}

            }
        }

        binding.btnShowCreate.setOnClickListener {
            binding.cvCreate.visibility = View.VISIBLE
        }

        binding.btnCreate.setOnClickListener {
            val communityName = binding.etCommunityName.editText?.text.toString()
            val deskripsi = binding.etDeskripsi.editText?.text.toString()
            val createRequest =
                CommunityCreateRequest(
                    name = communityName,
                    description = deskripsi
                )

            createCommunityViewModel.createCommunity(request = createRequest)

        }

        binding.btnJoin.setOnClickListener {

            val token = binding.etCode.editText?.text.toString()
            val createRequest =
                CommunityJoinRequest(
                    token = token
                )

            createCommunityViewModel.joinCommunity(request = createRequest)

        }


//        binding.imvBack.background =

    }
}