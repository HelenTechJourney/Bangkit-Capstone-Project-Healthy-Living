package com.example.healthyliving.helper

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class HtmlToXmlConverter {
    fun convert(html: String): String {
        val doc: Document = Jsoup.parse(html)
        convertElements(doc.body().children())
        return doc.outerHtml()
    }

    private fun convertElements(elements: List<Element>?) {
        elements?.forEach { element ->
            when (element.tagName()) {
                "b" -> element.tagName("strong")
                "i" -> element.tagName("em")
                "font" -> convertFontTag(element)
            }
            convertElements(element.children())
        }
    }

    private fun convertFontTag(element: Element) {
        val style = StringBuilder()
        val sizeAttr = element.attr("size")
        val colorAttr = element.attr("color")
        val faceAttr = element.attr("face")

        if (sizeAttr.isNotEmpty()) {
            style.append("font-size: ${sizeAttr}pt;")
        }

        if (colorAttr.isNotEmpty()) {
            style.append("color: $colorAttr;")
        }

        if (faceAttr.isNotEmpty()) {
            style.append("font-family: $faceAttr;")
        }

        element.tagName("span")
        element.removeAttr("size")
        element.removeAttr("color")
        element.removeAttr("face")

        if (style.isNotEmpty()) {
            element.attr("style", style.toString())
        }

        convertElements(element.children())
    }
}