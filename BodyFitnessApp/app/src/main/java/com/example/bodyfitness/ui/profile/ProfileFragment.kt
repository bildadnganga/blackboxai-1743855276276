package com.example.bodyfitness.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bodyfitness.databinding.FragmentProfileBinding
import com.example.bodyfitness.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        // Load profile image using Glide
        Glide.with(this)
            .load("https://images.pexels.com/photos/1542085/pexels-photo-1542085.jpeg?auto=compress&cs=tinysrgb&w=400")
            .circleCrop()
            .into(binding.profileImage)

        // Set user information (in a real app, get this from user data)
        binding.apply {
            profileNameText.text = "John Doe"
            profileEmailText.text = "john.doe@example.com"
            heightText.text = "175 cm"
            weightText.text = "70 kg"
            bmiText.text = calculateBMI(175f, 70f).toString()
        }
    }

    private fun setupClickListeners() {
        binding.editProfileButton.setOnClickListener {
            showEditProfileDialog()
        }

        binding.logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun showEditProfileDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Edit Profile")
            .setMessage("This is a demo. In a real app, this would show a form to edit your profile.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showLogoutConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                performLogout()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun performLogout() {
        // In a real app, clear user session, preferences, etc.
        
        // Navigate back to login screen
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    private fun calculateBMI(heightCm: Float, weightKg: Float): Float {
        val heightM = heightCm / 100
        return String.format("%.1f", weightKg / (heightM * heightM)).toFloat()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}