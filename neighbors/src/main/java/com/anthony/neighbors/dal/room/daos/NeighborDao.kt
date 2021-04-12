package com.anthony.neighbors.dal.room.daos

import com.anthony.neighbors.dal.room.entities.NeighborEntity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>
    @Insert
    fun add(entity: NeighborEntity)
}
