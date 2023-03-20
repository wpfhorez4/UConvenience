package com.git.uconvenience

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan

class UText {
    companion object {
        fun firstTextChange(target: String, color: Int, size: Float = 1f): SpannableStringBuilder {
            val builder = SpannableStringBuilder(target)
            var startIdx = 0
            do {
                if(target.isEmpty()) break
                val colorBlueSpan = ForegroundColorSpan(color)
                val sizeBigSpan = RelativeSizeSpan(size)
                builder.setSpan(colorBlueSpan, startIdx, startIdx.inc(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                builder.setSpan(sizeBigSpan, startIdx, startIdx.inc(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                startIdx = target.indexOf("\n", startIdx.inc()).inc()
            } while(startIdx > 0)
            return builder
        }
    }
}