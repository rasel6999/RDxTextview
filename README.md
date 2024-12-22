<h1>RDxTextview  is the Advanced Android TextView with super optimize</h1>
   <br/><h3>Support Bulletin Board Code (BBCode) </h3>
    <a href="https://jitpack.io/#rasel6999/RDxTextview"><img src="https://jitpack.io/v/rasel6999/RDxTextview.svg"/></a>
   
   
```bbcode 
    [b]  Bold text  [/b]
    [i]  Italic text  [/i]
    [u]  Underline text  [/u]
    [img] image url [/img]
    [big] Big text[/big]
    [quote]  Blockquote text  [/quote]
    [color=red]  change text color  [/color]
    [url=htts://example.com] url link [/url]
    [user=1] user name [/user]
    Hashtags (#)
    Mentions (@)
    URLs (https://)
    Phone Numbers
    Emails
 ```

<img src="https://i.ibb.co/KrvY58g/Whats-App-Image-2021-08-02-at-11-59-53-PM.jpg"/>


<br/><h3>Support all pre-loaded html tags</h3>

<li>&lt;p&gt;</li> 
<li>&lt;ul&gt;</li> 
<li>&lt;li&gt;</li>

<li>&lt;div&gt;</li>
<li>&lt;span&gt;</li>
<li>&lt;strong&gt;</li>
<li>&lt;b&gt;</li>
<li>&lt;em&gt;</li>
<li>&lt;cite&gt;</li>
<li>&lt;dfn&gt;</li>
<li>&lt;i&gt;</li>
<li>&lt;big&gt;</li>
<li>&lt;small&gt;</li>
<li>&lt;font&gt;</li>
<li>&lt;blockquote&gt;</li>
<li>&lt;tt&gt;</li>
<li>&lt;a&gt;</li>
<li>&lt;u&gt;</li>
<li>&lt;del&gt;</li>
<li>&lt;s&gt;</li>
<li>&lt;strike&gt;</li>
<li>&lt;sup&gt;</li>
<li>&lt;sub&gt;</li>
<li>&lt;h1&gt;</li>
<li>&lt;h2&gt;</li>
<li>&lt;h3&gt;</li>
<li>&lt;h4&gt;</li>
<li>&lt;h5&gt;</li>
<li>&lt;h6&gt;</li>
<li>&lt;img&gt;</li>
<li>&lt;br&gt;</li>



The current minSDK version is API level 14.
 <h2>Setup and Usage</h2>

<b>Gradle: in app</b><br/>
    <a href="https://jitpack.io/#rasel6999/RDxTextview"><img src="https://jitpack.io/v/rasel6999/RDxTextview.svg"/></a>



```xml 

 implementation 'com.github.rasel6999:RDxTextview:{latest version}'
``` 
add maven if not availeble?



```gradle 
   maven { url "https://jitpack.io" } 

``` 


<b>Add RDxTextView to your layout</b>

```xml   

           <com.macwap.rasel.rdxTextView.RDxTextView
                android:id="@+id/tvRDX"
                android:textSize="17sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                />
```
 
```kt  
val rdxTextView:RDxTextView? = findViewById(R.id.tvRDX);

```

<h4> Add one or multiple modes</h4>

```kotlin 
rdxTextView?.addMode(ModeBBCode, ModeBBImg, ModeMention,
ModeBBUser, ModeHashtag, ModeUrl, ModeBBUrl, ModeEmail, ModePhone)
```
<h3>Support Mode</h3>
<li> <b>ModeBBCode</b>    -> for [b] [/b] , [i] [/i] , [u] [/u] , [big] [/big] , [quote] [/quote] ,
<li> <b>ModeBBImg</b>     -> for [img] [/img]
<li> <b>ModeMention</b>   -> for @name
<li> <b>ModeBBUser</b>    -> for [user=id] [/user]
<li> <b>ModeHashtag</b>   -> for #hashtag
<li> <b>ModeUrl</b>       -> for http://example.com support html link
<li> <b>ModeBBUrl</b>     -> for [url=link] [/url]
<li> <b>ModeEmail </b>    -> for mail admin@macwap.com 
<li> <b>ModePhone </b>    -> for +968-98 58 70 40
<li> <b>ModeImageDisable</b>-> for Disable <img> tag  image loading

<h4> Setup Custom color for mode  </h4>
 
          
 

 ```kotlin 
            rdxTextView?.mentionModeColor = ContextCompat.getColor(this,R.color.teal_700)
            rdxTextView?.hashTagModeColor = ContextCompat.getColor(this,R.color.purple_700)
            rdxTextView?.urlModeColor = ContextCompat.getColor(this,R.color.blue)
            rdxTextView?.emailModeColor = ContextCompat.getColor(this,R.color.blue)
```
 
 <h4>Support Custom Color for mode </h4>
<li> <b>mentionModeColor</b>    
<li> <b>hashTagModeColor</b>    
<li> <b>userBbModeColor</b>    
<li> <b>emailModeColor</b>    
<li> <b>phoneModeColor</b>    
<li> <b>urlModeColor</b>    
<li> <b>urlBbModeColor</b>    

 
 
 <h2>You are Done. just set text in view</h2>
 
 
  ```kotlin 
  
          rdxTextView?.text = grdxTextView?.text

```
 
 
 
 
<h4>Set RDxTextView click listener</h4>



  ```kotlin 

        rdxTextView?.onRDxClick { item: RDxLinkItem ->
             if(item.mode==ModeUrl){
                /// do any thing you want

              } 
              
              
            Toast.makeText(this,"" +
                    "Mode = ${item.mode?.modeName}," +
                    " originalText = ${item.originalText}," +
                    " parameterText = ${item.parameterText}," +
                    " valueText = ${item.valueText}," +
                    "   . ", Toast.LENGTH_LONG).show()

        }

```
