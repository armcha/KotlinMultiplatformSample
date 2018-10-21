package io.github.armcha.kotlinmultiplatformexample.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import domain.models.Movie
import io.github.armcha.kotlinmultiplatformexample.R
import kotlinx.android.synthetic.main.item_view.view.*

class MovieListAdapter(private val items: List<Movie>, private val itemCLickListener: ItemCLickListener)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            itemCLickListener.onItemClick(items[position].id)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.movieName.text = movie.title
            itemView.movieDescription.text = movie.plot
            Picasso.get()
                    .load(movie.poster)
                    .into(itemView.imageView)


        }
    }
}

interface ItemCLickListener {

    fun onItemClick(movieId: String)
}

infix fun RecyclerView.setUpWith(movieListAdapter: MovieListAdapter){
    adapter = movieListAdapter
    layoutManager = LinearLayoutManager(context)
}