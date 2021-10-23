package com.example.simplenotes.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simplenotes.data.NoteDatabase
import com.example.simplenotes.data.NoteItem
import com.example.simplenotes.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateNoteViewModel(application: Application) : ViewModel() {

    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getInstance(application).noteDao
        repository = NoteRepository(dao)
    }

    fun onSubmit(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newNote = NoteItem()
            newNote.note = text
            repository.insert(newNote)
        }
    }

    fun onUpdate(text: String,time:Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = NoteItem()
            note.note = text
            note.dateTime = time
            repository.update(note)
        }
    }
}

class CreateNoteViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateNoteViewModel::class.java)) {
            return CreateNoteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}