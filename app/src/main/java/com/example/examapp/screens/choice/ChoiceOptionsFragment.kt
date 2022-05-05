package com.example.examapp.screens.choice


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.examapp.R

import com.example.examapp.database.WordsDataBase
import com.example.examapp.databinding.FragmentChoiceOptionsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ChoiceOptionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChoiceOptionsFragment : Fragment() {

    private lateinit var viewModel: ChoiceOptionsViewModel
    private lateinit var binding: FragmentChoiceOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChoiceOptionsBinding.inflate(
            inflater, container, false)

        initializeViewModel()
        initializeComponents()

        return binding.root
    }

    private fun initializeViewModel() {
        val application = requireActivity().application
        val dao = WordsDataBase.getInstance(application).getWordsDataBaseDao()
        val viewModelFactory = ChoiceOptionsViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[ChoiceOptionsViewModel::class.java]
        Log.i("OK", "Создание")
    }

    private fun initializeComponents(){
        val adapter = ChoiceAdapter()
        binding.themesListRv.adapter = adapter

        viewModel.themes.observe(viewLifecycleOwner, Observer { themes ->
            if (themes != null){
                adapter.data = themes
            }
        })

        adapter.onEnterThemeClick = {
            val action = ChoiceOptionsFragmentDirections.actionChoiceOptionsFragmentToGameFragment(
                it.themeID.toInt(), binding.spinner3.selectedItem.toString().toInt()
            )
            this.findNavController().navigate(action)
        }
    }


}