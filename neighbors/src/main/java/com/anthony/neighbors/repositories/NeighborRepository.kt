package com.anthony.neighbors.repositories

import RoomNeighborDataSource
import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.anthony.neighbors.adapters.ListNeighborsAdapter
import com.anthony.neighbors.dal.memory.DummyNeighborApiService
import com.anthony.neighbors.repositories.service.NeighborApiService
import com.anthony.neighbors.models.Neighbor

class NeighborRepository private constructor(context: Context) {
    private val dataSource: NeighborApiService

    init {
        dataSource = RoomNeighborDataSource(context)
    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): LiveData<List<Neighbor>> = dataSource.neighbours

    fun delete(neighbor: Neighbor) = dataSource.deleteNeighbour(neighbor)
    fun createNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    fun deleteNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    fun addFavNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(context: Context): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(context)
            }
            return instance!!
        }
    }
}