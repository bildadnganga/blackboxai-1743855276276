package com.example.bodyfitness.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bodyfitness.R
import com.example.bodyfitness.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        // Load header image using Glide
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1579758629938-03607ccdbaba?auto=format&fit=crop&w=800&q=80")
            .centerCrop()
            .placeholder(R.drawable.ic_goals) // Use a placeholder until image loads
            .error(R.drawable.ic_goals) // Show this if image fails to load
            .into(binding.headerImage)

        // Set welcome message with user's name (in a real app, get this from user data)
        binding.welcomeText.text = getString(R.string.home_welcome, "User")

        // Set a motivational quote
        binding.quoteText.text = getRandomQuote()
    }

    private fun setupClickListeners() {
        binding.trackWorkoutCard.setOnClickListener {
            // Handle track workout click
            // In a real app, navigate to workout tracking screen
        }

        binding.viewProgressCard.setOnClickListener {
            // Handle view progress click
            // In a real app, navigate to progress screen
        }
    }

    private fun getRandomQuote(): String {
        // In a real app, these could come from an API or database
        val quotes = listOf(
            "The only bad workout is the one that didn't happen.",
            "Your body can stand almost anything. It's your mind that you have to convince.",
            "The hard days are the best because that's when champions are made.",
            "Success is walking from failure to failure with no loss of enthusiasm.",
            "The difference between try and triumph is just a little umph!"
        )
        return quotes.random()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}