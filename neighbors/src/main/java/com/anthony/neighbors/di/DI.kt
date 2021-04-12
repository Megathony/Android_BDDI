package com.anthony.neighbors.di

import android.content.Context
import com.anthony.neighbors.repositories.NeighborRepository

object DI {
    lateinit var repository: NeighborRepository
    fun inject(context: Context) {
        repository = NeighborRepository.getInstance(context)
    }
}
