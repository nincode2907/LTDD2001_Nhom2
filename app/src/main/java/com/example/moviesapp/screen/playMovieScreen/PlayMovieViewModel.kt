package com.example.myapplication.screen.PlayMovieScreen

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class PlayMovieViewModel @Inject constructor(
    val player: Player
): ViewModel() {

    init {
        player.prepare()
        player.setMediaItem(
            MediaItem.fromUri( "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        )
    }

    fun playVideo(uri: String) {
        player.setMediaItem(
            MediaItem.fromUri(uri),
        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}
