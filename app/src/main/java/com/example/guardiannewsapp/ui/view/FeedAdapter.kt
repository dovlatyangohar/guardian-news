package com.example.guardiannewsapp.ui.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guardiannewsapp.R
import com.example.guardiannewsapp.network.models.Result

class FeedAdapter(
    private var data: List<Result> = mutableListOf(),
    private var clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.feed_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].let { result ->
            holder.apply {
                title.text = result.webTitle
                category.text = result.sectionName
                favNews.setOnClickListener {
                    clickListener.addToDB(result)
                    notifyItemChanged(position)
                }
                itemView.setOnClickListener {
                    clickListener.onItemClick(result)
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.newsTitle)
        val category: TextView = itemView.findViewById(R.id.newsCategory)
        val favNews: ImageView = itemView.findViewById(R.id.favImgView)
    }

    interface OnItemClickListener {
        fun onItemClick(result: Result)
        fun addToDB(result: Result)
    }

}