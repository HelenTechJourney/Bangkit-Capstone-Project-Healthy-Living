package com.example.healthyliving.ui.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthyliving.R
import com.example.healthyliving.databinding.FragmentForm4Binding
import com.example.healthyliving.databinding.FragmentResepBinding

class Form4Fragment : Fragment() {

    private lateinit var binding: FragmentForm4Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForm4Binding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvRec.layoutManager = layoutManager

        return inflater.inflate(R.layout.fragment_form4, container, false)
    }
}