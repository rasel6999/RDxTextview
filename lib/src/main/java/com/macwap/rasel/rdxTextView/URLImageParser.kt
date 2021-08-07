@file:Suppress("DEPRECATION")

package com.macwap.rasel.rdxTextView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.text.Html
import android.util.Base64
import android.view.Gravity
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.macwap.rasel.R
import java.io.InputStream
import java.net.URL

class URLImageParser(var container: View, private var context: Context, var textView: RDxTextView) :
    Html.ImageGetter {


    @SuppressLint("NewApi")
	override fun getDrawable(source: String): Drawable {
        return if (source.matches("data:image.*base64.*".toRegex())) {
            val base64Source = source.replace("data:image.*base64".toRegex(), "")
            val data = Base64.decode(base64Source, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            val image: Drawable = BitmapDrawable(context.resources, bitmap)
            image.setBounds(0, 0, image.intrinsicWidth, image.intrinsicHeight)
            image
        } else if(source == "ic_verified.png"){
            var id = 0

                id = R.drawable.ic_verified


            val bitmap = AppCompatResources.getDrawable(context, id)?.toBitmap()
            val d: BitmapDrawable? =  bitmap?.toDrawable(context.resources)
            d?.bounds = Rect(-10, 0, 50, 50)
            d?.gravity = Gravity.CENTER_VERTICAL
            val image: Drawable = d!!
            //image.setBounds(-0, -30, 50, 50)
            image

        }else {
            val urlDrawable = URLDrawable()
            val asyncTask = ImageGetterAsyncTask(urlDrawable)
            asyncTask.execute(source)
            urlDrawable //return reference to URLDrawable where We will change with actual image from the src tag
        }
    }

    @SuppressLint("StaticFieldLeak", "NewApi")
    inner class ImageGetterAsyncTask(private var urlDrawable: URLDrawable) :
        AsyncTask<String?, Void?, Drawable?>() {

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: String?): Drawable? {
            val source = params[0]
            return fetchDrawable(source)
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Drawable?) {
            result?.intrinsicWidth?.let {
                urlDrawable.setBounds(
                    0,
                    0,
                    it,
                    result.intrinsicHeight
                )  //set the correct bound according to the result from HTTP call
                urlDrawable.drawable =
                    result //change the reference of the current drawable to the result from the HTTP call
                container.invalidate() //redraw the image by invalidating the container

                textView.minWidth = result.intrinsicWidth
                textView.minHeight = result.intrinsicHeight

            }

        }

        private fun fetchDrawable(urlString: String?): Drawable? {


            return try {
                val `is` = URL(urlString).content as InputStream
                val drawable = Drawable.createFromStream(`is`, "src")
                drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                drawable

            } catch (e: Exception) {
                null
            }
        }


    }
}