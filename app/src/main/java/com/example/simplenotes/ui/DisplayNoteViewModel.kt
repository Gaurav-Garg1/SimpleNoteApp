package com.example.simplenotes.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simplenotes.data.NoteDatabase
import com.example.simplenotes.data.NoteItem
import com.example.simplenotes.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DisplayNoteViewModel(application: Application) :ViewModel() {
    val notes: LiveData<List<NoteItem>>
    private val repository : NoteRepository

    init {
        val dao = NoteDatabase.getInstance(application).noteDao
        repository = NoteRepository(dao)
        notes = repository.allNotes
    }

    fun deleteNote(noteItem: NoteItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(noteItem)
        }
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