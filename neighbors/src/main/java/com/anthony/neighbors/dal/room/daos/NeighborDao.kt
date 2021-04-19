package com.anthony.neighbors.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anthony.neighbors.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>
    @Insert
    fun add(entity: NeighborEntity)
    @Update
    fun update(vararg neighbors: NeighborEntity)
    @Delete
    fun delete(vararg neighbors: NeighborEntity)
}
