package com.bangkit.capsstonebangkit.ui.analysis

import android.os.Bundle
import android.widget.Toast
import com.bangkit.capsstonebangkit.data.api.model.PredictResponse
import com.bangkit.capsstonebangkit.databinding.ActivityAnalysisResultBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bumptech.glide.Glide
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class AnalysisResultActivity : BaseActivity<ActivityAnalysisResultBinding>() {

    override fun getViewBinding() = ActivityAnalysisResultBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imvBack.setOnClickListener {
            onBackPressed()
        }

        binding.sbEyesSlider.setOnTouchListener { _, _ -> true }
        binding.sbHangingEyes.setOnTouchListener { _, _ -> true }

        val analysisResult = intent.getParcelableExtra<PredictResponse>("analysis_result")

        binding.imvSave.setOnClickListener {
            Toast.makeText(this, "Data telah berhasil didownload", Toast.LENGTH_SHORT).show()
        }

        val current = Calendar.getInstance().time

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val formatted = sdf.format(current)

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        Glide.with(this).load(analysisResult?.prediction?.image).into(binding.imgFace)
        binding.apply {
            tvTime.text = formatted
            tvHangingEyes.text = "Eye Lid: ${analysisResult?.prediction?.eyelidCondition} ${
                df.format(analysisResult?.prediction?.probEyelid)
            }"
            sbHangingEyes.progress =
                (analysisResult?.prediction?.probEyelid?.times(100))?.toInt() ?: 0
            tvEyesBag.text = "Eye Bag: ${analysisResult?.prediction?.eyebagCondition} ${
                df.format(analysisResult?.prediction?.probEyebag)
            }"
            sbEyesSlider.progress =
                (analysisResult?.prediction?.probEyebag?.times(100))?.toInt() ?: 0

            val calc =
                (analysisResult?.prediction?.finalCondition?.probability?.times(100))?.toInt() ?: 0
            tvFinalResult.text = "$calc%"
//            tvEyeLidResult.text = analysisResult?.prediction?.eyelidCondition
//            tvEyeBagResult.text = analysisResult?.prediction?.eyebagCondition
            tvHeader.text = analysisResult?.prediction?.finalCondition?.header
            tvDetail.text = analysisResult?.prediction?.finalCondition?.detail
        }


    }
}