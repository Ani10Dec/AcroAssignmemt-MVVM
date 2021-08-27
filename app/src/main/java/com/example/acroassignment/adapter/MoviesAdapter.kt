package com.example.acroassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acroassignment.R
import com.example.acroassignment.databinding.ItemRowBinding
import com.example.acroassignment.model.LikeModel
import com.example.acroassignment.model.MovieModel.Result
import com.example.acroassignment.view.FeedBackActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class MoviesAdapter(val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    private val movieList = ArrayList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemRowBinding>(
                layoutInflater,
                R.layout.item_row,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position], context)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setNewList(Movie: List<Result>) {
        movieList.clear()
        movieList.addAll(Movie)
    }
}

class ViewHolder(private val binding: ItemRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result, context: Context) {
        binding.tvTitle.text = result.title
        binding.tvSubTitle.text = "Language: ${result.original_language}"
        binding.tvPublisher.text = "Adult: ${result.adult}"
        binding.tvPublishDate.text = result.release_date
        Picasso.get().load("https://image.tmdb.org/t/p/w500${result.poster_path}")
            .into(binding.imageView)
        binding.btnLike.setOnClickListener {
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val reference: DatabaseReference = database.getReference("Likes")

            val model = LikeModel(result.title, true)
            val id = reference.push().key
            reference.child(id!!).setValue(model)
            Toast.makeText(context, "Liked this book", Toast.LENGTH_SHORT).show()
        }
        binding.btnFeedback.setOnClickListener {
            val intent = Intent(context, FeedBackActivity::class.java)
            context.startActivity(intent)
        }
    }
}
