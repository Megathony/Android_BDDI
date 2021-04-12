package com.anthony.neighbors.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anthony.neighbors.di.DI
import com.anthony.neighbors.models.Neighbor
import com.anthony.neighbors.repositories.NeighborRepository

class NeighborViewModel : ViewModel() {
    private val repository: NeighborRepository = DI.repository

    // On fait passe plat sur le résultat retourné par le repository
    val neighbors: LiveData<List<Neighbor>>
        get() = repository.getNeighbours()
}
