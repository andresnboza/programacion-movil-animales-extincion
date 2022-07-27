package com.proyecto.animalesextintos.ui.animal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyecto.animalesextintos.R
import com.proyecto.animalesextintos.databinding.FragmentAddAnimalBinding
import com.proyecto.animalesextintos.model.Animal
import com.proyecto.animalesextintos.viewmodel.AnimalViewModel

class AddAnimalFragment : Fragment() {
    private var _binding : FragmentAddAnimalBinding? = null
    private val  binding get() = _binding!!
    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAnimalBinding.inflate(inflater,container,false)

        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)

        binding.btnSubmit.setOnClickListener {
            addLuggar()
        }

        return binding.root
    }

    private fun addLuggar(){
        val nombre = binding.animalName.text.toString()

        if(validation(nombre)){
            //guardar animal
            val animal = Animal(0,nombre)
            animalViewModel.addAnimal(animal) //enviando a la bd
            Toast.makeText(requireContext(), getString(R.string.msg_success), Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addAnimalFragment_to_nav_animal) //devolver al producto desde la lista

        } else{
            Toast.makeText(requireContext(), getString(R.string.msg_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun validation(nombre: String): Boolean {
        return !(nombre.isEmpty())
    }

}