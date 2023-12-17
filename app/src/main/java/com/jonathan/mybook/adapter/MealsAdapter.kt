package com.jonathan.mybook.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jonathan.mybook.data.model.MealsItem
import com.jonathan.mybook.databinding.ItemRowBinding
import com.jonathan.mybook.views.detail.DetailActivity

class MealsAdapter : ListAdapter<MealsItem, MealsAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MEAL, review)
            holder.itemView.context.startActivity(intent)
        }
    }

    class MyViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: MealsItem){
            binding.mealName.text = review.strMeal
            Glide.with(binding.root.context)
                .load(review.strMealThumb)
                .into(binding.imageView)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MealsItem>() {
            override fun areItemsTheSame(oldItem: MealsItem, newItem: MealsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: MealsItem, newItem: MealsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}