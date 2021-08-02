package com.macwap.rasel.rdxTextView

sealed class Mode(val modeName: String)

object ModeHashtag  : Mode("Hashtag")
object ModeMention  : Mode("Mention")
object ModeUrl      : Mode("Url")
object ModePhone    : Mode("Phone")
object ModeEmail    : Mode("Email")
object ModeBBUser   : Mode("BBUser")
object ModeBBUrl    : Mode("BBUrl")
object ModeBBCode   : Mode("BBCode")
object ModeBBImg    : Mode("BBImg")
object ModeImageDisable: Mode("ModeImageDisable")

