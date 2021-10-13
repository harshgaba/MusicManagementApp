package com.harsh.musicmanagementapp.presentation.ui.recyclerview
class ListItemPayloadDiffCallback : ItemsDiffCallback() {

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val oldItem = oldItems[oldItemPosition] as? ListItem
		val newItem = newItems[newItemPosition] as? ListItem
		return when {
			oldItem != null && newItem != null -> oldItem.id == newItem.id
			else -> super.areItemsTheSame(oldItemPosition, newItemPosition)
		}
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val oldItem = oldItems[oldItemPosition] as? ListItemWithPayload
		val newItem = newItems[newItemPosition] as? ListItemWithPayload
		return when {
			oldItem != null && newItem != null -> oldItem.payload == newItem.payload
			else -> super.areContentsTheSame(oldItemPosition, newItemPosition)
		}
	}

	override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? = (newItems[newItemPosition] as? ListItemWithPayload)?.payload
}

interface ListItemWithPayload : ListItem {
	val payload: Any
}

