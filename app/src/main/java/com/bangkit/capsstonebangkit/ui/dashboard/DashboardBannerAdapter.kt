package com.bangkit.capsstonebangkit.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capsstonebangkit.databinding.DashboardBannerListLayoutBinding
import com.bumptech.glide.Glide

class DashboardBannerAdapter(private val listImage: List<Int>):RecyclerView.Adapter<DashboardBannerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: DashboardBannerListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(currentImage: Int){

            binding.apply {
                Glide.with(imvBanner)
                    .load(currentImage)
                    .into(imvBanner)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DashboardBannerListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listImage[position])
    }

    override fun getItemCount() = listImage.size

}