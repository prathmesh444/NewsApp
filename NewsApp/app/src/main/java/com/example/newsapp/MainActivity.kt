package com.example.newsapp


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private val url:String = "https://saurav.tech/NewsAPI/top-headlines/category/business/in.json"

    private lateinit var mAdapter:NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler)
        recyclerview.layoutManager = LinearLayoutManager(this)

        fetchdata()
        mAdapter = NewsAdapter(this)
        recyclerview.adapter = mAdapter

        val Refresh: SwipeRefreshLayout = findViewById(R.id.refresh)
        Refresh.setOnRefreshListener {
            fetchdata()
            recyclerview.adapter = mAdapter
            Refresh.isRefreshing = false
        }
    }

    private fun fetchdata() {
        val NewsArray:ArrayList<NewsItem> = ArrayList()
        val JSONObject = JsonObjectRequest(Request.Method.GET,url,null,
            {
                val NewsJsonArray = it.getJSONArray("articles")

                for (i in 0 until NewsJsonArray.length())
                {
                    val newsJsonObject = NewsJsonArray.getJSONObject(i)
                    val news = NewsItem(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    NewsArray.add(news)
                }
                mAdapter.UpdateNews(NewsArray)
            },
            {

        })
        MySingleton.getInstance(this).addToRequestQueue(JSONObject)
    }


    override fun onItemClicked(item: NewsItem) {
        val Builder = CustomTabsIntent.Builder()
        val customIntent = Builder.build()
        customIntent.launchUrl(this, Uri.parse(item.url))
    }

}