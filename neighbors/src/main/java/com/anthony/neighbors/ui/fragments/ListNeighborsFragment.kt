package com.anthony.neighbors.ui.fragments

import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anthony.neighbors.R
import com.anthony.neighbors.adapters.ListNeighborHandler
import com.anthony.neighbors.adapters.ListNeighborsAdapter
import com.anthony.neighbors.di.DI
import com.anthony.neighbors.repositories.NeighborRepository
import com.anthony.neighbors.models.Neighbor
import com.anthony.neighbors.viewmodels.NeighborViewModel

class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        return view
    }

    private lateinit var viewModel: NeighborViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
    }

    private fun setData() {
        // Récupérer l'instance de l'application, si elle est null arrêter l'exécution de la méthode
        val application: Application = activity?.application ?: return

        val neighbors = NeighborRepository.getInstance(application).getNeighbours()

        neighbors.observe(
                viewLifecycleOwner,
                Observer {
                    val adapter = ListNeighborsAdapter(this, it)
                    recyclerView.adapter = adapter
                }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh()
    }

    private fun refresh() {
        DI.repository.getNeighbours().observe(viewLifecycleOwner) {
            var adapter = ListNeighborsAdapter(this, it)
            recyclerView.adapter = adapter
        }
    }

    fun reloadDisplay() {
        DI.repository.getNeighbours().observe(
            viewLifecycleOwner,
            Observer {
                val adapter = ListNeighborsAdapter(this, it)
                recyclerView.adapter = adapter
            }
        )
    }

    private lateinit var recyclerView: RecyclerView
    override fun onDeleteNeighbor(neighbor: Neighbor) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Alerte")
                setMessage("Voulez vraiment supprimer le voisin ?")
                setPositiveButton(
                    "Valider",
                    DialogInterface.OnClickListener { dialog, id ->
                        DI.repository.deleteNeighbor(neighbor)
                        reloadDisplay()
                    }
                )
                setNegativeButton(
                    "Annuler",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    }
                )
            }

            // Create the AlertDialog
            builder.create()
            builder.show()
        }
    }

    override fun onAddFavorite(neighbor: Neighbor) {
        DI.repository.addFavNeighbor(neighbor)
        reloadDisplay()
    }

    override fun onOpenPage(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun openWebsite(neighbor: Neighbor) {
        var url = Uri.parse("https://${neighbor.webSite}")
        val i = Intent(Intent.ACTION_VIEW, url)
        startActivity(i)
    }
}
