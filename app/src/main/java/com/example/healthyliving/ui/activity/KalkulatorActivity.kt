//package com.example.healthyliving.ui.activity
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.ArrayAdapter
//import android.widget.Toast
//import androidx.core.content.ContentProviderCompat.requireContext
//import com.example.healthyliving.R
//import com.example.healthyliving.databinding.ActivityKalkulator2Binding
//import com.example.healthyliving.databinding.ActivityKalkulatorBinding
//import com.example.healthyliving.ui.fragment.Form2Fragment
//
//class KalkulatorActivity : AppCompatActivity(), View.OnClickListener  {
//    private lateinit var binding : ActivityKalkulatorBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityKalkulatorBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val genderSpinner= binding.genderSpinner
//        val genderOptions = listOf("Laki-laki", "Perempuan")
//        val genderAdapter = ArrayAdapter(this, R.layout.dropdown_menu_popup_item, genderOptions)
//        genderSpinner.adapter = genderAdapter
//        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.genderSpinner.adapter = genderAdapter
//
//        val activitySpinner = binding.dailySpinner
//        val activityOptions = listOf("Tidak aktif", "Sedikit aktif(aktivitas fisik ringan 1-3 kali seminggu)", "Aktif(aktivitas fisik ringan 3-5 kali seminggu)", "Sangat aktif(aktivitas fisik berat 6-7 kali seminggu)", "Sangat aktif sekali(aktivitas fisik berat setiap hari atau aktivitas fisik berat 2 kali sehari)")
//        val activityAdapter = ArrayAdapter(this, R.layout.dropdown_menu_popup_item, activityOptions)
//        activitySpinner.adapter = activityAdapter
//        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.dailySpinner.adapter = activityAdapter
//    }
//
//    override fun onClick(v: View){
//        if (v.id == R.id.btn_lanjutkan) {
//            if (isFormValid()) {
//                val gender = binding.genderSpinner.selectedItem.toString()
//                val age = binding.etAge.text.toString().toInt()
//                val height = binding.etHeight.text.toString().toDouble()
//                val weight = binding.etWeight.text.toString().toDouble()
//                val daily = binding.dailySpinner.selectedItem.toString()
//
//                val form2Fragment = Form2Fragment()
//                val bundle = Bundle().apply {
//                    putString(Form2Fragment.EXTRA_GENDER,gender)
//                    putInt(Form2Fragment.EXTRA_AGE,age)
//                    putDouble(Form2Fragment.EXTRA_HEIGHT,height)
//                    putDouble(Form2Fragment.EXTRA_WEIGHT,weight)
//                    putString(Form2Fragment.EXTRA_DAILY,daily)
//                }
//                val intent = Intent(this, KalkulatorActivity::class.java)
//                startActivity(intent)
//                }
//            } else {
//                Toast.makeText(this, "Harap isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//    private fun isFormValid(): Boolean {
//        val age = binding.etAge.text.toString()
//        val height = binding.etHeight.text.toString()
//        val weight = binding.etWeight.text.toString()
//
//        return age.isNotBlank() && height.isNotBlank() && weight.isNotBlank()
//    }
//}