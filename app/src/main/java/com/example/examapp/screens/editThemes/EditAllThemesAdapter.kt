package com.example.examapp.screens.editThemes

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examapp.database.Theme
import com.example.examapp.screens.choice.ChoiceItemViewHolder

class EditAllThemesAdapter : RecyclerView.Adapter<EditAllThemesViewHolder>(){
    lateinit var onEditThemeClick: (Theme) -> Unit
    lateinit var onDeleteThemeClick: (Theme) -> Unit

    var data = listOf<Theme>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditAllThemesViewHolder {
        return EditAllThemesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EditAllThemesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        holder.editButton.setOnClickListener {
            onEditThemeClick(item)
        }

        holder.deleteButton.setOnClickListener {
            onDeleteThemeClick(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}