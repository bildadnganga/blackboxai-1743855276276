package com.example.bodyfitness.ui.goals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bodyfitness.databinding.FragmentGoalsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class GoalsFragment : Fragment() {
    private var _binding: FragmentGoalsBinding? = null
    private val binding get() = _binding!!
    private lateinit var goalsAdapter: GoalsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupFab()
        loadSampleGoals() // In a real app, load from database or API
    }

    private fun setupRecyclerView() {
        goalsAdapter = GoalsAdapter { goal ->
            // Handle goal item click
            // In a real app, show goal details or edit screen
        }

        binding.goalsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = goalsAdapter
        }
    }

    private fun setupFab() {
        binding.addGoalFab.setOnClickListener {
            showAddGoalDialog()
        }
    }

    private fun showAddGoalDialog() {
        // In a real app, show a proper dialog or navigate to an add goal screen
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add New Goal")
            .setMessage("This is a demo. In a real app, this would show a form to add a new goal.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun loadSampleGoals() {
        // Sample goals for demonstration
        val sampleGoals = listOf(
            Goal(
                id = 1,
                name = "Run 5km three times a week",
                target = "15km per week",
                deadline = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    .format(Calendar.getInstance().apply { add(Calendar.MONTH, 3) }.time),
                progress = 65
            ),
            Goal(
                id = 2,
                name = "Complete 100 push-ups daily",
                target = "100 push-ups",
                deadline = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    .format(Calendar.getInstance().apply { add(Calendar.MONTH, 1) }.time),
                progress = 40
            ),
            Goal(
                id = 3,
                name = "Maintain healthy diet",
                target = "2000 calories per day",
                deadline = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    .format(Calendar.getInstance().apply { add(Calendar.MONTH, 6) }.time),
                progress = 80
            )
        )

        goalsAdapter.submitList(sampleGoals)
        updateEmptyState(sampleGoals.isEmpty())
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyStateText.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.goalsRecyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Goal data class
data class Goal(
    val id: Long,
    val name: String,
    val target: String,
    val deadline: String,
    val progress: Int
)

// RecyclerView Adapter
class GoalsAdapter(private val onGoalClick: (Goal) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<Goal, GoalsAdapter.GoalViewHolder>(GoalDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val binding = com.example.bodyfitness.databinding.ItemGoalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GoalViewHolder(binding, onGoalClick)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GoalViewHolder(
        private val binding: com.example.bodyfitness.databinding.ItemGoalBinding,
        private val onGoalClick: (Goal) -> Unit
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(goal: Goal) {
            binding.apply {
                goalNameText.text = goal.name
                goalTargetText.text = goal.target
                goalDeadlineText.text = goal.deadline
                goalProgress.progress = goal.progress
                goalStatusChip.text = when {
                    goal.progress >= 100 -> "Completed"
                    goal.progress > 0 -> "In Progress"
                    else -> "Not Started"
                }

                root.setOnClickListener { onGoalClick(goal) }
            }
        }
    }
}

// DiffUtil for efficient RecyclerView updates
class GoalDiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<Goal>() {
    override fun areItemsTheSame(oldItem: Goal, newItem: Goal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Goal, newItem: Goal): Boolean {
        return oldItem == newItem
    }
}