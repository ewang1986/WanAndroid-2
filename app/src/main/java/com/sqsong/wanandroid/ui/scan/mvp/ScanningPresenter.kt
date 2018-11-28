package com.sqsong.wanandroid.ui.scan.mvp

import android.graphics.Bitmap
import android.view.SurfaceHolder
import com.google.zxing.Result
import com.google.zxing.ResultPoint
import com.sqsong.wanandroid.mvp.BasePresenter
import com.sqsong.wanandroid.mvp.IModel
import com.sqsong.wanandroid.util.zxing.CaptureHandler
import com.sqsong.wanandroid.util.zxing.ScanningResultListener
import com.sqsong.wanandroid.util.zxing.camera.CameraManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ScanningPresenter @Inject constructor(private val disposable: CompositeDisposable) :
        BasePresenter<ScanningContract.View, IModel>(null, disposable), ScanningResultListener, SurfaceHolder.Callback {

    private var hasSurface = false
    private lateinit var mCameraManager: CameraManager
    private var mCaptureHandler: CaptureHandler? = null

    override fun onAttach(view: ScanningContract.View) {
        super.onAttach(view)
    }

    fun onResume() {
        mCameraManager = CameraManager(mView.getAppContext().applicationContext)
        mView.setViewCameraManager(mCameraManager)

        val surfaceHolder = mView.getSurfaceView().holder
        if (hasSurface) {
            initCamera(surfaceHolder)
        } else {
            surfaceHolder.addCallback(this)
        }
    }

    fun onPause() {
        mCaptureHandler?.quitSynchronously()
        mCaptureHandler = null

        mCameraManager.closeDriver()
        if (!hasSurface) {
            mView.getSurfaceView().holder.removeCallback(this)
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        if (holder == null) return
        if (!hasSurface) {
            hasSurface = true
            initCamera(holder)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        // do nothing.
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        hasSurface = false
    }

    private fun initCamera(surfaceHolder: SurfaceHolder) {
        if (mCameraManager.isOpen()) return

        try {
            mCameraManager.openDriver(surfaceHolder)
            if (mCaptureHandler == null) {
                mCaptureHandler = CaptureHandler(mCameraManager, this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun foundPossibleResultPoint(points: ResultPoint?) {

    }

    override fun handleDecode(result: Result, barcodeBitmap: Bitmap?, scaleFactor: Float) {

    }

}