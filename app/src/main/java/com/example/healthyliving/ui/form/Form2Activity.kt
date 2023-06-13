//package com.example.healthyliving.ui.form
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.activity.OnBackPressedCallback
//import com.example.healthyliving.R
//import com.example.healthyliving.databinding.ActivityForm2Binding
//
//class Form2Activity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_form2)
//
//        private lateinit var binding: ActivityForm2Binding
////    private var _binding: FragmentForm2Binding? = null
////    private val binding get() = _binding!!
////    private var formData: Form1Fragment? = null
//        companion object {
//            var EXTRA_AGE= "extra_age"
//            var EXTRA_HEIGHT = "extra_height"
//            var EXTRA_WEIGHT= "extra_weight"
//            var EXTRA_GENDER= "extra_gender"
//            var EXTRA_DAILY = "extra_daily"
//        }
//        override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//        ): View {
//            binding= ActivityForm2Binding.inflate(inflater, container, false)
//            // Mengambil data yang dikirimkan dari Form1Fragment
//        formData = arguments?.getSerializable("formData") as? Form1Fragment
//            return binding.root
//        }
//
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            super.onViewCreated(view, savedInstanceState)
//
//            val gender = arguments?.getString(EXTRA_GENDER)
//            val age = arguments?.getInt(EXTRA_AGE, 0)
//            val height = arguments?.getDouble(EXTRA_HEIGHT, 0.0)
//            val weight = arguments?.getDouble(EXTRA_WEIGHT, 0.0)
//            val daily = arguments?.getString(EXTRA_DAILY)
//
//            val bmr = calculateBMR(gender, age, height, weight)
//            val bmrAct= activityBMR(bmr,daily)
//            binding.resultBmr.text = bmrAct.toString()
//
//            val bmi = calculateBMI(height, weight)
//            binding.resultBmi.text = bmi.toString()
//
//            val weightRecommendation = calculateWeightRecommendation(height)
//            binding.resultWeightRecomend.text = weightRecommendation
//
//            binding.btnSelesai.setOnClickListener {
//                val weightTarget = binding.etWeight.text.toString()
//
//                if (weightTarget.isNotBlank()) {
//                    val intent = Intent(activity, MainActivity::class.java)
//                    intent.putExtra("weightTarget", weightTarget)
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(activity, "Harap isi target berat badan", Toast.LENGTH_SHORT).show()
//                }
//            }
//            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    handleBackPressed()
//                }
//            })
//        }
//
//        private fun handleBackPressed() {
//            val fragmentManager = parentFragmentManager
//            fragmentManager.popBackStack()
//        }
//
//        private fun calculateBMR(gender: String?, ageInYears: Int?, heightInCm: Double?, weightInKg: Double?): Double {
//            val ageInMonths = ageInYears!! * 12
//
//            val bmr: Double = if (gender == "Laki-laki") {
//                66 + (13.75 * weightInKg!!) + (5 * heightInCm!!) - (6.75 * ageInMonths)
//            } else {
//                655 + (9.56 * weightInKg!!) + (1.85 * heightInCm!!) - (4.68 * ageInMonths)
//            }
//            return activityBMR(bmr, daily = String())
//        }
//
//        private fun activityBMR(bmr: Double, daily: String?): Double {
//            return when (daily) {
//                "Tidak aktif" -> bmr * 1.2
//                "Sedikit aktif(aktivitas fisik ringan 1-3 kali seminggu)" -> bmr * 1.375
//                "Aktif(aktivitas fisik ringan 3-5 kali seminggu)" -> bmr * 1.55
//                "Sangat aktif(aktivitas fisik berat 6-7 kali seminggu)" -> bmr * 1.725
//                "Sangat aktif sekali(aktivitas fisik berat setiap hari atau aktivitas fisik berat 2 kali sehari)" -> bmr * 1.9
//                else -> bmr
//            }
//        }
//        private fun calculateBMI(heightInCm: Double?, weightInKg: Double?): Double {
//            val heightInMeter = heightInCm!! / 100
//            return weightInKg!! / (heightInMeter * heightInMeter)
//        }
//
//        private fun Double.format(digits: Int) = "%.${digits}f".format(this)
//
//        private fun calculateWeightRecommendation(heightInCm: Double?): String {
//            val heightInMeter = heightInCm!! / 100
//            val weightMin = (heightInMeter * heightInMeter) * 18.5
//            val weightMax = (heightInMeter * heightInMeter) * 24.9
//            return "${weightMin.format(1)} - ${weightMax.format(1)} kg"
//        }
//    }
//}