package com.example.evstropovtestovoeaxis.ui.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.evstropovtestovoeaxis.domain.NoteDomain

@BindingAdapter("setTittle")
fun TextView.setTittle(r: NoteDomain?) {
    r?.let {
        text = it.tittle
    }
}

@BindingAdapter("setContent")
fun TextView.setContent(r: NoteDomain?) {
    r?.let {
        text = it.content
    }
}