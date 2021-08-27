package com.example.acroassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acroassignment.R
import com.example.acroassignment.adapter.AppAdapter
import com.example.acroassignment.databinding.FragmentAppsBinding
import com.example.acroassignment.model.AppsModel.Apps
import com.example.acroassignment.repository.retrofit.ApiServices
import com.example.acroassignment.repository.retrofit.RetrofitInstance
import com.example.acroassignment.utils.AppHelper

class AppsFragment : Fragment() {

    lateinit var binding: FragmentAppsBinding
    lateinit var adapter: AppAdapter
    private lateinit var apiServices: ApiServices
    private val appList: ArrayList<Apps> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_apps, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiServices =
            RetrofitInstance.getMovieRetrofitInstance().create(ApiServices::class.java)
        binding.lifecycleOwner = this
        AppHelper.showProgressBar(requireContext())
        setUpAppsRecyclerView()
    }

    private fun setUpAppsRecyclerView() {
        appList.add(
            Apps(
                "Sharechat",
                "250 Million",
                "Mohalla Tech",
                "October 2015",
                requireActivity().getDrawable(R.drawable.sharechat)!!
            )
        )
        appList.add(
            Apps(
                "Gmail",
                "1.5 Billion",
                "Google",
                "April 1, 2004",
                requireActivity().getDrawable(R.drawable.gmail)!!
            )
        )
        appList.add(
            Apps(
                "Instagram",
                "123 Million",
                "Facebook, Inc.",
                "6 October 2010",
                requireActivity().getDrawable(R.drawable.instagram)!!
            )
        )
        appList.add(
            Apps(
                "Facebook",
                "120 Million",
                "Mark Zuckerberg",
                "February 2004",
                requireActivity().getDrawable(R.drawable.facebook)!!
            )
        )
        appList.add(
            Apps(
                "Google Chrome",
                "33 Million",
                "Google",
                "September 2008",
                requireActivity().getDrawable(R.drawable.chrome)!!
            )
        )
        appList.add(
            Apps(
                "Twitter",
                "10 Million",
                "Jack Dorsey",
                "March 2006",
                requireActivity().getDrawable(R.drawable.twitter)!!
            )
        )
        appList.add(
            Apps(
                "Paytm",
                "9 Million",
                "Vijay Shekhar Sharma",
                "April 2010",
                requireActivity().getDrawable(R.drawable.paytm)!!
            )
        )
        appList.add(
            Apps(
                "PhonePe",
                "6 Million",
                "Sameer Nigam, Rahul Char",
                "December 2015",
                requireActivity().getDrawable(R.drawable.phonepe)!!
            )
        )
        appList.add(
            Apps(
                "Google Pay",
                "6 Million",
                "Google",
                "September 2015",
                requireActivity().getDrawable(R.drawable.gpay)!!
            )
        )
        appList.add(
            Apps(
                "Flipkart",
                "21 Million",
                "Sachin Bansal, Binny Bansal",
                "October 2007",
                requireActivity().getDrawable(R.drawable.flipkart)!!
            )
        )

        binding.appRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = AppAdapter(requireContext())
        binding.appRecyclerview.adapter = adapter
        adapter.setNewList(appList)
        adapter.notifyDataSetChanged()
        AppHelper.hideProgressBar()
    }
}