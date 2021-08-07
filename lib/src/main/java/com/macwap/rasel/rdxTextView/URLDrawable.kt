@file:Suppress("DEPRECATION")

package com.macwap.rasel.rdxTextView

import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

class URLDrawable : BitmapDrawable() {
    var drawable: Drawable? = null
    override fun draw(canvas: Canvas) {
        if (drawable != null) {
            drawable?.draw(canvas)
        }
    }
}