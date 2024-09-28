package eu.ampersand.objectapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.ampersand.objectapp.R
import eu.ampersand.objectapp.databinding.ItemObjectsBinding
import eu.ampersand.objectapp.model.PhoneObjectItem

/*
 * File: TransformAdapter.kt
 * ------------------------------
 * Owner: Esther
 * © 2024 Ampersand. All rights reserved.
 */
class TransformAdapter(private val onItemClick: (PhoneObjectItem) -> Unit) :
    ListAdapter<PhoneObjectItem, TransformViewHolder>(object : DiffUtil.ItemCallback<PhoneObjectItem>() {

        // Checks whether two PhoneObjectItem objects represent the same item.
        override fun areItemsTheSame(oldItem: PhoneObjectItem, newItem: PhoneObjectItem): Boolean =
            oldItem == newItem

        // Checks whether two PhoneObjectItem objects contain the same data.
        override fun areContentsTheSame(oldItem: PhoneObjectItem, newItem: PhoneObjectItem): Boolean =
            oldItem == newItem
    }) {

    // List of drawable resource IDs representing avatars used for random selection.
    private val drawables = listOf(
        R.drawable.avatar_1,
        R.drawable.avatar_2,
        R.drawable.avatar_3,
        R.drawable.avatar_4,
        R.drawable.avatar_5,
        R.drawable.avatar_6,
        R.drawable.avatar_7,
        R.drawable.avatar_8,
        R.drawable.avatar_9,
        R.drawable.avatar_10,
        R.drawable.avatar_11,
        R.drawable.avatar_12,
        R.drawable.avatar_13,
        R.drawable.avatar_14,
        R.drawable.avatar_15,
        R.drawable.avatar_16,
    )

    // Inflates the layout for an individual item and returns a TransformViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransformViewHolder {
        val binding = ItemObjectsBinding.inflate(LayoutInflater.from(parent.context))
        return TransformViewHolder(binding)
    }

    // Binds data to the view holder at the given position in the list.
    override fun onBindViewHolder(holder: TransformViewHolder, position: Int) {
        // Set the item's name to the TextView.
        holder.textView.text = getItem(position).name

        // Select a random avatar from the drawables list and set it to the ImageView.
        val drawable = drawables[(0..15).random()]
        holder.imageView.setImageDrawable(
            ResourcesCompat.getDrawable(holder.imageView.resources, drawable, null)
        )

        // Set up a click listener for the item, invoking the onItemClick callback.
        holder.itemView.setOnClickListener {
            onItemClick(getItem(position))
        }
    }
}

// ViewHolder class for holding references to the views in each item layout (image and text).
class TransformViewHolder(binding: ItemObjectsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    // Reference to the ImageView in the item layout.
    val imageView: ImageView = binding.imageViewItemTransform

    // Reference to the TextView in the item layout.
    val textView: TextView = binding.textViewItemTransform
}
