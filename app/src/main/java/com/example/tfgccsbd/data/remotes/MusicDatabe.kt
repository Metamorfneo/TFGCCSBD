package com.example.tfgccsbd.data.remotes

import com.example.tfgccsbd.data.entities.Song
import com.example.tfgccsbd.other.Constants.SONG_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MusicDatabe {

    private val firestore = FirebaseFirestore.getInstance()

    private val songCollection = firestore.collection(SONG_COLLECTION)

    suspend fun getAllSongs() : List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

}