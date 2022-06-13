package com.bangkit.capsstonebangkit.ui.community.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.CommunityDetailResponse
import com.bangkit.capsstonebangkit.databinding.FragmentCommunityMemberBinding
import com.bangkit.capsstonebangkit.ui.community.CommunityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CommunityMemberFragment : Fragment() {

    private lateinit var binding: FragmentCommunityMemberBinding

    private val communityViewModel by sharedViewModel<CommunityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommunityMemberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        communityViewModel.communityDetailResponse.observe(viewLifecycleOwner) {
            when (it.status) {

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when (it.data?.code()) {
                        //sukses
                        200 -> {
                            binding.apply {
                                tvToken.text = it.data.body()?.community?.get(0)?.token
                                tvDeskripsi.text = it.data.body()?.community?.get(0)?.description
                                tvHost.text = it.data.body()?.community?.get(0)?.username
                                tvJumlahAnggota.text = it.data.body()?.members?.size.toString()
                            }
                            showMemberList(it.data.body()?.members)
                        }

                    }
                }

                Status.ERROR -> {}

            }
        }


    }

    private fun showMemberList(members: List<CommunityDetailResponse.Member>?) {
        val adapter = CommunityMemberAdapter()
        adapter.submitList(members)
        binding.rvMember.adapter = adapter
    }

}