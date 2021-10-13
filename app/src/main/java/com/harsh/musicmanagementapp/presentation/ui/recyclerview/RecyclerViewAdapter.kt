package com.harsh.musicmanagementapp.presentation.ui.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.harsh.musicmanagementapp.presentation.ui.recyclerview.RecyclerView.Companion.calculateItemViewType

open class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

class RecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val items: MutableList<Any> = ArrayList()
    val bindings: HashMap<Int, RecyclerViewViewHolder<ViewBinding, Any>> = hashMapOf()
    private var itemsDiffCallback: ItemsDiffCallback? = null

    private fun getBinding(viewType: Int): RecyclerViewViewHolder<ViewBinding, Any> {
        return bindings[viewType] ?: run {
            val itemType =
                items.first { calculateItemViewType(it::class) == viewType }::class.simpleName
            throw IllegalStateException("Have you forgotten to add the recyclerview item binding for item type $itemType ?")
        }
    }

    fun setDiffUtilCallback(diffUtilCallback: ItemsDiffCallback) {
        this.itemsDiffCallback = diffUtilCallback
    }

    fun updateItems(items: List<Any>) {
        itemsDiffCallback?.let {
            val diffCallback = it.apply {
                oldItems = this@RecyclerViewAdapter.items
                newItems = items
            }
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            this.items.clear()
            this.items.addAll(items)
            diffResult.dispatchUpdatesTo(this)
        } ?: run {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val recyclerViewViewHolder = getBinding(viewType)
        return ViewHolder(recyclerViewViewHolder.inflate(parent))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        getBinding(calculateItemViewType(items[position]::class))
            .bind(holder.binding, items[position])

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            getBinding(calculateItemViewType(items[position]::class))
                .bind(holder.binding, items[position], payloads)
        }
    }

    override fun getItemViewType(position: Int) = calculateItemViewType(items[position]::class)

}
