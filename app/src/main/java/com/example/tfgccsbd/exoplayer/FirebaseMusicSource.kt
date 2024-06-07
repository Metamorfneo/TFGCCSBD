package com.example.tfgccsbd.exoplayer

import android.provider.MediaStore.Audio
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import androidx.core.net.toUri
import com.example.tfgccsbd.data.remotes.MusicDatabe
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.hls.DefaultHlsDataSourceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class FirebaseMusicSource @Inject constructor(
    private val musicDatabase: MusicDatabe
){

    private val onReadyListener = mutableListOf<(Boolean) -> Unit>()

    var songs = emptyList<MediaMetadataCompat>()

    suspend fun fetchMediaData() = withContext(Dispatchers.IO){
        state = State.STATE_INITIALIZED
        val allSongs = musicDatabase.getAllSongs()
        songs = allSongs.map { song ->
            MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST , song.subtitle)
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID , song.mediaId)
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE , song.title)
                .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE , song.title)
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI , song.songUrl)
                .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE , song.subtitle)
                .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION , song.subtitle)
                .build()
        }
        state = State.STATE_INITIALIZED
    }

    fun asMediaSource(dataSourceFactory: DefaultHlsDataSourceFactory) : ConcatenatingMediaSource{
        val concatenatingMediaSource = ConcatenatingMediaSource()
        songs.forEach { song ->
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
                MediaItem.fromUri(song.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI)))
            concatenatingMediaSource.addMediaSource(mediaSource)
        }
        return concatenatingMediaSource
    }

    fun asMediaItems() = songs.map {song ->
        val desc = MediaDescriptionCompat.Builder()
            .setMediaId(song.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI).toUri().toString())
            .setTitle(song.description.title)
            .setSubtitle(song.description.subtitle)
            .setMediaId(song.description.mediaId)
            .build()
        MediaBrowserCompat.MediaItem(desc, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE)
    }

    private var state : State = State.STATE_CREATED
        set(value){
            if (value == State.STATE_INITIALIZED || value == State.STATE_ERROR) {
                synchronized(onReadyListener) {
                    field = value
                    onReadyListener.forEach { listener ->
                        listener(state == State.STATE_INITIALIZED)
                    }
            }
        }else{
            field = value
            }

            fun whenReady(action: (Boolean) -> Unit) : Boolean{
                if (state == State.STATE_CREATED || state == State.STATE_INITIALIZING){
                    onReadyListener += action
                    return false
                }else{
                    action(state == State.STATE_INITIALIZED)
                    return true
                }

            }            }
}

enum class State {
    STATE_CREATED,
    STATE_INITIALIZING,
    STATE_INITIALIZED,
    STATE_ERROR
}
