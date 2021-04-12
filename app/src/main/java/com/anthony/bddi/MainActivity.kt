package com.example.bddi

import androidx.lifecycle.MutableLiveData
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bddi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setContentView(binding.root) // passe élémetn racine

        val dataChange = DataChange()

        binding.lifecycleOwner = this // pour savoir qui gère cycl de vie et éviter fuite mémoire
        binding.nbClick = dataChange
        binding.myButton.setOnClickListener {
            dataChange.newData()
        }

        binding.myButton.setOnLongClickListener {
            Toast.makeText(baseContext, "Vous m'avez cliqué longtemps", Toast.LENGTH_LONG).show()
            false
        }
    }
}

class DataChange {
    // Observer - observarble ou abonnement abonné
    // à chaque fois que abonnement sur live data, on change la partie vye
    val data = MutableLiveData<String>()
    var nbClick = 0
    fun newData() {
        nbClick++
        data.value = "$nbClick"
    }
}