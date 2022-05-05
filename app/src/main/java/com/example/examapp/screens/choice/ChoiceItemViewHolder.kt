package com.example.examapp.screens.choice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examapp.R
import com.example.examapp.database.Theme

class ChoiceItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val themeName : TextView = itemView.findViewById(R.id.enter_theme_text_view)
    val enterButton : Button = itemView.findViewById(R.id.enter_theme_button)

    fun bind(item: Theme){
        themeName.text = item.nameTheme
    }

    companion object{
        fun from(parent: ViewGroup) : ChoiceItemViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.choice_item_view, parent, false)
            return ChoiceItemViewHolder(view)
        }
    }
}