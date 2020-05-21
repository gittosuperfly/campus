package com.cai.campus.common.code

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.common.code.android.*
import com.cai.campus.common.code.camera.CameraManager
import com.cai.campus.common.code.view.ViewfinderView
import com.cai.campus.common.router.RequestCode
import com.cai.campus.common.router.RouterPath
import com.google.zxing.BarcodeFormat
import com.google.zxing.DecodeHintType
import com.google.zxing.Result
import java.io.IOException

/**
 * 这个activity打开相机，在后台线程做常规的扫描；它绘制了一个结果view来帮助正确地显示条形码，在扫描的时候显示反馈信息，
 * 然后在扫描成功的时候覆盖扫描结果
 */
@Route(path = RouterPath.SCAN_QR_CODE)
class CaptureActivity : Activity(), SurfaceHolder.Callback {
    // 相机控制
    var cameraManager: CameraManager? = null
        private set
    private var handler: CaptureActivityHandler? = null
    var viewfinderView: ViewfinderView? = null
        private set
    private var hasSurface = false
    private var source: IntentSource? = null
    private var decodeFormats: Collection<BarcodeFormat>? = null
    private val decodeHints: Map<DecodeHintType, *>? = null
    private var characterSet: String? = null

    // 电量控制
    private var inactivityTimer: InactivityTimer? = null


    fun getHandler(): Handler? {
        return handler
    }

    fun drawViewfinder() {
        viewfinderView!!.drawViewfinder()
    }

    /**
     * OnCreate中初始化一些辅助类，如InactivityTimer（休眠）、Beep（声音）以及AmbientLight（闪光灯）
     */
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        // 保持Activity处于唤醒状态
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.capture)
        hasSurface = false
        inactivityTimer = InactivityTimer(this)
    }

    override fun onResume() {
        super.onResume()

        // CameraManager必须在这里初始化，而不是在onCreate()中。
        // 这是必须的，因为当我们第一次进入时需要显示帮助页，我们并不想打开Camera,测量屏幕大小
        // 当扫描框的尺寸不正确时会出现bug
        cameraManager = CameraManager(application)
        viewfinderView = findViewById<View>(R.id.viewfinder_view) as ViewfinderView
        viewfinderView!!.setCameraManager(cameraManager)
        handler = null
        val surfaceView =
            findViewById<View>(R.id.preview_view) as SurfaceView
        val surfaceHolder = surfaceView.holder
        if (hasSurface) {
            // activity在paused时但不会stopped,因此surface仍旧存在；
            // surfaceCreated()不会调用，因此在这里初始化camera
            initCamera(surfaceHolder)
        } else {
            // 重置callback，等待surfaceCreated()来初始化camera
            surfaceHolder.addCallback(this)
        }
        inactivityTimer!!.onResume()
        source = IntentSource.NONE
        decodeFormats = null
        characterSet = null
    }

    override fun onPause() {
        if (handler != null) {
            handler!!.quitSynchronously()
            handler = null
        }
        inactivityTimer!!.onPause()
        cameraManager!!.closeDriver()
        if (!hasSurface) {
            val surfaceView =
                findViewById<View>(R.id.preview_view) as SurfaceView
            val surfaceHolder = surfaceView.holder
            surfaceHolder.removeCallback(this)
        }
        super.onPause()
    }

    override fun onDestroy() {
        inactivityTimer!!.shutdown()
        super.onDestroy()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (!hasSurface) {
            hasSurface = true
            initCamera(holder)
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        hasSurface = false
    }

    override fun surfaceChanged(
        holder: SurfaceHolder, format: Int, width: Int,
        height: Int
    ) {
    }

    /**
     * 扫描成功，处理反馈信息
     *
     * @param rawResult
     * @param barcode
     * @param scaleFactor
     */
    fun handleDecode(rawResult: Result, barcode: Bitmap?, scaleFactor: Float) {
        inactivityTimer!!.onActivity()
        val fromLiveScan = barcode != null
        //这里处理解码完成后的结果，此处将参数回传到Activity处理
        if (fromLiveScan) {
            val intent = intent
            intent.putExtra("codedContent", rawResult.text)
            intent.putExtra("codedBitmap", barcode)
            setResult(RequestCode.SCAN_QR_CODE, intent)
            finish()
        }
    }

    /**
     * 初始化Camera
     *
     * @param surfaceHolder
     */
    private fun initCamera(surfaceHolder: SurfaceHolder?) {
        checkNotNull(surfaceHolder) { "No SurfaceHolder provided" }
        if (cameraManager!!.isOpen) {
            return
        }
        try {
            // 打开Camera硬件设备
            cameraManager!!.openDriver(surfaceHolder)
            // 创建一个handler来打开预览，并抛出一个运行时异常
            if (handler == null) {
                handler = CaptureActivityHandler(
                    this,
                    decodeFormats,
                    decodeHints,
                    characterSet,
                    cameraManager
                )
            }
        } catch (ioe: IOException) {
            Log.w(TAG, ioe)
            displayFrameworkBugMessageAndExit()
        } catch (e: RuntimeException) {
            Log.w(TAG, "Unexpected error initializing camera", e)
            displayFrameworkBugMessageAndExit()
        }
    }

    /**
     * 显示底层错误信息并退出应用
     */
    private fun displayFrameworkBugMessageAndExit() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(getString(R.string.msg_camera_framework_bug))
        builder.setPositiveButton(
            R.string.button_ok,
            FinishListener(this)
        )
        builder.setOnCancelListener(FinishListener(this))
        builder.show()
    }

    companion object {
        private val TAG = CaptureActivity::class.java.simpleName
    }
}