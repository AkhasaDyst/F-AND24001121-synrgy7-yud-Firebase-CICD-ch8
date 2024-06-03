package com.yudhi.moviedatabase.worker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object WorkerUtils {

    private const val TAG = "WorkerUtils"
    private const val BLUR_RADIUS = 20f

    fun blurBitmap(bitmap: Bitmap, applicationContext: Context): Bitmap {
        lateinit var rsContext: RenderScript

        try {
            val output = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
            rsContext = RenderScript.create(applicationContext, RenderScript.ContextType.NORMAL)

            val input = Allocation.createFromBitmap(rsContext, bitmap)
            val outputAlloc = Allocation.createFromBitmap(rsContext, output)

            val script = ScriptIntrinsicBlur.create(rsContext, Element.U8_4(rsContext))
            script.setRadius(BLUR_RADIUS)
            script.setInput(input)
            script.forEach(outputAlloc)

            outputAlloc.copyTo(output)
            return output
        } finally {
            rsContext.finish()
        }
    }

    fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): Uri {
        val name = "blurred_profile.png"
        val outputDir = File(applicationContext.filesDir, "blur_outputs")
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
        val outputFile = File(outputDir, name)

        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(outputFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, out)
        } catch (e: Exception) {
            Log.e(TAG, "Error writing bitmap", e)
        } finally {
            out?.let {
                try {
                    it.close()
                } catch (ignore: IOException) {
                }
            }
        }
        return Uri.fromFile(outputFile)
    }
}
