package com.wooyj.alicorn.ui.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


@BindingAdapter("imageLoadByUrlString")
fun ImageView.setImageLoadByUri(urlString: String) {
    load(urlString)
}

@BindingAdapter("setTimeString")
fun TextView.timeStringFromLong(long: Long) {
    val sdf = SimpleDateFormat("a HH:mm", Locale.KOREA).format(long)
    text = sdf
}

@BindingAdapter("setTimeStringByToday")
fun TextView.timeStringByToday(lastTime: Long) {
    val today = Date()
    val lastTimeDate = Date(lastTime)
    val betweenTime = (today.time - lastTimeDate.time) / 1000 / 60
    val betweenHour = betweenTime / 60
    val betweenDay = betweenHour / 24
    val betweenYear = betweenDay / 365

    text = when (betweenTime) {
        in 0L until 1L
        -> {
            "방금 전"
        }
        in 1L until 60L
        -> {
            "${betweenTime}분 전"
        }
        else -> {
            when (betweenHour) {
                in 1L until 24L -> {
                    "${betweenHour}시간 전"
                }
                else -> {
                    when (betweenDay) {
                        1L -> {
                            "어제"
                        }
                        in 2 until 365 -> {
                            "${betweenDay}일 전"
                        }
                        else -> {
                            "${betweenYear}년 전"
                        }
                    }
                }
            }
        }
    }
}