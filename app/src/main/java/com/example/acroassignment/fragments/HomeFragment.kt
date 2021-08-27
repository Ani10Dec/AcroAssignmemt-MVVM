package com.example.acroassignment.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.acroassignment.R
import com.example.acroassignment.databinding.FragmentHomeBinding
import com.example.acroassignment.utils.AppHelper
import com.example.acroassignment.view.LogInActivity
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.btnEntertainment.setOnClickListener {
            if (AppHelper.isOnline(requireContext()))
                it.findNavController().navigate(R.id.action_homeFragment_to_entertainmentFragment)
            else
                Toast.makeText(
                    requireContext(),
                    "No Internet!\nCheck The Connection",
                    Toast.LENGTH_SHORT
                ).show()
        }
        binding.btnBooks.setOnClickListener {
            if (AppHelper.isOnline(requireContext()))
                it.findNavController().navigate(R.id.action_homeFragment_to_booksFragment)
            else
                Toast.makeText(
                    requireContext(),
                    "No Internet!\nCheck The Connection",
                    Toast.LENGTH_SHORT
                ).show()
        }
        binding.btnApps.setOnClickListener {
            if (AppHelper.isOnline(requireContext()))
                it.findNavController().navigate(R.id.action_homeFragment_to_appsFragment)
            else
                Toast.makeText(
                    requireContext(), "No Internet!\n" +
                            "Check The Connection", Toast.LENGTH_SHORT
                ).show()
        }
        binding.btnLogOut.setOnClickListener {
            if (AppHelper.isOnline(requireContext())) {
                FirebaseAuth.getInstance().signOut();
                val intent = Intent(requireContext(), LogInActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                requireContext().startActivity(intent)
            } else
                Toast.makeText(
                    requireContext(), "No Internet!\n" +
                            "Check The Connection", Toast.LENGTH_SHORT
                ).show()
        }
        return binding.root
    }

}