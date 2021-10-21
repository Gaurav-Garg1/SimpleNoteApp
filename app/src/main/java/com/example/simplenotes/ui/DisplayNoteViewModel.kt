package com.example.simplenotes.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplenotes.data.NoteDatabase
import com.example.simplenotes.data.NoteItem
import com.example.simplenotes.data.NoteRepository

class DisplayNoteViewModel(application: Application) :ViewModel() {
    val notes: LiveData<List<NoteItem>>
    private val repository : NoteRepository

    init {
        val dao = NoteDatabase.getInstance(application).noteDao
        repository = NoteRepository(dao)
        notes = repository.allNotes
    }
}
class DisplayNoteViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayNoteViewModel::class.java)) {
            return DisplayNoteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}