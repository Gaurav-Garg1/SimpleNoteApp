package com.example.simplenotes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(noteItem: NoteItem)

    @Update
    fun update(noteItem: NoteItem)

    @Query("select * from NoteItem where time = :key")
    fun get(key: Long): NoteItem

    @Query("select * from NoteItem order by time desc ")
    fun getAll(): LiveData<List<NoteItem>>

    @Delete
    fun deleteItem(noteItem: NoteItem)
}