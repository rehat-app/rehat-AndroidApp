package com.bangkit.capsstonebangkit.ui.dashboard

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capsstonebangkit.data.api.model.CommunityResponse
import com.bangkit.capsstonebangkit.databinding.CommunityListLayoutBinding

class CommunityAdapter(private val onClick:(CommunityResponse.Community)->Unit)
    : ListAdapter<CommunityResponse.Community, CommunityAdapter.ViewHolder>(CommunityComparator()) {


    class ViewHolder(private val binding: CommunityListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(currentCommunity: CommunityResponse.Community,
                 onClick: (CommunityResponse.Community) -> Unit){

            val hue = currentCommunity.hue?.toFloat() ?: 190f
            val satruation =
                currentCommunity.saturation?.dropLast(1)?.toFloat() ?: 89f
            val light = currentCommunity.light?.dropLast(1)?.toFloat() ?: 35f
            val bgColor = Color.HSVToColor(floatArrayOf(hue, satruation, light))
            binding.apply {
                btnCommunityName.text = currentCommunity.name
                root.setOnClickListener {
                    onClick(currentCommunity)
                }
                btnCommunityColor.backgroundTintList = ColorStateList.valueOf(bgColor)
            }

        }

    }

    class CommunityComparator : DiffUtil.ItemCallback<CommunityResponse.Community>() {
        override fun areItemsTheSame(oldItem: CommunityResponse.Community, newItem: CommunityResponse.Community): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CommunityResponse.Community, newItem: CommunityResponse.Community): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommunityListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

}