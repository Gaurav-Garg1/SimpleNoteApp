package com.example.simplenotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simplenotes.R
import com.example.simplenotes.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    private lateinit var viewModel: CreateNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_create_note, container, false)

        //Use SAFE ARGS Plugin for type safe data passing
        var note = arguments?.get("noteTxt")
        var time = arguments?.get("time")

        val application = requireNotNull(this.activity).application
        val vmFactory = CreateNoteViewModelFactory(application)
        viewModel = ViewModelProvider(this, vmFactory).get(CreateNoteViewModel::class.java)

        if (note != null) {

            note = note.toString()
            time = time.toString().toLong()

            binding.noteInput.setText(note)
            binding.saveButton.apply {
                text = "UPDATE NOTE"
                setOnClickListener {
                    val input = binding.noteInput.text.toString().trim()
                    if (input.isNotEmpty()) viewModel.onUpdate(input,time)
                    findNavController().navigate(R.id.action_createNoteFragment_to_displayNotesFragment)
                }
            }
        } else {
            binding.saveButton.setOnClickListener {
                val input = binding.noteInput.text.toString().trim()
                if (input.isNotEmpty()) viewModel.onSubmit(input)
                findNavController().navigate(R.id.action_createNoteFragment_to_displayNotesFragment)
            }
        }
        return binding.root
    }
}