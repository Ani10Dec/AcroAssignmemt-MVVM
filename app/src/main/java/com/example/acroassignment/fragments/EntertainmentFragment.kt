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
import com.example.acroassignment.adapter.MoviesAdapter
import com.example.acroassignment.databinding.FragmentEntertainmentBinding
import com.example.acroassignment.repository.repository.Repository
import com.example.acroassignment.repository.retrofit.ApiServices
import com.example.acroassignment.repository.retrofit.RetrofitInstance
import com.example.acroassignment.utils.AppHelper
import com.example.acroassignment.viewmodel.MoviesViewModel
import com.example.acroassignment.viewmodel.ViewModelFactory

class EntertainmentFragment : Fragment() {

    lateinit var binding: FragmentEntertainmentBinding
    lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var apiServices: ApiServices

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_entertainment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiServices =
            RetrofitInstance.getMovieRetrofitInstance().create(ApiServices::class.java)
        val repo = Repository(apiServices)
        val factory = ViewModelFactory(repo, "movies")
        viewModel = ViewModelProvider(this, factory).get(MoviesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        AppHelper.showProgressBar(requireContext())

        viewModel.moviesResponse.observe(requireActivity(), {
            val movies = it.results
            settingRecyclerView()
            adapter.setNewList(movies)
            adapter.notifyDataSetChanged()
            AppHelper.hideProgressBar()
        })

    }

    private fun settingRecyclerView() {
        binding.moviewRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = MoviesAdapter(requireContext())
        binding.moviewRecyclerview.adapter = adapter
    }

}