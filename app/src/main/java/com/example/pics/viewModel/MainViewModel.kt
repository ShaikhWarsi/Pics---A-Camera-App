package com.example.pics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _photos = MutableStateFlow<List<File>>(emptyList())
    val photos = _photos.asStateFlow()

    private val _videos = MutableStateFlow<List<File>>(emptyList())
    val videos = _videos.asStateFlow()

    private val _isRecording = MutableStateFlow(false)
    val isRecording = _isRecording.asStateFlow()

    private val _isPaused = MutableStateFlow(false)
    val isPaused = _isPaused.asStateFlow()

    init {
        loadMedia()
    }

    private fun loadMedia() {
        val files = getApplication<Application>().filesDir.listFiles()
        _photos.value = files?.filter { it.extension == "jpg" }?.sortedByDescending { it.lastModified() } ?: emptyList()
        _videos.value = files?.filter { it.extension == "mp4" }?.sortedByDescending { it.lastModified() } ?: emptyList()
    }

    fun onTakePhoto(file: File) {
        _photos.value = listOf(file) + _photos.value
    }

    fun onVideoRecorded(file: File) {
        _videos.value = listOf(file) + _videos.value
    }

    fun setRecording(recording: Boolean) {
        _isRecording.value = recording
        if (!recording) {
            _isPaused.value = false
        }
    }

    fun setPaused(paused: Boolean) {
        _isPaused.value = paused
    }

    // TODO: Persist images using Room or File storage
}
