package com.example.healthyliving.ui.form//package com.example.healthyliving.ui.activity
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.Toast
//import com.example.healthyliving.R
//import com.example.healthyliving.databinding.ActivityForm1Binding
//
//class Form1Activity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityForm1Binding
//    private var gender: String? = null
//    private var daily: String? = null
//    private var age: Int = 0
//    private var height: Double = 0.0
//    private var weight: Double = 0.0
//
//    //    private lateinit var weight: EditText
////    private lateinit var height: EditText
////    private lateinit var age: EditText
////    private lateinit var btnLanjut: Button
////    private var gender: String? = null
////    private var daily: String? = null
////    companion object {
////        const val EXTRA_AGE= "extra_age"
////        const val EXTRA_HEIGHT = "extra_height"
////        const val EXTRA_WEIGHT= "extra_weight"
////        const val EXTRA_GENDER= "extra_gender"
////        const val EXTRA_DAILY = "extra_daily"
////    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityForm1Binding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//            val genderSpinner= binding.genderSpinner
//            val genderOptions = listOf("Laki-laki", "Perempuan")
//            val genderAdapter = ArrayAdapter(this, R.layout.dropdown_menu_popup_item, genderOptions)
////        genderSpinner.adapter = genderAdapter
//            genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            binding.genderSpinner.adapter = genderAdapter
//
//            val activitySpinner = binding.dailySpinner
//            val activityOptions = listOf("Tidak aktif", "Sedikit aktif(aktivitas fisik ringan 1-3 kali seminggu)", "Aktif(aktivitas fisik ringan 3-5 kali seminggu)", "Sangat aktif(aktivitas fisik berat 6-7 kali seminggu)", "Sangat aktif sekali(aktivitas fisik berat setiap hari atau aktivitas fisik berat 2 kali sehari)")
//            val activityAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, activityOptions)
////        activitySpinner.adapter = activityAdapter
//            activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            binding.dailySpinner.adapter = activityAdapter
//        }
////        val gender = binding.genderSpinner.selectedItem.toString()
////        val age = edtAge.text.toString().toInt()
////        val height = edtHeight.text.toString().toDouble()
////        val weight = edtWeight.text.toString().toDouble()
////        val daily = binding.dailySpinner.selectedItem.toString()
//
////        btnLanjut.setOnClickListener{view->
////            val mBundle = Bundle()
////            mBundle.putString(EXTRA_GENDER, gender)
////            mBundle.putInt(EXTRA_AGE, age)
////            mBundle.putDouble(EXTRA_HEIGHT, height)
////            mBundle.putDouble(EXTRA_WEIGHT, weight)
////            mBundle.putString(EXTRA_DAILY, daily)
////
////            view.findNavController().navigate(R.id.action_form1_to_form2, mBundle)
////        }
//
//    override fun onClick(v: View){
//        if (v.id == R.id.btn_lanjutkan) {
//            if (isFormValid()) {
//                gender = binding.genderSpinner.selectedItem.toString()
//                age = binding.etAge.text.toString().toInt()
//                height = binding.etHeight.text.toString().toDouble()
//                weight = binding.etWeight.text.toString().toDouble()
//                daily = binding.dailySpinner.selectedItem.toString()
//
//                val form2Fragment = Form2Fragment()
//                val bundle = Bundle().apply {
//                    putString(Form2Fragment.EXTRA_GENDER,gender)
//                    putInt(Form2Fragment.EXTRA_AGE,age)
//                    putDouble(Form2Fragment.EXTRA_HEIGHT,height)
//                    putDouble(Form2Fragment.EXTRA_WEIGHT,weight)
//                    putString(Form2Fragment.EXTRA_DAILY,daily)
//                }
//                form2Fragment.arguments = bundle
//                parentFragmentManager.beginTransaction().apply {
//                    replace(
//                        R.id.frame_container,
//                        form2Fragment,
//                        Form2Fragment::class.java.simpleName)
//                    addToBackStack(null)
//                    commit()
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
//        return age.isNotBlank() && height.isNotBlank() && weight.isNotBlank()
//    }
//}