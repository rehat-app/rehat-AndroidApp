package com.bangkit.capsstonebangkit.ui.password.update

import android.os.Bundle
import android.widget.Toast
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.UpdatePasswordRequest
import com.bangkit.capsstonebangkit.databinding.ActivityUpdatePasswordBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdatePasswordActivity : BaseActivity<ActivityUpdatePasswordBinding>() {

    override fun getViewBinding() = ActivityUpdatePasswordBinding.inflate(layoutInflater)

    private val updatePasswordViewModel: UpdatePasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent?.data


        val url = data.toString()

        //get token via dropping first 43 character (http://localhost:4000/reset-password?token=)
        val token = url.drop(43)
//        //get token via split string
//        val token2 = url.split("token=")[1]

        updatePasswordViewModel.updatePasswordResponse.observe(this){
            when(it.status){

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when(it.data?.code()){
                        //sukses
                        200 ->{
                            Toast.makeText(this, "Password berhasil diganti", Toast.LENGTH_SHORT).show()
                        }
                        401 ->{
                            Toast.makeText(this, "token tidak valid", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                Status.ERROR ->{}

            }
        }

        binding.btnUpdatePassword.setOnClickListener {
            val password = binding.etPassword.editText?.text.toString()
            val passwordRequest = UpdatePasswordRequest(token, password)
            updatePasswordViewModel.postUpdatePassword(passwordRequest)
        }

        binding.imvBack.setOnClickListener {
            finish()
        }
    }
}