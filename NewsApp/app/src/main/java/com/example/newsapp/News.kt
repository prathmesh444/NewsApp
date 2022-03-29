package com.example.newsapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class News(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title:TextView = itemView.findViewById(R.id.title)
    val author:TextView = itemView.findViewById(R.id.auth)
    val image:ImageView = itemView.findViewById(R.id.NewsImage)
}
