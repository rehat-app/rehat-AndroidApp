package com.bangkit.capsstonebangkit.ui.community.member

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capsstonebangkit.data.api.model.CommunityDetailResponse
import com.bangkit.capsstonebangkit.databinding.CommunityMemberListLayoutBinding
import com.bumptech.glide.Glide

class CommunityMemberAdapter :
    ListAdapter<CommunityDetailResponse.Member, CommunityMemberAdapter.ViewHolder>(
        CommunityComparator()
    ) {


    class ViewHolder(private val binding: CommunityMemberListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            currentCommunity: CommunityDetailResponse.Member
        ) {

            binding.apply {
                tvMemberName.text = currentCommunity.username
                Glide
                    .with(binding.imgMember)
                    .load(currentCommunity.image)
                    .circleCrop()
                    .into(binding.imgMember)
            }

        }

    }

    class CommunityComparator : DiffUtil.ItemCallback<CommunityDetailResponse.Member>() {
        override fun areItemsTheSame(
            oldItem: CommunityDetailResponse.Member,
            newItem: CommunityDetailResponse.Member
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: CommunityDetailResponse.Member,
            newItem: CommunityDetailResponse.Member
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommunityMemberListLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}