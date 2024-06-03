package com.yudhi.moviedatabase.viewmodel

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.yudhi.moviedatabase.R
import com.yudhi.moviedatabase.helper.MyDataStore
import com.yudhi.moviedatabase.worker.BlurWorker
import kotlinx.coroutines.launch

class BlurViewModel(application: Application, private val myDataStore: MyDataStore) : AndroidViewModel(application) {

    private val _imageUri = MutableLiveData<Uri>()
    val imageUri: LiveData<Uri> get() = _imageUri

    private val _outputUri = MutableLiveData<Uri>()
    val outputUri: LiveData<Uri> get() = _outputUri

    private val workManager = WorkManager.getInstance(application)

    init {
        viewModelScope.launch {
            myDataStore.getFoto().collect { fotoUri ->
                fotoUri?.let {
                    _imageUri.value = Uri.parse(it)
                }
            }
        }
    }

    fun setImageUri(uri: Uri) {
        _imageUri.value = uri
    }

    fun applyBlur() {
        val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
            .setInputData(createInputDataForUri())
            .build()
        workManager.enqueue(blurRequest)

        workManager.getWorkInfoByIdLiveData(blurRequest.id)
            .observeForever { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    val outputUriString = workInfo.outputData.getString(BlurWorker.KEY_IMAGE_URI)
                    outputUriString?.let {
                        _outputUri.value = Uri.parse(it)
                        Log.d("D", "BLURRED")
                        viewModelScope.launch {
                            myDataStore.saveFoto(it)
                        }
                    }
                }
            }
    }

    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        _imageUri.value?.let {
            builder.putString(BlurWorker.KEY_IMAGE_URI, it.toString())
        }
        return builder.build()
    }
}
