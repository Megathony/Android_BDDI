import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.anthony.neighbors.models.Neighbor
import com.anthony.neighbors.repositories.service.NeighborApiService

class RoomNeighborDataSource(context: Context) : NeighborApiService {
    private val database: NeighborDataBase = NeighborDataBase.getDataBase(context)
    private val dao: NeighborDao = database.neighborDao()

    private val _neighors = MediatorLiveData<List<Neighbor>>()

    init {
        _neighors.addSource(dao.getNeighbors()) { entities ->
            _neighors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    override val neighbours: LiveData<List<Neighbor>>
        get() = _neighors

    override fun deleteNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun createNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateDataNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun addNeighbour() {
        TODO("Not yet implemented")
    }
}