package io.github.armcha.kotlinmultiplatformexample.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import io.github.armcha.kotlinmultiplatformexample.R
import org.kotlin.mpp.mobile.domain.models.Movie

class MovieListAdapter(private val items:List<Movie>,private val clickListener: View.OnClickListener? = null)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
        holder.itemView.setOnClickListener(clickListener)
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        Picasso.get()
                .load(item.poster)
                .into(holder.imageView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageView: ImageView by lazy {
            view.findViewById<ImageView>(R.id.imageView)
        }
    }
}