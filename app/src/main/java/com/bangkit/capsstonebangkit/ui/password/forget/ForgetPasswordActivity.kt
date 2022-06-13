package com.bangkit.capsstonebangkit.ui.password.forget

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.ForgetPasswordRequest
import com.bangkit.capsstonebangkit.databinding.ActivityForgetPasswordBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgetPasswordActivity : BaseActivity<ActivityForgetPasswordBinding>() {

    override fun getViewBinding() = ActivityForgetPasswordBinding.inflate(layoutInflater)

    private val forgetPasswordViewModel: ForgetPasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text1 = "Sudah punya akun?"
        val text2 = "Login"
        val lightColor = ContextCompat.getColor(this, R.color.light_2)
        val textSize = resources.getDimensionPixelSize(R.dimen.register_text_size)


        val span2 = SpannableString(text2)
        span2.setSpan(AbsoluteSizeSpan(textSize), 0, text2.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        span2.setSpan(
            ForegroundColorSpan(lightColor), 0, text2.length,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )

        val registerText = TextUtils.concat(text1,span2)

        binding.tvLogin.text = registerText

        binding.tvLogin.setOnClickListener {
            finish()
        }


        forgetPasswordViewModel.forgetPasswordResponse.observe(this){
            when(it.status){

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when(it.data?.code()){
                        //sukses
                        200 ->{
                            Toast.makeText(this, "Link reset password telah dikirim ke email anda", Toast.LENGTH_SHORT).show()
                        }
                        402 ->{
                            Toast.makeText(this, "harap perhatikan passwora", Toast.LENGTH_SHORT).show()
                        }
                        403 ->{
                            Toast.makeText(this, "Terjadi kesalahan, harap coba lagi", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                Status.ERROR ->{}

            }
        }

        binding.btnSendUpdatePassword.setOnClickListener {
            val email = binding.etEmail.editText?.text.toString()
            val passwordRequest = ForgetPasswordRequest(email)
            forgetPasswordViewModel.postForgetPassword(passwordRequest)
        }

        binding.imvBack.setOnClickListener {
            finish()
        }

    }
}