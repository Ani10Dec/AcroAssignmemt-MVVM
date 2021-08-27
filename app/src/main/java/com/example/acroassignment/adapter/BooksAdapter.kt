package com.example.acroassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.acroassignment.R
import com.example.acroassignment.databinding.ItemRowBinding
import com.example.acroassignment.model.BooksModel.Item
import com.example.acroassignment.model.LikeModel
import com.example.acroassignment.view.FeedBackActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BooksAdapter(val context: Context) :
    RecyclerView.Adapter<MyViewHolder>() {

    private val booksList = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemRowBinding>(
                layoutInflater,
                R.layout.item_row,
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(booksList[position], context)
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    fun setNewList(books: List<Item>) {
        booksList.clear()
        booksList.addAll(books)
    }
}

class MyViewHolder(private val binding: ItemRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item, context: Context) {
        val current = item.volumeInfo
        binding.tvTitle.text = current.title
        binding.tvSubTitle.text = "Pages: ${current.pageCount}"
        binding.tvPublisher.text = current.publisher
        binding.tvPublishDate.text = current.publishedDate
        Glide.with(context).load(current.imageLinks.smallThumbnail)
            .error(R.drawable.ic_launcher_foreground).into(binding.imageView)

        binding.btnLike.setOnClickListener {
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val reference: DatabaseReference = database.getReference("Likes")

            val model = LikeModel(current.title, true)
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
