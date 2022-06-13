package com.bangkit.capsstonebangkit.ui.register

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.databinding.ActivityRegisterBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.utils.PermissionUtils
import com.bangkit.capsstonebangkit.utils.URIPathHelper
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private var imageUri: Uri? = null

    override fun getViewBinding() = ActivityRegisterBinding.inflate(layoutInflater)

    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imvBack.setOnClickListener {
            finish()
        }

        //setup textview login
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

        if (imageUri==null){

            Glide.with(this).load(R.drawable.default_avatar).circleCrop().into(binding.imvPhotoProfile)
        }

        binding.imvUploadPhoto.setOnClickListener {
            if (PermissionUtils.isPermissionsGranted(this, getRequiredPermission()) {
                    requestPermissionLauncher.launch(getRequiredPermission())
                }){
                openGallery()
            }
        }

        registerViewModel.registerResponse.observe(this){
            when(it.status){

                Status.LOADING -> {
                    binding.pbRegister.visibility = View.VISIBLE
                    binding.btnRegister.visibility = View.INVISIBLE
                }

                Status.SUCCESS -> {
                    binding.pbRegister.visibility = View.GONE
                    binding.btnRegister.visibility = View.VISIBLE
                    when(it.data?.code()){
                        //sukses
                        200 ->{
                            Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        //username sudah ada
                        400 ->{
                            Toast.makeText(this, "username sudah ada", Toast.LENGTH_SHORT).show()
                        }
                        401 ->{
                            Toast.makeText(this, "email sudah ada", Toast.LENGTH_SHORT).show()
                        }
                        500 ->{
                            Toast.makeText(this, "tidak bisa validasi user", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
                Status.ERROR ->{
                    binding.pbRegister.visibility = View.GONE
                    binding.btnRegister.visibility = View.VISIBLE
                    Log.d("postregis", "onCreate: ${it.message}")

                }

            }
        }


        binding.apply {
            btnRegister.setOnClickListener{
                val username = etUsername.editText?.text.toString()
                val email = etEmail.editText?.text.toString()
                val password = etPassword.editText?.text.toString()

                val imageFile = if (imageUri==null){
                    val defaultPath = URIPathHelper.getPath(this@RegisterActivity,
                        Uri.Builder()
                        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                        .authority(resources.getResourcePackageName(R.drawable.default_avatar))
                        .appendPath(resources.getResourceTypeName(R.drawable.default_avatar))
                        .appendPath(resources.getResourceEntryName(R.drawable.default_avatar))
                        .build()
                    )
                    File(defaultPath.toString())


                }else{
                    File(URIPathHelper.getPath(this@RegisterActivity, imageUri!!).toString())
                }



                val usernameBody = username.toRequestBody("text/plain".toMediaTypeOrNull())
                val emailBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
                val passwordBody = password.toRequestBody("text/plain".toMediaTypeOrNull())

                val requestImage = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageBody = MultipartBody.Part.createFormData("image",
                    imageFile.name, requestImage
                )

                registerViewModel.postRegister(
                    username = usernameBody,
                    email = emailBody,
                    password = passwordBody,
                    image = imageBody
                )
            }
        }

    }

    private var launchSomeActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            imageUri = data?.data
            Glide.with(this).load(imageUri).circleCrop().into(binding.imvPhotoProfile)
        }
    }


    private fun openGallery() {
        val intentGallery = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        launchSomeActivity.launch(intentGallery)
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val granted = permissions.entries.all {
            it.value
        }
        if (granted) {
            openGallery()
        }
    }

    private fun getRequiredPermission(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }
}