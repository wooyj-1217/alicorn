package com.wooyj.alicorn.ui.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("imageLoadByUrlString")
fun ImageView.setImageLoadByUri(urlString: String) {
    load(urlString)
}

@BindingAdapter("setTimeString")
fun TextView.timeStringFromLong(long:Long){
    val sdf = SimpleDateFormat("a HH:mm", Locale.KOREA).format(long)
    text = sdf
}