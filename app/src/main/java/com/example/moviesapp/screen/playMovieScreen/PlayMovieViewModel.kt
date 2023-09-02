package com.example.myapplication.screen.PlayMovieScreen

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.example.moviesapp.data.moviesData.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@UnstableApi

@HiltViewModel
class PlayMovieViewModel @Inject constructor(
    val videoPlayer: ExoPlayer,
    val movieRepository: MovieRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<VideoDetailUiState>(VideoDetailUiState.Default)
    val uiState: StateFlow<VideoDetailUiState>
        get() = _uiState

    init {
        videoPlayer.repeatMode = Player.REPEAT_MODE_ALL
        videoPlayer.playWhenReady = true
        videoPlayer.prepare()

    }

    fun handleAction(action: VideoDetailAction) {
        when (action) {
            is VideoDetailAction.LoadData -> {
                val videoId = action.videoResource
                loadVideo(videoId)
            }

            is VideoDetailAction.ToggleVideo -> {
                toggleVideo()
            }
            is VideoDetailAction.StopVideo ->{
                stopVideo()
            }

            else -> {

            }
        }
    }

    private fun loadVideo(videoResource: String) {
        _uiState.value = VideoDetailUiState.Loading
        viewModelScope.launch {
            delay(100L)
            playVideo(videoResource)
            _uiState.value = VideoDetailUiState.Success
        }
    }

    private fun playVideo(videoResource: String) {
        val mediaItem = MediaItem.fromUri(videoResource)
        videoPlayer.setMediaItem(mediaItem)
        videoPlayer.play()
        videoPlayer.pause()
    }

    private fun toggleVideo() {
        if (videoPlayer.isLoading) {

        } else {
            if (videoPlayer.isPlaying)
                videoPlayer.pause()
            else
                videoPlayer.play()
        }
    }

    private fun stopVideo() {
        videoPlayer.pause()

    }

    override fun onCleared() {
        super.onCleared()
        videoPlayer.release()
    }
}

sealed interface VideoDetailUiState {
    object Default : VideoDetailUiState
    object Loading : VideoDetailUiState
    object Success : VideoDetailUiState
    data class Error(val msg: String) : VideoDetailUiState
}

sealed class VideoDetailAction {
    data class LoadData(val videoResource: String) : VideoDetailAction()
    object ToggleVideo : VideoDetailAction()
    object StopVideo : VideoDetailAction()

}