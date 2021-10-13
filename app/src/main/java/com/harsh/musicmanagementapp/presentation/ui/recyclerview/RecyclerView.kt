package com.harsh.musicmanagementapp.presentation.ui.recyclerview
import android.content.Context
import android.util.AttributeSet
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

class RecyclerView(context: Context, attrs: AttributeSet?) : androidx.recyclerview.widget.RecyclerView(context, attrs) {
	val recyclerViewAdapter = RecyclerViewAdapter()
	init {
		adapter = recyclerViewAdapter
	}

	inline fun <B: ViewBinding, reified I: Any> addItemBindings(viewHolders: RecyclerViewViewHolder<B, I>) {
		@Suppress("UNCHECKED_CAST")
		recyclerViewAdapter.bindings[calculateItemViewType(I::class)] = viewHolders as RecyclerViewViewHolder<ViewBinding, Any>
	}

	fun setItems(items: List<Any>?) = recyclerViewAdapter.updateItems(items ?: emptyList())

	companion object {
		fun calculateItemViewType(clazz: KClass<*>) : Int = clazz.hashCode()
	}
}