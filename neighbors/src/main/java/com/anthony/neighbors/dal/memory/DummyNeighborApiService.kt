package com.anthony.neighbors.dal.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anthony.neighbors.dal.DUMMY_NeighborS
import com.anthony.neighbors.repositories.service.NeighborApiService
import com.anthony.neighbors.models.Neighbor

class DummyNeighborApiService : NeighborApiService {

    private val _neighbours = MutableLiveData<List<Neighbor>>()

    override val neighbours: LiveData<List<Neighbor>>
        get() = _neighbours

    init {
        _neighbours.value = DUMMY_NeighborS
    }

    override fun deleteNeighbour(neighbor: Neighbor) {
        DUMMY_NeighborS.remove(neighbor)
        _neighbours.value = DUMMY_NeighborS
    }

    override fun createNeighbour(neighbor: Neighbor) {
        DUMMY_NeighborS.add(neighbor)
        _neighbours.value = DUMMY_NeighborS
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        DUMMY_NeighborS.find {
            it.id == neighbor.id
        }?.let {
            it.favorite = !it.favorite
        }
        _neighbours.value = DUMMY_NeighborS
    }

    override fun updateDataNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun addNeighbour() {
        TODO("Not yet implemented")
    }
}
