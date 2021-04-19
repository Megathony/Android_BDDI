package com.anthony.neighbors.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.anthony.neighbors.R
import com.anthony.neighbors.databinding.AddNeighborsFragmentBinding
import com.anthony.neighbors.models.Neighbor
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputEditText
import com.anthony.neighbors.ui.NavigationListener
import com.anthony.neighbors.di.DI

class AddNeighbourFragment : Fragment() {
    private lateinit var formView: View
    lateinit var binding: AddNeighborsFragmentBinding
    private lateinit var fields: List<TextInputEditText>
    private var phoneOk = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.add_neighbors_fragment, container, false)
        with(binding) {
            fields = listOf(inputImage, inputName, inputPhone, inputWeb, inputAddress, inputAboutMe)
        }
        formView = binding.root

        (activity as? NavigationListener)?.updateTitle(R.string.titleAddNeighbor)

        return formView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    /**
     * Bind all watchers
     */
    private fun bind() {
        fields.forEach { it.doAfterTextChanged { onUserInput() } }

        with(binding) {
            inputPhone.doAfterFinishedEditing { checkPhone(it) }
            inputImage.doAfterFinishedEditing { checkUrl(it) }
            inputWeb.doAfterFinishedEditing { checkUrl(it) }
            inputSave.setOnClickListener {
                save()
            }
        }
    }

    private fun onUserInput() {
        with(binding) {
            // L'utilisateur peut sauveharder si tous les champs passent le predicate, et si les spécificités sont remplies
            val imageOk = inputImage.isValidUrl()
            val userCanSave = (
                fields.all { it.isNotEmpty() } &&
                    phoneOk &&
                    imageOk &&
                    inputWeb.isValidUrl()
                )
            inputSave.isEnabled = userCanSave

            // Mise à jour de l'image
            if (imageOk) {
                updateImage()
            }
        }
    }

    private fun checkPhone(input: TextInputEditText) {
        val value = input.text ?: ""
        phoneOk = value.length == 10 && (value.startsWith("06") || value.startsWith("07"))

        if (phoneOk) {
            input.error = null
        } else {
            input.error = getString(R.string.phoneError)
        }
        // We trigger onUserInput to update the save button state
        onUserInput()
    }
    private fun checkUrl(input: TextInputEditText) {
        if (input.isValidUrl()) {
            input.error = null
        } else {
            input.error = getString(R.string.urlError)
        }
    }
    private fun updateImage() {
        with(binding) {
            val context = imageView.context
            Glide.with(context)
                .load(inputImage.text.toString())
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_baseline_person_24)
                .error(R.drawable.ic_baseline_person_24)
                .skipMemoryCache(false)
                .into(imageView)
        }
    }

    /**
     * Vérifier que les champs ne sont pas vide
     */
    private fun TextInputEditText.isNotEmpty(): Boolean = text?.isNotEmpty() ?: false

    /**
     * vérifier que le champ contient bien une url valide
     */
    private fun TextInputEditText.isValidUrl(): Boolean = URLUtil.isValidUrl(text.toString())

    /**
     * Lancer la vérification du champs quand celui-ci est fini d'être modifié
     */
    private fun TextInputEditText.doAfterFinishedEditing(callback: (TextInputEditText) -> Unit) {
        setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) { callback.invoke(v as TextInputEditText) }
        }
    }

    private fun save() {
        val lastNeighborId = DI.repository.getNeighbours().value?.last()?.id ?: 0
        val id = lastNeighborId + 1
        with(binding) {
            val newNeighbor = Neighbor(
                id = id,
                name = inputName.text.toString(),
                avatarUrl = inputImage.text.toString(),
                address = inputAddress.text.toString(),
                phoneNumber = inputPhone.text.toString(),
                aboutMe = inputAboutMe.text.toString(),
                favorite = false,
                webSite = inputWeb.text.toString()
            )
            DI.repository.createNeighbor(newNeighbor)
        }
        (activity as? NavigationListener)?.showFragment(ListNeighborsFragment())
    }
}
