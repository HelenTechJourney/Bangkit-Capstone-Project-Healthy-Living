package com.example.healthyliving.ui.form

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.healthyliving.R
import com.example.healthyliving.databinding.FragmentForm2Binding
import com.example.healthyliving.di.ViewModelFactory
import com.example.healthyliving.remote.response.RequestForm
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.ui.authentication.DataStoreViewModel

@Suppress("NAME_SHADOWING")
class Form2Fragment(private val dataStore: DataStore<Preferences>) : Fragment(), View.OnFocusChangeListener {

    private val formViewModel: FormViewModel by viewModels()
    private var _binding: FragmentForm2Binding? = null
    private val binding get() = _binding!!

    private lateinit var gender: String
    private var age: Int = 0
    private var height: Int = 0
    private var weight: Int = 0
    private lateinit var dailyActivity: String
    private lateinit var token: String

    private var isAgeValid : Boolean= false
        get() {
            checkAge()
            return field
        }

    private var isWeightValid : Boolean= false
        get() {
            checkWeight()
            return field
        }
    private var isHeightValid : Boolean = false
        get() {
            checkHeight()
            return field
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "Isi User Data"
        _binding = FragmentForm2Binding.inflate(inflater, container, false)

        val pref = UserPreference.getInstance(dataStore)
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[DataStoreViewModel::class.java]

        viewModel.getToken().observe(viewLifecycleOwner) {
            token = it
        }
        val genderSpinner= binding.genderSpinner
        val genderOptions = listOf("Laki-laki", "Perempuan")
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.droplist_item, genderOptions)
        genderSpinner.adapter = genderAdapter
//        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.genderSpinner.adapter = genderAdapter

        val activitySpinner = binding.dailySpinner
        val activityOptions = listOf(
            "Tidak aktif",
            "Sedikit aktif(aktivitas fisik ringan 1-3 kali seminggu)",
            "Aktif(aktivitas fisik ringan 3-5 kali seminggu)",
            "Sangat aktif(aktivitas fisik berat 6-7 kali seminggu)",
            "Sangat aktif sekali(aktivitas fisik berat setiap hari atau aktivitas fisik berat 2 kali sehari)")
        val activityAdapter = ArrayAdapter(requireContext(), R.layout.droplist_item, activityOptions)
        activitySpinner.adapter = activityAdapter
//        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.dailySpinner.adapter = activityAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        binding.btnLanjutkan.setOnClickListener { view ->
            if (isDataValid()) {
                gender = binding.genderSpinner.selectedItem.toString().trim()
                age = binding.etAge.text.toString().trim().toInt()
                height = binding.etHeight.text.toString().trim().toInt()
                weight = binding.etWeight.text.toString().trim().toInt()
                dailyActivity = binding.dailySpinner.selectedItem.toString().trim()
                val data = RequestForm(
                    gender,
                    age,
                    height,
                    weight,
                    dailyActivity,
                    token
                )
                formViewModel.getFormResponse(data)
            }
            view.findNavController().navigate(R.id.action_form2Fragment_to_form3Fragment)
            return@setOnClickListener
        }

        binding.etAge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                isFieldsEmpty()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.etHeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                isFieldsEmpty()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                isFieldsEmpty()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun isFieldsEmpty(): Boolean {
        return binding.etAge.text.toString().trim() == ""
                &&binding.etWeight.text.toString().trim() == ""
                &&binding.etHeight.text.toString().trim() == ""

    }

    private fun isDataValid(): Boolean {
        return isAgeValid && isWeightValid && isHeightValid
    }

    private fun checkAge() {
        val selectedAge = binding.etAge.text.toString().trim()
        if (selectedAge.isEmpty()) {
            isAgeValid = false
            binding.age.error = getString(R.string.input_age)
        } else
            isAgeValid = true
    }

    private fun checkHeight() {
        val selectedHeight = binding.etHeight.text.toString().trim()
        if (selectedHeight.isEmpty()) {
            isHeightValid = false
            binding.height.error = getString(R.string.input_height)
        } else
            isHeightValid = true
    }

    private fun checkWeight() {
        val selectedWeight = binding.etWeight.text.toString().trim()
        if (selectedWeight.isEmpty()) {
            isWeightValid = false
            binding.weight.error = getString(R.string.input_weight)
        } else
            isWeightValid = true
    }
    override fun onFocusChange(v: View?, isFocused: Boolean) {
        if (v != null) {
            when (v.id) {
                R.id.et_age -> {
                    if (isFocused) {
                        binding.age.isErrorEnabled = false
                        binding.age.error = ""
                    } else {
                        checkAge()
                    }
                }

            }
        }
    }
}