package com.example.examapp.screens.editThemes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examapp.R
import com.example.examapp.database.Theme

class EditAllThemesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val themeName : TextView = itemView.findViewById(R.id.theme_item_text_view)
    val editButton : Button = itemView.findViewById(R.id.edit_theme_button)
    val deleteButton : Button = itemView.findViewById(R.id.delete_theme_button)

    fun bind(item: Theme){
        themeName.text = item.nameTheme
    }

    companion object{
        fun from(parent: ViewGroup) : EditAllThemesViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.theme_item_view, parent, false)
            return EditAllThemesViewHolder(view)
        }
    }
}