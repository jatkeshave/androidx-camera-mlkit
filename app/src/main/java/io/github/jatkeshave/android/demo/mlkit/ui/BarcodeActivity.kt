package io.github.jatkeshave.android.demo.mlkit.ui

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.github.florent37.runtimepermission.kotlin.askPermission
import io.github.jatkeshave.android.demo.mlkit.databinding.ActivityBacodeBinding

class BarcodeActivity : AppCompatActivity() {
    private var _binding: ActivityBacodeBinding? = null
    private val binding: ActivityBacodeBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBacodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        askPermission(*REQUIRED_PERMISSION) {
            startCameraPreview()
        }.onDeclined {
            // Handle denied permissions here
        }
    }

    private fun startCameraPreview() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this@BarcodeActivity)

        cameraProviderFuture.addListener(
            {
                val cameraProvider = cameraProviderFuture.get()

                val cameraPreview = Preview.Builder()
                    .build()
                    .also { previewBuilder ->
                        previewBuilder.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
                    }

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this@BarcodeActivity,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    cameraPreview
                )
            },
            ContextCompat.getMainExecutor(this@BarcodeActivity)
        )
    }

    companion object {
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    }
}