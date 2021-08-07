@file:Suppress("unused")

package com.macwap.rasel.rdxTextView

import android.os.Build
import android.util.Patterns
import androidx.annotation.RequiresApi
import java.util.regex.Pattern


internal val MENTION_PATTERN  	= "(?:^|\\s|$|[.])@(\\w+)".toRegex()
internal val HASH_TAG_PATTERN  	= "(?:^|\\s|$|[.])#(\\w+)".toRegex()
internal val URL_PATTERN 		= "(^|[\\s.:;?\\-\\]<(])((https?://|www\\.|pic\\.)[-\\w;/?:@&=+\$|_.!~*'()\\[\\]%#,☺]+[\\w/#](\\(\\))?)(?=\$|[\\s',|().:;?\\-\\[\\]>])".toRegex()
internal val PHONE_PATTERN  	= "(?:^|\\s|\$|[.])[+0-9()\\- &plus;]{7,19}(?:^|\\s|\$|[.])".toRegex()
internal val IMAGE_PATTERN 		= "<img src=(.*?)/>".toRegex()
internal val BB_BOLD_PATTERN 	= "\\[b](.*?)\\[/b]".toRegex()
internal val BB_ITALIC_PATTERN 	= "\\[i](.*?)\\[/i]".toRegex()
internal val BB_UNDERLINE_PATTERN = "\\[u](.*?)\\[/u]".toRegex()
internal val BB_QUOTE_PATTERN 	= "\\[quote](.*?)\\[/quote]".toRegex()
internal val BB_USER_PATTERN  	= "\\[user=(.*?)](.*?)\\[/user]".toRegex()
internal val BB_URL_PATTERN  	= "\\[url=(.*?)](.*?)\\[/url]".toRegex()
internal val IMG_URL_PATTERN  	= "\\[img](.*?)\\[/img]".toRegex()
internal val BB_BIG_PATTERN 	= "\\[big](.*?)\\[/big]".toRegex()
internal val BB_COLOR_PATTERN  	= "\\[color=(.*?)](.*?)\\[/color]".toRegex()
@RequiresApi(Build.VERSION_CODES.FROYO)
internal val EMAIL_PATTERN: Pattern = Patterns.EMAIL_ADDRESS


internal val HASH_TAG_PATTERN_NO_MB = "(^|[\\s.:;?\\-\\]<(])(?<![a-zA-Z0-9_])#(?=[0-9_]*[a-zA-Z])[a-zA-Z0-9_]+".toRegex()
internal val URL_PATTERN_OLD = Pattern.compile("(^|[\\s.:;?\\-\\]<\\(])" +
        "((https?://|www\\.|pic\\.)[-\\w;/?:@&=+$\\|\\_.!~*\\|'()\\[\\]%#,☺]+[\\w/#](\\(\\))?)" +
        "(?=$|[\\s',\\|\\(\\).:;?\\-\\[\\]>\\)])")


