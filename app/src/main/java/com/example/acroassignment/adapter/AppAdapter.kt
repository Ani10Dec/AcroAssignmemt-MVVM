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
import com.example.acroassignment.model.AppsModel.Apps
import com.example.acroassignment.model.LikeModel
import com.example.acroassignment.view.FeedBackActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AppAdapter(val context: Context) : RecyclerView.Adapter<AppViewHolder>() {

    private val appList = ArrayList<Apps>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemRowBinding>(
                layoutInflater,
                R.layout.item_row,
                parent,
                false
            )
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(appList[position], context)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    fun setNewList(app: List<Apps>) {
        appList.clear()
        appList.addAll(app)
    }

}

class AppViewHolder(private val binding: ItemRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Apps, context: Context) {
        binding.tvTitle.text = result.title
        binding.tvSubTitle.text = "Users: ${result.users}"
        binding.tvPublisher.text = result.publisher
        binding.tvPublishDate.text = result.publishedDate

        Glide.with(context).load(result.image).error(R.drawable.ic_launcher_foreground)
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