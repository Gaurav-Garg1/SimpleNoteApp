package com.example.simplenotes.data

//The single source of Truth for data when the data also stored on
//cloud database and offline cached
class NoteRepository(private val noteDao: NoteDao) {

    val allNotes = noteDao.getAll()

    fun insert(note: NoteItem){
        noteDao.insert(note)
    }

    fun delete(note: NoteItem){
        noteDao.deleteItem(note.dateTime)
    }

    fun get(note: NoteItem){
        noteDao.get(note.dateTime)
    }

    fun update(note: NoteItem){
        noteDao.update(note)
    }

}