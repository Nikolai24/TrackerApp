package com.example.trackerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trackerapp.Habit
import com.example.trackerapp.R

class DataAdapter(habits: MutableList<Habit>, listener: OnItemClickListener): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private val habits: MutableList<Habit>
    private val habitsAll: MutableList<Habit>
    private val listener: OnItemClickListener

    init {
        this.habits = ArrayList(habits)
        this.habitsAll = ArrayList(habits)
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(item: Habit, position: Int)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name_habit)
        val description: TextView = itemView.findViewById(R.id.description_habit)
        val priority: TextView = itemView.findViewById(R.id.priority_habit)
        val type: TextView = itemView.findViewById(R.id.type_habit)
        val quantity: TextView = itemView.findViewById(R.id.quantity_habit)
        val periodicity: TextView = itemView.findViewById(R.id.periodicity_habit)
        fun bind(item: Habit){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Habit = habits[position]
        with(holder){
            name.text = item.name
            description.text = item.description
            priority.text = item.priority
            type.text = item.type
            quantity.text = QUANTITY + item.quantity.toString()
            periodicity.text = PERIODICITY + item.periodicity.toString()
            itemView.setOnClickListener{listener.onItemClick(item, position)}
        }
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    fun setHabits(habitsNew: List<Habit>) {
        habits.clear()
        habits.addAll(habitsNew)
        notifyDataSetChanged()
    }
    companion object {
        private const val QUANTITY = "Quantity: "
        private const val PERIODICITY = "Periodicity: "
    }
}