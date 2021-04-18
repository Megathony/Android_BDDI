package com.anthony.neighbors.repositories

import android.app.Application
import com.anthony.neighbors.dal.room.RoomNeighborDataSource
import androidx.lifecycle.LiveData
import com.anthony.neighbors.adapters.ListNeighborsAdapter
import com.anthony.neighbors.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private val dataSource: NeighborDatasource

    init {
        dataSource = RoomNeighborDataSource(application)
    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): LiveData<List<Neighbor>> = dataSource.neighbours
    fun createNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    fun deleteNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    fun addFavNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    fun delete(neighbor: Neighbor) = dataSource.deleteNeighbour(neighbor)

    private fun setData() {
        // Récupérer l'instance de l'application, si elle est null arrêter l'exécution de la méthode
        val application: Application = activity?.application ?: return

        val neighbors = NeighborRepository.getInstance(application).getNeighbours()
        neighbors.observe(viewLifecycleOwner) {
            val adapter = ListNeighborsAdapter(it, this)
            binding.neighborsList.adapter = adapter
        }
    }
    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}