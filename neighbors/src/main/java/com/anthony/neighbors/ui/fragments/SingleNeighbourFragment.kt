package com.anthony.neighbors.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.anthony.neighbors.R
import com.anthony.neighbors.adapters.NeighborBindingAdapder
import com.anthony.neighbors.databinding.OneNeighborsFragmentBinding
import com.anthony.neighbors.di.DI
import com.anthony.neighbors.models.Neighbor

class SingleNeighbourFragment(val neighbor: Neighbor) : Fragment() {
    private lateinit var binding: OneNeighborsFragmentBinding
    private val liveNeighbor = MutableLiveData<Neighbor>(neighbor)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.one_neighbors_fragment, container, false)
        binding.neighbor = liveNeighbor
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.singleLikeButton.setOnClickListener {
            DI.repository.updateFavoriteStatus(neighbor)
            refresh()
        }
    }
    private fun refresh() {
        liveNeighbor.value = neighbor
        NeighborBindingAdapder.bindFavorite(binding.singleLikeButton, neighbor.favorite)
    }
}
