package com.bangkit.capsstonebangkit.ui.login

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.LoginRequest
import com.bangkit.capsstonebangkit.databinding.ActivityLoginBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.dashboard.DashboardActivity
import com.bangkit.capsstonebangkit.ui.password.forget.ForgetPasswordActivity
import com.bangkit.capsstonebangkit.ui.register.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    private val loginViewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setup create account text

        val text1 = "Belum punya akun?"
        val text2 = "Buat akun baru"
        val lightColor = ContextCompat.getColor(this, R.color.light_2)
        val textSize = resources.getDimensionPixelSize(R.dimen.register_text_size)


        val span2 = SpannableString(text2)
        span2.setSpan(AbsoluteSizeSpan(textSize), 0, text2.length, SPAN_INCLUSIVE_INCLUSIVE)
        span2.setSpan(ForegroundColorSpan(lightColor), 0, text2.length, SPAN_INCLUSIVE_INCLUSIVE)

        val registerText = TextUtils.concat(text1,span2)

        binding.tvCreateAccount.text = registerText

        binding.tvCreateAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        //edittext styling
        binding.etEmail.editText?.setOnFocusChangeListener { _, hasFocus ->
            val color = if (hasFocus) {
                ContextCompat.getColor(this, R.color.light_2)
            }else {
                ContextCompat.getColor (this, R.color.light_2_70)
            }
            binding.etEmail.setStartIconTintList(ColorStateList.valueOf(color))

        }
        binding.etPasswordField.setOnFocusChangeListener { _, hasFocus ->
            val color = if (hasFocus) {
                ContextCompat.getColor(this, R.color.light_2)
            }else {
                ContextCompat.getColor (this, R.color.light_2_70)
            }
            binding.etPassword.setStartIconTintList(ColorStateList.valueOf(color))
            binding.etPassword.setEndIconTintList(ColorStateList.valueOf(color))

        }


        //setup observer
        loginViewModel.loginResponse.observe(this){
            when(it.status){

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when(it.data?.code()){
                        //sukses
                        200 ->{
                            Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, DashboardActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        //password salah
                        401 ->{
                            Toast.makeText(this, "harap perhatikan password anda", Toast.LENGTH_SHORT).show()
                        }
                        //user tidak ditemukan
                        404 ->{
                            Toast.makeText(this, "user tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                Status.ERROR ->{}

            }
        }

        binding.tvForgetPassword.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.apply {
            btnLogin.setOnClickListener{
                val email = etEmail.editText?.text.toString()
                val password = etPassword.editText?.text.toString()
                val loginRequest = LoginRequest(email, password)
                loginViewModel.postLogin(loginRequest)
            }
        }

        binding.imvBack.setOnClickListener {
            finish()
        }

    }



}