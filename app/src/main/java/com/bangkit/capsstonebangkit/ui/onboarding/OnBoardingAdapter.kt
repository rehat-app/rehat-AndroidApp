package com.bangkit.capsstonebangkit.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capsstonebangkit.databinding.OnboardingBannerListLayoutBinding
import com.bumptech.glide.Glide

class OnBoardingAdapter(private val listImage: List<Int>):RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    class ViewHolder(private val binding: OnboardingBannerListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(currentImage: Int){

            binding.apply {
                Glide.with(imvBanner)
                    .load(currentImage)
                    .into(imvBanner)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OnboardingBannerListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listImage[position])
    }

    override fun getItemCount() = listImage.size

}