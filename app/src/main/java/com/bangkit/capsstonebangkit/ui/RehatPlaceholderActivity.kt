package com.bangkit.capsstonebangkit.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.capsstonebangkit.R

class RehatPlaceholderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rehat_placeholder)

        val image = intent.getIntExtra("image",0)
        val from = intent.getIntExtra("from",0)

        val imageView = findViewById<ImageView>(R.id.imv_placeholder)
        if (from==1){
            imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }

        imageView.setImageResource(image)


    }
}