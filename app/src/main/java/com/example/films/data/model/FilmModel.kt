package com.example.films.data.model

import android.os.Parcelable
import android.os.Parcel


data class FilmModel (var id: Int, var name: String, var release: String, var playtime: String, var description: String, var plot: String, var poster: String, var gif: String): Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(release)
        parcel.writeString(playtime)
        parcel.writeString(description)
        parcel.writeString(plot)
        parcel.writeString(poster)
        parcel.writeString(gif)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilmModel> {
        override fun createFromParcel(parcel: Parcel): FilmModel {
            return FilmModel(
                id = parcel.readInt(),
                name = parcel.readString() ?: "",
                release = parcel.readString()?: "",
                playtime = parcel.readString()?: "",
                description = parcel.readString()?: "",
                plot = parcel.readString()?: "",
                poster = parcel.readString()?: "",
                gif = parcel.readString()?: ""
            )
        }

        override fun newArray(size: Int): Array<FilmModel?> {
            return arrayOfNulls(size)
        }
    }
}