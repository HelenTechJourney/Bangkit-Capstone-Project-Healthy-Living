package com.example.healthyliving.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.healthyliving.databinding.ActivityDetailRecipeBinding
import com.example.healthyliving.helper.HtmlToXmlConverter
import com.example.healthyliving.remote.response.ResepItem

@Suppress("DEPRECATION")
class DetailRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Resep"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailRecipe = intent.getParcelableExtra<ResepItem>(EXTRA_RECIPE) as ResepItem
        setDetailRecipe(detailRecipe)
    }
    private fun setDetailRecipe(detailRecipe: ResepItem) {
        binding.apply {
            tvDetailName.text = detailRecipe.judul
            val htmlToXmlConverter = HtmlToXmlConverter()
            tvDetailDescription.text = htmlToXmlConverter.convert(detailRecipe.deskripsi)
            tvDetailReference.text = detailRecipe.referensi
        }
        Glide.with(this)
            .load(detailRecipe.gambar)
            .into(binding.ivDetailPhoto)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_RECIPE = "extra_recipe"
    }
}