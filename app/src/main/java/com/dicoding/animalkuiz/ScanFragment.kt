package com.dicoding.animalkuiz

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.tasks.Task
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.android.material.snackbar.Snackbar
import java.io.IOException

class ScanFragment : Fragment() {

    private lateinit var cameraSource: CameraSource
    private lateinit var surfaceView: SurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scan, container, false)

        // Initialize SurfaceView
        surfaceView = view.findViewById(R.id.surface_view)

        // Check and request camera permission
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            // Set up the barcode scanner
            setupBarcodeScanner()
        }

        return view
    }

    // Request permissions callback
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, set up the scanner
                setupBarcodeScanner()
            } else {
                // Permission denied, show an error message
                Snackbar.make(requireView(), "Camera permission required", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupBarcodeScanner() {
        // Create a BarcodeDetector
        val barcodeDetector = BarcodeDetector.Builder(requireContext())
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()

        if (!barcodeDetector.isOperational) {
            // Detector is not operational; handle this case
            Snackbar.make(requireView(), "Barcode scanner could not be set up", Snackbar.LENGTH_SHORT).show()
            return
        }

        // Create a CameraSource to handle the camera preview and barcode detection
        cameraSource = CameraSource.Builder(requireContext(), barcodeDetector)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedPreviewSize(1280, 720)
            .setAutoFocusEnabled(true)
            .build()

        // Start camera preview
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    // Start camera
                    if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource.start(surfaceView.holder)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                // You may need to handle this
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                // Stop the camera when the surface is destroyed
                cameraSource.stop()
            }
        })

        // Set up a Processor for the detector to handle barcode detection
        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                // Release resources
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() > 0) {
                    // Handle the barcode results (this runs on a separate thread)
                    val barcode = barcodes.valueAt(0)
                    requireActivity().runOnUiThread {
                        // Perform actions with the barcode data (e.g., update the UI)
                        // Example: Show the barcode value in a Snackbar
                        Snackbar.make(requireView(), "Barcode: ${barcode.displayValue}", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 101
    }
}
