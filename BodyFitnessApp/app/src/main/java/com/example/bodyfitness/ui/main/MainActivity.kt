package com.example.bodyfitness.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bodyfitness.R
import com.example.bodyfitness.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        
        // Connect the bottom navigation view with the navigation controller
        binding.bottomNavView.setupWithNavController(navController)

        // Handle navigation state changes
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // You can handle specific navigation events here if needed
            // For example, hiding the bottom nav on specific screens
            when (destination.id) {
                R.id.navigation_home,
                R.id.navigation_goals,
                R.id.navigation_profile -> {
                    binding.bottomNavView.visibility = android.view.View.VISIBLE
                }
                else -> {
                    binding.bottomNavView.visibility = android.view.View.GONE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}