package com.anthony.neighbors.repositories

import android.app.Application
import com.anthony.neighbors.dal.room.RoomNeighborDataSource
import androidx.lifecycle.LiveData
import com.anthony.neighbors.dal.NeighborApiService
import com.anthony.neighbors.dal.memory.DummyNeighborApiService
import com.anthony.neighbors.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private var apiService: NeighborApiService
    private val memoryApiService: NeighborApiService
    private val persistentApiService: NeighborApiService
    init {
        memoryApiService = DummyNeighborApiService()
        persistentApiService = RoomNeighborDataSource(application)

        apiService = memoryApiService
    }

    fun setPersistency(shouldBePersistent: Boolean) {
        apiService = if (shouldBePersistent) {
            persistentApiService
        } else {
            memoryApiService
        }
    }

    fun getNeighbours(): LiveData<List<Neighbor>> = apiService.neighbours

    fun createNeighbor(neighbor: Neighbor) = apiService.createNeighbour(neighbor)

    fun deleteNeighbor(neighbor: Neighbor) = apiService.deleteNeighbour(neighbor)

    fun updateFavoriteStatus(neighbor: Neighbor) = apiService.updateFavoriteStatus(neighbor)

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}
