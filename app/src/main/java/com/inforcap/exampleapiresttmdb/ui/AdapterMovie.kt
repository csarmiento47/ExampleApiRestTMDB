package com.inforcap.exampleapiresttmdb.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.collection.mutableObjectIntMapOf
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inforcap.exampleapiresttmdb.R
import com.inforcap.exampleapiresttmdb.core.Constants
import com.inforcap.exampleapiresttmdb.databinding.ItemMovieBinding
import com.inforcap.exampleapiresttmdb.model.MovieEntity

class AdapterMovie(
    val context: Context,
    var movieList: List<MovieEntity>
) : RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {

    private lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    inner class MovieViewHolder(binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(movie: MovieEntity){
            binding.run {
                Glide.with(context)
                    .load("${Constants.API_URL_IMG}${movie.imagen}")
                    .apply(RequestOptions().override(Constants.IMG_WIDTH, Constants.IMG_HEIGHT))
                    .error(R.drawable.baseline_error_24)
                    .into(binding.ivImage)

                tvInfo.text = HtmlCompat.fromHtml(
                    "<b>Title: </b>" + movie.title +
                    "<br><b>Popularity: </b>" + movie.popularity +
                    "<br><b>Rating: </b>" + movie.rating,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                cvMovie.setOnClickListener {
                    showOverview(movie.title, movie.overview)
                }
            }
        }

        private fun showOverview(title: String, overview: String) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(overview)
            builder.show()
        }
    }
}