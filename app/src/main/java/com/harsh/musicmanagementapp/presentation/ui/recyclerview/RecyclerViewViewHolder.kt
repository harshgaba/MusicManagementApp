package com.harsh.musicmanagementapp.presentation.ui.recyclerview
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface RecyclerViewViewHolder<T : ViewBinding, I : Any> {
	fun bind(binder: T, item: I)

	fun bind(binder: T, item: I, payload: List<Any?>) {}

	fun inflate(parent: ViewGroup): T
}
