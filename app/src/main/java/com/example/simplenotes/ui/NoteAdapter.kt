package com.example.simplenotes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenotes.R
import com.example.simplenotes.data.NoteItem
import java.text.SimpleDateFormat
import java.util.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var data = listOf<NoteItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val note = itemView.findViewById<TextView>(R.id.note_text)
        val time = itemView.findViewById<TextView>(R.id.time_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thisItem = data[position]
        holder.note.text = thisItem.note
        val date = SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault()).format(thisItem.dateTime)
        holder.time.text = date
    }

    override fun getItemCount() = data.size
}