package com.example.examapp.screens.editOneTheme

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examapp.database.Word

class EditOneThemeAdapter : RecyclerView.Adapter<EditOneThemeViewHolder>() {
    lateinit var onDeleteWordClick: (Word) -> Unit

    var data = listOf<Word>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditOneThemeViewHolder {
        return EditOneThemeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EditOneThemeViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        holder.deleteButton.setOnClickListener {
            onDeleteWordClick(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}