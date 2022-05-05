package com.example.examapp.screens.choice

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examapp.database.Theme

class ChoiceAdapter : RecyclerView.Adapter<ChoiceItemViewHolder>() {
    lateinit var onEnterThemeClick : (Theme) -> Unit

    var data = listOf<Theme>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceItemViewHolder {
        return ChoiceItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ChoiceItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        holder.enterButton.setOnClickListener {
            onEnterThemeClick(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}