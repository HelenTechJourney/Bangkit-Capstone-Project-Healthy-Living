package com.example.healthyliving.helper

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class HtmlToXmlConverter {
    fun convert(html: String): String {
        val doc: Document = Jsoup.parse(html)
        removeHtmlTags(doc.body())
        return doc.body().text()
    }

    private fun removeHtmlTags(element: Element) {
        val childTags: List<Element> = element.children()
        for (childTag in childTags) {
            removeHtmlTags(childTag)
            childTag.unwrap()
        }
    }
}

fun main() {
    // HTML dengan tag dan atribut
    val htmlString = "<div>" +
            "<p description=\"Description text\">Hello <strong>world</strong></p>" +
            "<p detail=\"Detail text\">This is <em>bold</em> and <a href=\"https://example.com\">link</a> text.</p>" +
            "</div>"

    // Membuat instance HtmlToXmlConverter
    val converter = HtmlToXmlConverter()

    // Mengubah HTML menjadi format yang mudah terbaca
    val textOutput = converter.convert(htmlString)

    // Menghilangkan karakter escape XML yang ada pada output
    val xmlOutput = textOutput.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")

    // Membuat XML output dengan elemen tvDescriptionDetail
    val xmlString = "<tvDescriptionDetail>$xmlOutput</tvDescriptionDetail>"

    // Output hasil
    println(xmlString)
}