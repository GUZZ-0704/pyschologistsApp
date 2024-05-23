package com.example.psychologistsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.psychologistsapp.databinding.CategoryItemBinding
import com.example.psychologistsapp.models.Category

class CategoryAdapter(
    private var categoryList: ArrayList<Category>,
    private val listener: CategoryAdapter.OnCategoryClickListener
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val category = categoryList?.get(position)
        if (category != null) {
            holder.bind(category, listener)
        }
    }

    override fun getItemCount(): Int {
        return categoryList?.size?: 0
    }

    class CategoryViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category, listener: CategoryAdapter.OnCategoryClickListener) {
            val binding = CategoryItemBinding.bind(itemView)
            binding.lblCategoryName.text = category.name
            binding.root.setOnClickListener {
                listener.onCategoryClick(category)
            }
        }
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category)

    }

}