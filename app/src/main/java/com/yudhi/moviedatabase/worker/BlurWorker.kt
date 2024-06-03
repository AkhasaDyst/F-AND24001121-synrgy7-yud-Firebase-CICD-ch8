package com.yudhi.moviedatabase.worker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.yudhi.moviedatabase.worker.WorkerUtils
import java.io.FileOutputStream

class BlurWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val appContext = applicationContext
        val resourceUri = inputData.getString(KEY_IMAGE_URI)

        return try {
            if (resourceUri.isNullOrEmpty()) {
                throw IllegalArgumentException("Invalid input uri")
            }

            val resolver = appContext.contentResolver
            val picture = BitmapFactory.decodeStream(
                resolver.openInputStream(Uri.parse(resourceUri))
            )

            val output = WorkerUtils.blurBitmap(picture, appContext)
            val outputUri = WorkerUtils.writeBitmapToFile(appContext, output)

            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
            Result.success(outputData)
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }

    companion object {
        const val KEY_IMAGE_URI = "IMAGE_URI"
    }
}
