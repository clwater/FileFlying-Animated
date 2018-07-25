package com.clwater.fileflying_animated

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FileFlyingView : View {
    constructor(context: Context, radius: Float) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    var perIndex: Float = 0.0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        canvas.translate(width.toFloat() / 2, height.toFloat() / 2)
        val paint = Paint()
        paint.textSize = 20f
        canvas.drawText(perIndex.toString(), 0f, 0f, paint)

    }


    fun satrtView() {
        val timing = 1000 * 15
        doAsync {
            kotlin.run {
                while (true) {
                    uiThread {
                        startAnimator(timing.toLong())
                    }
                    Thread.sleep(timing.toLong())
                }
            }
        }
    }

    fun startAnimator(timing: Long) {
        val va = ValueAnimator.ofFloat(0F, 1F)
        va.duration = timing
        va.addUpdateListener { animation ->
            perIndex = animation.animatedValue as Float
            perIndex = perIndex
            invalidate()
        }
        va.start()
    }
}