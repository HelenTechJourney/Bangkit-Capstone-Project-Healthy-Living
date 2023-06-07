package com.example.healthyliving.ui.fragment

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.healthyliving.databinding.FragmentDialogBinding

class DialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding
    private lateinit var btnChoose: TextView
    private lateinit var descBmi: TextView
    private lateinit var descBmr: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = binding.btnChoose
        descBmi = binding.dialogDesc
        descBmr = binding.dialogDesc2
        btnChoose.setOnClickListener {
            dialog?.cancel()
        }
    }
}