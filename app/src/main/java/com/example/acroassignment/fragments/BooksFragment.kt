package com.example.acroassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acroassignment.R
import com.example.acroassignment.adapter.BooksAdapter
import com.example.acroassignment.databinding.FragmentBooksBinding
import com.example.acroassignment.repository.repository.Repository
import com.example.acroassignment.repository.retrofit.ApiServices
import com.example.acroassignment.repository.retrofit.RetrofitInstance
import com.example.acroassignment.utils.AppHelper
import com.example.acroassignment.viewmodel.BooksViewModel
import com.example.acroassignment.viewmodel.ViewModelFactory

class BooksFragment : Fragment() {

    private lateinit var binding: FragmentBooksBinding
    private lateinit var viewModel: BooksViewModel
    private lateinit var adapter: BooksAdapter
    private lateinit var apiServices: ApiServices

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_books, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiServices =
            RetrofitInstance.getBooksRetrofitInstance().create(ApiServices::class.java)
        val repo = Repository(apiServices)
        val factory = ViewModelFactory(repo, "books")
        viewModel = ViewModelProvider(this, factory).get(BooksViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        AppHelper.showProgressBar(requireContext())

        viewModel.bookResponse.observe(requireActivity(), {
            val books = it.items
            settingRecyclerView()
            adapter.setNewList(books)
            adapter.notifyDataSetChanged()
            AppHelper.hideProgressBar()
        })
    }

    private fun settingRecyclerView() {
        binding.booksRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = BooksAdapter(requireContext())
        binding.booksRecyclerview.adapter = adapter
    }
}

