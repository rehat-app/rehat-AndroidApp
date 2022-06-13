package com.bangkit.capsstonebangkit.ui.community

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.databinding.ActivityCommunityBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.community.agenda.CommunityAgendaFragment
import com.bangkit.capsstonebangkit.ui.community.member.CommunityMemberFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommunityActivity : BaseActivity<ActivityCommunityBinding>() {

    override fun getViewBinding() = ActivityCommunityBinding.inflate(layoutInflater)

    private val communityViewModel by viewModel<CommunityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imvBack.setOnClickListener {
            onBackPressed()
        }

        val communityId = intent.getIntExtra("community_id", 0)
        val userRole = intent.getStringExtra("user_role")

        if (userRole == "member") {
            binding.btnSetting.visibility = View.GONE
        }

        communityViewModel.communityDetailResponse.observe(this) {
            when (it.status) {

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when (it.data?.code()) {
                        //sukses
                        200 -> {

                            val data = it.data.body()
                            val hue = data?.colors?.get(0)?.hue?.toFloat() ?: 190f
                            val satruation =
                                data?.colors?.get(0)?.saturation?.dropLast(1)?.toFloat() ?: 89f
                            val light = data?.colors?.get(0)?.light?.dropLast(1)?.toFloat() ?: 35f
                            val bgColor = Color.HSVToColor(floatArrayOf(hue, satruation, light))

                            binding.apply {
                                tvToken.text = it.data.body()?.community?.get(0)?.token
                                binding.tvCommunityName.setBackgroundColor(bgColor)
                            }
                        }

                    }
                }

                Status.ERROR -> {}

            }
        }

        communityViewModel.getCommunityDetail(communityId)


        binding.btnCopyToken.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("code", binding.tvToken.text.toString())
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "Kode telah dicopy", Toast.LENGTH_SHORT).show()
        }

        binding.btnAgenda.setOnClickListener {
            binding.btnAnggota.alpha = 0.5f
            binding.btnAgenda.alpha = 1f

            supportFragmentManager.beginTransaction()
                .replace(R.id.community_fragment_container, CommunityAgendaFragment())
                .commit()

        }

        binding.btnAnggota.setOnClickListener {
            binding.btnAgenda.alpha = 0.5f
            binding.btnAnggota.alpha = 1f

            supportFragmentManager.beginTransaction()
                .replace(R.id.community_fragment_container, CommunityMemberFragment())
                .commit()

        }

        binding.btnCloseToken.setOnClickListener {
            binding.cvShareToken.visibility = View.GONE
            binding.btnNewAgenda.visibility = View.VISIBLE
        }


    }
}