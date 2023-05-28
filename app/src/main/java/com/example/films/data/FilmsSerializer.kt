package com.example.films.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.films.Films
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object FilmsSerializer: Serializer<Films> {

    override val defaultValue: Films = Films.getDefaultInstance()


    override suspend fun readFrom(input: InputStream): Films {
        try {
            return Films.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: Films, output: OutputStream) {
       t.writeTo(output)
    }

}

val Context.filmsDataStore: DataStore<Films> by dataStore(
    fileName = "user_store.pb",
    serializer = FilmsSerializer
)