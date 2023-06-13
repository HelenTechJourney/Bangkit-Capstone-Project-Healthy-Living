package com.example.healthyliving.ui.detail

import com.example.healthyliving.helper.HtmlToXmlConverter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.healthyliving.databinding.ActivityDetailArticleBinding
import com.example.healthyliving.remote.response.ArtikelItem

@Suppress("DEPRECATION")
class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Artikel"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailArticle = intent.getParcelableExtra<ArtikelItem>(EXTRA_ARTICLE) as ArtikelItem
        setDetailArticle(detailArticle)
    }
    private fun setDetailArticle(detailArticle: ArtikelItem) {
        binding.apply {
            tvDetailName.text = detailArticle.judul
            val htmlToXmlConverter = HtmlToXmlConverter()
            val convertedDescription = htmlToXmlConverter.convert(detailArticle.deskripsi)
            tvDetailDescription.text = android.text.Html.fromHtml(convertedDescription).toString()
            tvDetailReference.text = detailArticle.referensi
        }
        Glide.with(this)
            .load(detailArticle.gambar)
            .into(binding.ivDetailPhoto)
    }
//    private fun removeHtmlTags(html: String): String {
//        // Parse teks HTML menjadi objek Jsoup Document
//        val document: Document = Jsoup.parse(html)
//
//        // Hapus tag HTML
//        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml)
//        document.outputSettings().escapeMode(org.jsoup.nodes.Entities.EscapeMode.xhtml)
//        document.select("a").unwrap()
//
//        return document.outerHtml()
//    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }
}