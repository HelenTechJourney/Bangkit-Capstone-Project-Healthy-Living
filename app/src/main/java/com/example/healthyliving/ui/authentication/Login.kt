package com.example.healthyliving.ui.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.healthyliving.R
import com.example.healthyliving.customview.CustomButton
import com.example.healthyliving.customview.InputEmail
import com.example.healthyliving.customview.InputPassword
import com.example.healthyliving.databinding.ActivityLoginBinding
import com.example.healthyliving.di.ViewModelFactory
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.remote.response.RequestLogin
import com.example.healthyliving.ui.main.MainActivity

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var email: String
    private lateinit var password: String

    private lateinit var myButton: CustomButton
    private lateinit var myEditEmail: InputEmail
    private lateinit var myEditPass: InputPassword

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAction()

        myButton = binding.myButtonLogin
        myEditEmail = binding.emailInput
        myEditPass = binding.passwordInput

        setMyButtonEnable()
        supportActionBar?.title = "Login"

        val pref = UserPreference.getInstance(dataStore)
        val viewModel = ViewModelProvider(this, ViewModelFactory(pref))[DataStoreViewModel::class.java]

        viewModel.getLoginState().observe(this) { state ->
            if (state) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        loginViewModel.message.observe(this) {
            val user = loginViewModel.userLogin.value
            checkResponseLogin(it, user?.resultLogin?.token, viewModel)
        }

        loginViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        myEditEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        myEditPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setMyButtonEnable() {
        val result = myEditEmail.text;myEditPass.text
        myButton.isEnabled = result != null && result.toString().isNotEmpty()
    }

    private fun setAction() {
        binding.comeSignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        binding.myButtonLogin.setOnClickListener {
            binding.emailTextInput.clearFocus()
            binding.passTextInput.clearFocus()

            if (isLoginValid()) {
                email = binding.emailInput.text.toString().trim()
                password = binding.passwordInput.text.toString().trim()
                val user = RequestLogin(
                    email,
                    password
                )
                loginViewModel.getLoginResponse(user)
            }
        }
    }
    private fun isLoginValid(): Boolean {
    return binding.emailInput.isEmailValid && binding.passwordInput.isPasswordValid
    }

    private fun checkResponseLogin(
        message: String,
        token: String?,
        viewModel: DataStoreViewModel
    ) {
        if (message.contains("sebagai")) {
            Toast.makeText(
                this,
                "${getString(R.string.login_success)} $message",
                Toast.LENGTH_LONG
            ).show()
            viewModel.saveLoginState(true)
            if (token != null) viewModel.saveToken(token)
            viewModel.saveName(loginViewModel.userLogin.value?.resultLogin?.nama.toString())
        } else {
            when (message) {
                "Unauthorized" -> {
                    Toast.makeText(this, getString(R.string.unauthorized), Toast.LENGTH_SHORT)
                        .show()
                    binding.emailInput.apply {
                        setText("")
                        requestFocus()
                    }
                    binding.passwordInput.setText("")
                }
                else -> {
                    Toast.makeText(
                        this,
                        "${getString(R.string.message_error)} $message",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}