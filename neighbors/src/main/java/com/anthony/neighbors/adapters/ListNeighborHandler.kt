package com.anthony.neighbors.adapters

import com.anthony.neighbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
    fun onAddFavorite(neighbor: Neighbor)
    fun onOpenPage(neighbor: Neighbor)
    fun onOpenSingle(neighbor: Neighbor)
}