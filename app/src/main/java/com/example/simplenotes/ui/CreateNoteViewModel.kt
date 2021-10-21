package com.example.simplenotes.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CreateNoteViewModel(application: Application) {
}
class NoteViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateNoteViewModel::class.java)) {
            return CreateNoteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}