package com.anthony.neighbors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anthony.neighbors.R
import com.anthony.neighbors.databinding.NeighborItemBinding
import com.anthony.neighbors.models.Neighbor
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListNeighborsAdapter(
    val callback: ListNeighborHandler,
    items: List<Neighbor>
) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {
    private val mNeighbours: List<Neighbor> = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: NeighborItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.neighbor_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]
        holder.binding.neighbor = neighbour

        if (neighbour.favorite) {
            // on met l'image plein
            holder.binding.itemListFavButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            // on met l'image vide
            holder.binding.itemListFavButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        holder.binding.itemListFavButton.setOnClickListener {
            callback.onAddFavorite(neighbour)
        }

        holder.binding.itemListPageButton.setOnClickListener {
            callback.onOpenPage(neighbour)
        }

        holder.binding.itemListCard.setOnClickListener {
            callback.onOpenSingle(neighbour)
        }

        holder.binding.itemListFavButton.setOnClickListener {
            callback.onDeleteNeighbor(neighbour)
        }
    }

    override fun getItemCount(): Int {
        return mNeighbours.size
    }

    class ViewHolder(val binding: NeighborItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
