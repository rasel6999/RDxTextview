@file:Suppress("DEPRECATION")

package com.macwap.rasel.rdxTextView

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.util.AttributeSet
import android.view.View

class RDxTextView(context: Context, attrs: AttributeSet? = null) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    companion object {
        private const val DEFAULT_COLOR = Color.RED

    }

    private var onRDxClick: ((RDxLinkItem) -> Unit)? = null
    private val modes = mutableSetOf<Mode?>()
    var defaultModeColor = DEFAULT_COLOR
    var mentionModeColor: Int? = null
    var hashTagModeColor: Int? = null
    var userBbModeColor: Int? = null
    var emailModeColor: Int? = null
    var phoneModeColor: Int? = null
    var urlModeColor: Int? = null
    var urlBbModeColor: Int? = null

    init {
        highlightColor = Color.TRANSPARENT
    }

    override fun setText(text: CharSequence?, type: BufferType) {
        if (text?.isEmpty() == true || modes.isNullOrEmpty()) {
            super.setText(text, type)
            return
        }

        val html: String? = bbCode(text)
        val sequence: CharSequence = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                html,
                Html.FROM_HTML_MODE_COMPACT,
                URLImageParser(this, context, this),
                null
            )
        } else {
            Html.fromHtml(html, URLImageParser(this, context, this), null)
        }


        val strBuilder = SpannableStringBuilder(sequence)
        val urls: Array<URLSpan> = strBuilder.getSpans(0, sequence.length, URLSpan::class.java)
        for (span in urls) {

            makeLinkClickable(strBuilder, span)
        }


        movementMethod = LinkMovementMethod.getInstance()
        super.setText(strBuilder, type)
        movementMethod = LinkMovementMethod.getInstance()
    }

    fun addMode(vararg modes: Mode) {
        this.modes.addAll(modes)
    }


    private fun bbCode(string: CharSequence?): String? {
        var text = string
        modes.forEach { mode ->

            when (mode) {

                ModeMention -> {
                    text = text?.replace(MENTION_PATTERN) {
                        "<a href=\"mention://{${it.value}},{${it.groupValues[1]}},{${it.value}}\">${it.value}</a>"
                    }
                }
                ModeBBUser -> {
                    text = text?.replace(BB_USER_PATTERN) {
                        "<a href=\"user://{${it.value}},{${it.groupValues[1]}},{${it.groupValues[2]}}\">${it.groupValues[2]}</a>"
                    }
                }
                ModeHashtag -> {
                    text = text?.replace(HASH_TAG_PATTERN) {
                        "<a href=\"hashtag://{${it.value}},{${it.groupValues[1]}},{${it.value}}\">${it.value}</a>"
                    }
                }
                ModeBBUrl -> {
                    text = text?.replace(BB_URL_PATTERN) {
                        "<a href=\"bbUrl://{${it.value}},{${it.groupValues[1]}},{${it.groupValues[2]}}\">${it.groupValues[2]}</a>"
                    }
                }
                ModeUrl -> {
                    text = text?.replace(URL_PATTERN) {
                        "<a href=\"${it.value}\">${it.value}</a>"
                    }
                }
                ModeEmail -> {
                    text = text?.replace(EMAIL_PATTERN.toRegex()) {
                        "<a href=\"email://{${it.value}}\">${it.value}</a>"
                    }

                }
                ModePhone -> {
                    text = text?.replace(PHONE_PATTERN) {
                        "<a href=\"phone://{${it.value}}\">${it.value}</a>"
                    }

                }
                ModeImageDisable -> {
                    text = text?.replace(IMAGE_PATTERN) { "&empty;" }

                }
                ModeBBCode -> {
                    text = text?.replace(BB_BOLD_PATTERN) { "<b>${it.groupValues[1]}</b>" }
                    text = text?.replace(BB_ITALIC_PATTERN) { "<i>${it.groupValues[1]}</i>" }
                    text = text?.replace(BB_UNDERLINE_PATTERN) { "<u>${it.groupValues[1]}</u>" }
                    text =
                        text?.replace(BB_QUOTE_PATTERN) { "<blockquote>${it.groupValues[1]}</blockquote>" }
                    text = text?.replace(BB_BIG_PATTERN) { "<big>${it.groupValues[1]}</big>" }
                    text = (text as String?)?.replace("[br]", "<br/>")
                    text =
                        text?.replace(BB_COLOR_PATTERN) { "<font color=\"${it.groupValues[1]}\">${it.groupValues[2]}</font>" }

                }
                ModeBBImg -> {
                    text = text?.replace(IMG_URL_PATTERN) {
                        "<img src=\"${it.groupValues[1]}\"/>"
                    }
                }
                else -> {

                }
            }
        }

        return text?.toString()
    }


    private fun makeLinkClickable(strBuilder: SpannableStringBuilder, span: URLSpan?) {
        val start: Int = strBuilder.getSpanStart(span)
        val end: Int = strBuilder.getSpanEnd(span)
        val flags: Int = strBuilder.getSpanFlags(span)
        val clickable: ClickableSpan = object : ClickableSpan() {

            override fun updateDrawState(tp: TextPaint) {
                tp.isUnderlineText = false

                when (span?.url?.substringBefore("://")) {
                    "mention" -> {
                        tp.color = mentionModeColor ?: defaultModeColor
                    }
                    "hashtag" -> {
                        tp.color = hashTagModeColor ?: defaultModeColor

                    }
                    "user" -> {
                        tp.color = userBbModeColor ?: defaultModeColor

                    }
                    "bbUrl" -> {
                        tp.color = urlBbModeColor ?: defaultModeColor

                    }
                    "http", "https" -> {
                        tp.color = urlModeColor ?: defaultModeColor

                    }
                    "email" -> {
                        tp.color = emailModeColor ?: defaultModeColor

                    }
                    "phone" -> {
                        tp.color = phoneModeColor ?: defaultModeColor

                    }
                    else -> {
                        tp.color = urlModeColor ?: defaultModeColor

                    }
                }

            }

            override fun onClick(widget: View) {
                var value1 = ""
                var value2 = ""
                var value3 = ""
                val type = span?.url?.substringBefore("://")
                var index = span?.url?.substringAfter("://")
                if (index?.length !in 0..2) {
                    index = index?.substring(1, index.length - 1)

                }
                val array = index?.split("},{")?.toTypedArray()
                val autoLinkItem: RDxLinkItem
                when (array?.size) {
                    0 -> {
                    }
                    1 -> {
                        value1 = array[0]
                    }
                    2 -> {
                        value1 = array[0]
                        value2 = array[1]

                    }
                    3 -> {
                        value1 = array[0]
                        value2 = array[1]
                        value3 = array[2]
                    }
                    else -> {


                        value1 = array?.get(0) ?: ""
                        value2 = array?.get(1) ?: ""
                        value3 = array?.get(2) ?: ""
                    }

                }


                when (type) {
                    "mention" -> {
                        autoLinkItem =
                            RDxLinkItem(start, end, value1, "", value2, value3, ModeMention)
                    }
                    "hashtag" -> {
                        autoLinkItem =
                            RDxLinkItem(start, end, value1, "", value2, value3, ModeHashtag)
                    }
                    "user" -> {
                        autoLinkItem =
                            RDxLinkItem(start, end, value1, "", value2, value3, ModeBBUser)
                    }
                    "http", "https" -> {
                        autoLinkItem = RDxLinkItem(start, end, span.url, "", "", "", ModeUrl)

                    }
                    "bbUrl" -> {
                        autoLinkItem =
                            RDxLinkItem(start, end, value1, "", value2, value3, ModeBBUrl)

                    }
                    "email" -> {
                        autoLinkItem = RDxLinkItem(start, end, value1, "", "", value1, ModeEmail)

                    }
                    "phone" -> {
                        autoLinkItem = RDxLinkItem(start, end, value1, "", "", value1, ModePhone)

                    }
                    else -> {
                        autoLinkItem = RDxLinkItem(start, end, span?.url, "", "", "", null)

                    }
                }
                postDelayed({ onRDxClick?.invoke(autoLinkItem) }, 10)

            }
        }
        strBuilder.setSpan(clickable, start, end, flags)
        strBuilder.removeSpan(span)
    }

    fun onRDxClick(body: (RDxLinkItem) -> Unit) {
        onRDxClick = body
    }


}