package com.example.examapp.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.examapp.R
import com.example.examapp.database.WordsDataBase
import com.example.examapp.databinding.FragmentMainMenuBinding
import kotlin.system.exitProcess

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainMenuBinding.inflate(
            inflater, container, false
        )

        binding.playButton.setOnClickListener{

            it.findNavController().navigate(R.id.action_mainMenuFragment_to_choiceOptionsFragment)
            Log.i("OK", "Переход")
        }

        binding.exitButton.setOnClickListener{
            exitProcess(0)
        }

        binding.themesButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuFragment_to_editAllThemesFragment)
        }


        setHasOptionsMenu(true)
        return binding.root
    }


}