package com.example.healthylivingapp.ui.customview

import android.content.Context
import android.graphics.Rect
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.example.healthylivingapp.R

class InputEmail: AppCompatEditText, View.OnTouchListener  {

    private var isEmailValid: Boolean = false

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                checkEmail()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                checkEmail()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkEmail()
            }
        })
    }

    private fun checkEmail() {
        val email = text?.trim()
        if (email.isNullOrEmpty()) {
            isEmailValid = false
            error = resources.getString(R.string.required_email)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isEmailValid = false
            error = resources.getString(R.string.invalid_email)
        } else {
            isEmailValid = true
        }
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (!focused) checkEmail()
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return false
    }
}