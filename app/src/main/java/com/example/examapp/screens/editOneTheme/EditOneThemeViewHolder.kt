package com.example.examapp.screens.editOneTheme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examapp.R
import com.example.examapp.database.Theme
import com.example.examapp.database.Word
import com.example.examapp.screens.editThemes.EditAllThemesViewHolder

class EditOneThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val wordName : TextView = itemView.findViewById(R.id.word_item_text_view)
    val deleteButton : Button = itemView.findViewById(R.id.delete_word_item_button)

    fun bind(item: Word){
        wordName.text = item.wordText
    }

    companion object{
        fun from(parent: ViewGroup) : EditOneThemeViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item_view, parent, false)
            return EditOneThemeViewHolder(view)
        }
    }
}