package com.example.simplenotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteItem(
    var note : String = "",
    @PrimaryKey @ColumnInfo(name = "time")
    var dateTime: Long = System.currentTimeMillis()
)