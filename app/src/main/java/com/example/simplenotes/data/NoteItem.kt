package com.example.simplenotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteItem(
    @PrimaryKey @ColumnInfo(name = "time")
    var note : String = "",
    val dateTime: Long = System.currentTimeMillis()
)