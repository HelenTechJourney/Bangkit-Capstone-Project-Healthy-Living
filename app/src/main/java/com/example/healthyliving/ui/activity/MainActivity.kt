package com.example.healthyliving.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.healthyliving.R
import com.example.healthyliving.databinding.ActivityMainBinding
import com.example.healthyliving.ui.fragment.JelajahiFragment
import com.example.healthyliving.ui.fragment.ProfileFragment
import com.example.healthyliving.ui.fragment.ResepFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(JelajahiFragment(dataStore))

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){

                R.id.navigation_jelajahi -> replaceFragment(JelajahiFragment(dataStore))
                R.id.navigation_resep -> replaceFragment(ResepFragment())
                R.id.navigation_profile -> replaceFragment(ProfileFragment(dataStore))

                else ->{}
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, fragment)
        fragmentTransaction.commit()
    }
}