package com.example.healthyliving.ui.main

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.healthyliving.databinding.ActivityEditBinding
import com.example.healthyliving.di.ViewModelFactory
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.ui.authentication.DataStoreViewModel
import com.example.healthyliving.ui.authentication.dataStore
import com.example.healthyliving.ui.helper.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

@Suppress("DEPRECATION")
class EditActivity : AppCompatActivity() {
    private val editViewModel : EditViewModel by viewModels()
    private lateinit var binding: ActivityEditBinding
    private lateinit var token: String

    private var getFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Edit Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pref = UserPreference.getInstance(dataStore)
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[DataStoreViewModel::class.java]

        viewModel.getToken().observe(this) {
            token = it
        }

        editViewModel.message.observe(this) {
            showToast(it)
        }

        editViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        binding.galleryButton.setOnClickListener { startGallery() }
        binding.btnSimpan.setOnClickListener { uploadProfile() }
    }

    private fun uploadProfile() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val nama = binding.nameInput.text.toString()
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )
            editViewModel.editProfile(
                imageMultipart,
                nama,
                token)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this)
                getFile = myFile
                binding.imgItemAvatar.setImageURI(uri)
            }
        }
    }

    private fun reduceFileImage(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > MAXIMAL_SIZE)
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showToast(message: String) {
        if (message.contains("Profile successfully updated")) {
            Toast.makeText(
                this,
                message,
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this@EditActivity, ProfileFragment::class.java))
            finish()
        }
    }
    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
        private const val MAXIMAL_SIZE = 1000000
    }
}