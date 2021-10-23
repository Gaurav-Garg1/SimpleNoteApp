package com.example.simplenotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplenotes.R
import com.example.simplenotes.data.NoteItem
import com.example.simplenotes.databinding.FragmentDisplayNotesBinding
import com.google.android.material.snackbar.Snackbar

class DisplayNotesFragment : Fragment(), ViewClicked {

    private lateinit var binding: FragmentDisplayNotesBinding
    private lateinit var viewModel: DisplayNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_display_notes,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val vmFactory = DisplayNoteViewModelFactory(application)
        viewModel = ViewModelProvider(this, vmFactory).get(DisplayNoteViewModel::class.java)

        binding.addFab.setOnClickListener {
            findNavController().navigate(R.id.action_displayNotesFragment_to_createNoteFragment)
        }

        val adapter = NoteAdapter(this)
        binding.noteRecyclerView.adapter = adapter
        binding.noteRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.notes.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

    override fun onDelClicked(note: NoteItem) {
        Toast.makeText(context,"Note Clicked ${note.note.take(10)}",Toast.LENGTH_SHORT).show()
    }

    override fun onEditClicked(note: NoteItem) {
        Snackbar.make(binding.root,"Note Clicked ${note.note.take(10)}",Snackbar.LENGTH_SHORT).show()
    }

}