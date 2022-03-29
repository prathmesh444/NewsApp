package com.example.newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide

class NewsAdapter(val listener: NewsItemClicked): RecyclerView.Adapter<News>() {

    private var items: ArrayList<NewsItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): News {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items,parent,false)
        val N = News(view)
        view.setOnClickListener{
            listener.onItemClicked(items[N.adapterPosition])
        }

        return N
    }


    override fun onBindViewHolder(holder: News, position: Int) {
        val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun UpdateNews(update : ArrayList<NewsItem>)
    {
        items.clear()
        items.addAll(update)

        notifyDataSetChanged() //it will run all the 3 overridden process again
    }

}
interface NewsItemClicked {
    fun onItemClicked(item: NewsItem)
}