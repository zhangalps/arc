package com.alone.news.arumenu.autoview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.widget.TextView
import com.arc.news.utils.util.LogUtils


/**
 * package : com.alone.news.arumenu.autoview
 * anthor : 张贺岗
 * Date : 2019/3/21
 * Use : 滑动改变view
 */
class SlidingTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {
    var isSliding = false
    var clickY: Float = 0.0F
    var txBottom: Int = 0
    val TAG: String = "SLIDING"

    var txHeight: Int = 0
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val action = event!!.action
        LogUtils.e(TAG, "onTouchEvent==$action")
        when (action) {
            ACTION_DOWN -> {
                clickY = event.y
                LogUtils.e(TAG, "ClickY==$clickY")
            }
            ACTION_MOVE -> slidingMove(event.y)

        }
        return true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (txBottom == 0) {
            txBottom = this.height
            txHeight = ((this.lineHeight + lineSpacingMultiplier) * lineCount + paddingTop * 2).toInt()
            LogUtils.e(TAG, "txHeight==$txHeight,linecount==$lineCount,lineHeight==$lineHeight,lineSpacingExtra==$lineSpacingExtra,lineSpacingMultiplier==$lineSpacingMultiplier")
        }
        if (lineCount >= 3) {
            isSliding = true
        }
    }

    /**
     * 滑动改变位置
     */
    fun slidingMove(locationY: Float) {
        LogUtils.e(TAG, "locationY==$locationY")
        if (clickY != 0.0F) {
            val slidingY = this.clickY - locationY
            val height = this.height
            val width = this.width

            LogUtils.e(TAG, "locationY==$locationY,slidingY===$slidingY,height===$height,Width===$width==bottom==$txBottom")
            val layoutParams = this.layoutParams
            if ((height + slidingY).toInt() <= txHeight) {
                layoutParams.height = (height + slidingY).toInt()
            } else {
                layoutParams.height = txHeight
            }
            layoutParams.width = width
            if (layoutParams.height >= txBottom && isSliding && (layoutParams.height) <= txHeight) {
                this.layoutParams = layoutParams
            }
        }
    }


}