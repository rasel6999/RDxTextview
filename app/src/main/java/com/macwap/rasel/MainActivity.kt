package com.macwap.rasel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.macwap.rasel.rdxTextView.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rdxTextView :RDxTextView?= findViewById(R.id.tvRDX)

        ////set mode you want
        rdxTextView?.addMode(ModeBBCode,ModeBBImg, ModeMention, ModeBBUser, ModeHashtag,ModeUrl,ModeBBUrl,
            ModeEmail,ModePhone,/*ModeImageDisable*/)

       ///set custom color for mode if you need
        rdxTextView?.mentionModeColor = ContextCompat.getColor(this,R.color.teal_700)
        rdxTextView?.hashTagModeColor = ContextCompat.getColor(this,R.color.purple_700)
        rdxTextView?.userBbModeColor = ContextCompat.getColor(this,R.color.teal_200)
        rdxTextView?.urlModeColor = ContextCompat.getColor(this,R.color.blue)
        rdxTextView?.emailModeColor = ContextCompat.getColor(this,R.color.blue)


        /// after intalize set text.. remember set text after mode* and color setup
        rdxTextView?.text =getString(R.string.mainText)


        //// click lisener
        rdxTextView?.onRDxClick { item: RDxLinkItem ->
        /*     if(item.mode==ModeUrl){
                /// do any thing you want

              }*/
            Toast.makeText(this,"" +
                    "Mode = ${item.mode?.modeName}," +
                    " originalText = ${item.originalText}," +
                    " parameterText = ${item.parameterText}," +
                    " valueText = ${item.valueText}," +
                    "   . ", Toast.LENGTH_LONG).show()

        }

    }

}