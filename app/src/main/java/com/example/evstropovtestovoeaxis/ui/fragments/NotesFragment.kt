package com.example.evstropovtestovoeaxis.ui.fragments

import com.example.evstropovtestovoeaxis.ui.adapter.AdapterNote
import com.example.evstropovtestovoeaxis.ui.adapter.NoteListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.content.DialogInterface
import android.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evstropovtestovoeaxis.App
import com.example.evstropovtestovoeaxis.data.database.Note
import com.example.evstropovtestovoeaxis.databinding.FragmentNotesBinding
import com.example.evstropovtestovoeaxis.domain.NoteDomain
import javax.inject.Inject

class NotesFragment : Fragment(), NoteListener {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val adapter: AdapterNote = AdapterNote(this)
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private val notesFragmentViewModel: NotesFragmentViewModel by activityViewModels { viewModelProvider }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as App).getappComponent().inject(this)
        _binding = FragmentNotesBinding.inflate(inflater, container, false)

        binding.recycterNotes.layoutManager = LinearLayoutManager(requireContext()).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        binding.recycterNotes.adapter = adapter

        notesFragmentViewModel.getNotes().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)

        })
        val email = notesFragmentViewModel.user.value?.email
        notesFragmentViewModel.addDefaultNotes()
        binding.floatingActionButton.setOnClickListener {
            showNewRouteDialog(email)
        }
        return binding.root
    }

    fun showNewRouteDialog(email: String?) {
        val li = LayoutInflater.from(requireContext())
        val dialogView: View =
            li.inflate(com.example.evstropovtestovoeaxis.R.layout.alert_dialog, null)
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(
            requireContext()
        )
        alertDialogBuilder.setView(dialogView)
        val inputTittle =
            dialogView.findViewById<View>(com.example.evstropovtestovoeaxis.R.id.userInputTittle) as TextView
        val inputContent =
            dialogView.findViewById<View>(com.example.evstropovtestovoeaxis.R.id.userInputContent) as TextView
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton(
                "OK",
                DialogInterface.OnClickListener { dialog, id ->
                    email?.let {
                        val newNote = Note(
                            id = 0,
                            email = it,
                            tittle = inputTittle.text.toString(),
                            content = inputContent.text.toString()
                        )
                        notesFragmentViewModel.insertNote(newNote)
                    }

                })
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDeleteClickK(note: NoteDomain) {
        notesFragmentViewModel.deleteNote(note.id)
    }
}