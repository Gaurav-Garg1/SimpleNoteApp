package com.example.simplenotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplenotes.R
import com.example.simplenotes.databinding.FragmentDisplayNotesBinding

class DisplayNotesFragment : Fragment() {

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

        val adapter = NoteAdapter()
        binding.noteRecyclerView.adapter = adapter
        binding.noteRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.notes.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

}