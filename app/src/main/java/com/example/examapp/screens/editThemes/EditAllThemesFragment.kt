package com.example.examapp.screens.editThemes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.examapp.R
import com.example.examapp.database.WordsDataBase
import com.example.examapp.databinding.FragmentChoiceOptionsBinding
import com.example.examapp.databinding.FragmentEditAllThemesBinding
import com.example.examapp.screens.choice.ChoiceOptionsFragmentDirections
import com.example.examapp.screens.choice.ChoiceOptionsViewModel
import com.example.examapp.screens.choice.ChoiceOptionsViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [EditAllThemesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditAllThemesFragment : Fragment() {

    private lateinit var viewModel: EditAllThemesViewModel
    private lateinit var binding: FragmentEditAllThemesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditAllThemesBinding.inflate(
            inflater, container, false)

        initializeViewModel()
        initializeComponents()

        return binding.root
    }

    private fun initializeViewModel() {
        val application = requireActivity().application
        val dao = WordsDataBase.getInstance(application).getWordsDataBaseDao()
        val viewModelFactory = EditAllThemesViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[EditAllThemesViewModel::class.java]
    }

    private fun initializeComponents(){
        val adapter = EditAllThemesAdapter()
        binding.editAllThemesRv.adapter = adapter

        viewModel.themes.observe(viewLifecycleOwner, Observer { themes ->
            if (themes != null){
                adapter.data = themes
            }
        })

        adapter.onEditThemeClick = {
            val action = EditAllThemesFragmentDirections.actionEditAllThemesFragmentToEditOneThemeFragment(
                it.themeID.toInt()
            )
            this.findNavController().navigate(action)
        }

        adapter.onDeleteThemeClick = {
            viewModel.deleteTheme(it.themeID)
        }

        binding.addThemeButton.setOnClickListener {
            if(binding.nameThemeEditText.text.toString() != ""){
                viewModel.addTheme(binding.nameThemeEditText.text.toString())
            }
        }
    }
}