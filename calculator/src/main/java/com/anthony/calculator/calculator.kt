package com.anthony.calculator

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.anthony.calculator.databinding.ActivityCalculatorBinding

class calculator : AppCompatActivity() {
    lateinit var binding: ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView( this, R.layout.activity_calculator )
        binding.lifecycleOwner = this
        setContentView(binding.root)
        compute()
    }

    private fun enableButton() {
        with(binding){
            computeButton.isEnabled = !operandFirst.text.isNullOrEmpty() && !operandSecond.text.isNullOrEmpty()
        }
    }

    private fun sum() {
        with(binding) {
            // Récupère le text d'un champ
            // le convertir en Int ou retourne null si ce n'est pas un Int
            val operandFirst = operandFirst.toInt()
            val operandSecond = operandSecond.toInt()
            // Faire la somme des 2 nombres
            // et afficher le résultat dans le champ compute
            computeResult.text = "${operandFirst.plus(operandSecond)}"
        }
    }

    private fun compute() {
        with(binding) {
            computeButton.setOnClickListener {
                sum()
            }

            operandFirst.doAfterTextChanged {
                enableButton()
            }

            operandSecond.doAfterTextChanged {
                enableButton()
            }
        }
    }

    /**
     * Fonction d'extension permettant de convertir le text d'un edit en Int
     */
    private fun EditText.toInt(): Int {
        return text.toString().toIntOrNull() ?: 0
    }
}