/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cai.campus.common.code.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.cai.campus.R
import com.cai.campus.app.BaseApplication
import com.cai.campus.common.code.camera.CameraManager
import com.google.zxing.ResultPoint
import java.util.*

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points. 这是一个位于相机顶部的预览view,它增加了一个外部部分透明的取景框，以及激光扫描动画和结果组件
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
class ViewfinderView(
    context: Context?,
    attrs: AttributeSet?
) : View(context, attrs) {
    private var cameraManager: CameraManager? = null
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var resultBitmap: Bitmap? = null

    // 取景框外的背景颜色
    private val bgColor = ContextCompat.getColor(BaseApplication.getAppContext(), R.color.view_mask)

    // 特征点的颜色
    private val spotColor = ContextCompat.getColor(BaseApplication.getAppContext(), R.color.origin)

    // 提示文字颜色
    private val tipsColor = ContextCompat.getColor(BaseApplication.getAppContext(), R.color.white)

    private var possibleResultPoints: MutableList<ResultPoint>
    private var lastPossibleResultPoints: List<ResultPoint>?

    // 扫描线移动的y
    private var scanLineTop = 0

    // 扫描线移动速度
    private val SCAN_VELOCITY = 2

    // 扫描线
    var scanLight: Bitmap
    fun setCameraManager(cameraManager: CameraManager?) {
        this.cameraManager = cameraManager
    }

    @SuppressLint("DrawAllocation")
    public override fun onDraw(canvas: Canvas) {
        if (cameraManager == null) {
            return  // not ready yet, early draw before done configuring
        }

        // frame为取景框
        val frame = cameraManager!!.framingRect
        val previewFrame = cameraManager!!.framingRectInPreview
        if (frame == null || previewFrame == null) {
            return
        }
        val width = width
        val height = height

        // Draw the exterior (i.e. outside the framing rect) darkened
        // 绘制取景框外的暗灰色的表面，分四个矩形绘制
        paint.color = bgColor
        canvas.drawRect(0f, 0f, width.toFloat(), frame.top.toFloat(), paint) // Rect_1
        canvas.drawRect(
            0f,
            frame.top.toFloat(),
            frame.left.toFloat(),
            frame.bottom + 1.toFloat(),
            paint
        ) // Rect_2
        canvas.drawRect(
            frame.right + 1.toFloat(),
            frame.top.toFloat(),
            width.toFloat(),
            frame.bottom + 1.toFloat(),
            paint
        ) // Rect_3
        canvas.drawRect(
            0f,
            frame.bottom + 1.toFloat(),
            width.toFloat(),
            height.toFloat(),
            paint
        ) // Rect_4
        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            // 如果有二维码结果的Bitmap，在扫取景框内绘制不透明的result Bitmap
            paint.alpha = CURRENT_POINT_OPACITY
            canvas.drawBitmap(resultBitmap!!, null, frame, paint)
        } else {
            drawFrameBounds(canvas, frame)
            drawStatusText(canvas, frame, width)
            drawScanLight(canvas, frame)
            val scaleX = frame.width() / previewFrame.width().toFloat()
            val scaleY = frame.height() / previewFrame.height().toFloat()

            // 绘制扫描线周围的特征点
            val currentPossible: List<ResultPoint> = possibleResultPoints
            val currentLast = lastPossibleResultPoints
            val frameLeft = frame.left
            val frameTop = frame.top
            if (currentPossible.isEmpty()) {
                lastPossibleResultPoints = null
            } else {
                possibleResultPoints = ArrayList(5)
                lastPossibleResultPoints = currentPossible
                paint.alpha = CURRENT_POINT_OPACITY
                paint.color = spotColor
                synchronized(currentPossible) {
                    for (point in currentPossible) {
                        canvas.drawCircle(
                            (frameLeft
                                    + (point.x * scaleX).toInt()).toFloat(),
                            (frameTop
                                    + (point.y * scaleY).toInt()).toFloat(),
                            POINT_SIZE.toFloat(),
                            paint
                        )
                    }
                }
            }
            if (currentLast != null) {
                paint.alpha = CURRENT_POINT_OPACITY / 2
                paint.color = spotColor
                synchronized(currentLast) {
                    val radius = POINT_SIZE / 2.0f
                    for (point in currentLast) {
                        canvas.drawCircle(
                            (frameLeft
                                    + (point.x * scaleX).toInt()).toFloat(), (frameTop
                                    + (point.y * scaleY).toInt()).toFloat(), radius, paint
                        )
                    }
                }
            }

            // Request another update at the animation interval, but only
            // repaint the laser line,
            // not the entire viewfinder mask.
            postInvalidateDelayed(
                ANIMATION_DELAY,
                frame.left - POINT_SIZE,
                frame.top - POINT_SIZE,
                frame.right + POINT_SIZE,
                frame.bottom + POINT_SIZE
            )
        }
    }

    /**
     * 绘制取景框边框
     *
     * @param canvas
     * @param frame
     */
    private fun drawFrameBounds(
        canvas: Canvas,
        frame: Rect
    ) {
        paint.color = Color.WHITE
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
        canvas.drawRect(frame, paint)
        paint.color = resources.getColor(R.color.blue)
        paint.style = Paint.Style.FILL
        val width = 10
        val length = 45

        // 左上角
        canvas.drawRect(
            frame.left - width.toFloat(), frame.top.toFloat(),
            frame.left.toFloat(), frame.top + length.toFloat(), paint
        )
        canvas.drawRect(
            frame.left - width.toFloat(), frame.top - width.toFloat(),
            frame.left + length.toFloat(), frame.top.toFloat(), paint
        )
        // 右上角
        canvas.drawRect(
            frame.right.toFloat(), frame.top.toFloat(),
            frame.right + width.toFloat(), frame.top + length.toFloat(), paint
        )
        canvas.drawRect(
            frame.right - length.toFloat(), frame.top - width.toFloat(),
            frame.right + width.toFloat(), frame.top.toFloat(), paint
        )
        // 左下角
        canvas.drawRect(
            frame.left - width.toFloat(), frame.bottom - length.toFloat(),
            frame.left.toFloat(), frame.bottom.toFloat(), paint
        )
        canvas.drawRect(
            frame.left - width.toFloat(), frame.bottom.toFloat(),
            frame.left + length.toFloat(), frame.bottom + width.toFloat(), paint
        )
        // 右下角
        canvas.drawRect(
            frame.right.toFloat(), frame.bottom - length.toFloat(),
            frame.right + width.toFloat(), frame.bottom.toFloat(), paint
        )
        canvas.drawRect(
            frame.right - length.toFloat(), frame.bottom.toFloat(),
            frame.right + width.toFloat(), frame.bottom + width.toFloat(), paint
        )
    }

    /**
     * 绘制提示文字
     */
    private fun drawStatusText(
        canvas: Canvas,
        frame: Rect,
        width: Int
    ) {
        val textLine1 = resources.getString(
            R.string.viewfinderview_status_text1
        )
        val textLine2 = resources.getString(
            R.string.viewfinderview_status_text2
        )
        val textSize = 45
        val marginTop = 180
        paint.color = tipsColor
        paint.textSize = textSize.toFloat()
        val textWidth1 = paint.measureText(textLine1).toInt()
        canvas.drawText(
            textLine1,
            (width - textWidth1 shr 1.toFloat().toInt()).toFloat(),
            frame.top - marginTop.toFloat(),
            paint
        )
        val textWidth2 = paint.measureText(textLine2).toInt()
        canvas.drawText(
            textLine2,
            (width - textWidth2 shr 1.toFloat().toInt()).toFloat(),
            frame.top - marginTop + 60.toFloat(),
            paint
        )
    }

    /**
     * 绘制移动扫描线
     */
    private fun drawScanLight(
        canvas: Canvas,
        frame: Rect
    ) {
        if (scanLineTop == 0) {
            scanLineTop = frame.top
        }
        if (scanLineTop >= frame.bottom - 20) {
            scanLineTop = frame.top
        } else {
            scanLineTop += SCAN_VELOCITY
        }
        val scanRect =
            Rect(frame.left, scanLineTop, frame.right, scanLineTop + 30)
        canvas.drawBitmap(scanLight, null, scanRect, paint)
    }

    fun drawViewfinder() {
        val resultBitmap = resultBitmap
        this.resultBitmap = null
        resultBitmap?.recycle()
        invalidate()
    }

    fun addPossibleResultPoint(point: ResultPoint) {
        val points = possibleResultPoints
        synchronized(points) {
            points.add(point)
            val size = points.size
            if (size > MAX_RESULT_POINTS) {
                // trim it
                points.subList(0, size - MAX_RESULT_POINTS / 2).clear()
            }
        }
    }

    companion object {
        private const val ANIMATION_DELAY = 10L
        private const val CURRENT_POINT_OPACITY = 0xA0
        private const val MAX_RESULT_POINTS = 20
        private const val POINT_SIZE = 6
    }

    init {

        // Initialize these once for performance rather than calling them every
        // time in onDraw().
        possibleResultPoints = ArrayList(5)
        lastPossibleResultPoints = null
        scanLight = BitmapFactory.decodeResource(
            resources,
            R.drawable.scan_light
        )
    }
}