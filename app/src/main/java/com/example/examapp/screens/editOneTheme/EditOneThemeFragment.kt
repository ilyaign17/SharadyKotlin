package com.example.examapp.screens.editOneTheme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.examapp.R
import com.example.examapp.database.WordsDataBase
import com.example.examapp.databinding.FragmentEditAllThemesBinding
import com.example.examapp.databinding.FragmentEditOneThemeBinding
import com.example.examapp.screens.editThemes.EditAllThemesAdapter
import com.example.examapp.screens.editThemes.EditAllThemesViewModel
import com.example.examapp.screens.editThemes.EditAllThemesViewModelFactory
import com.example.examapp.screens.game.GameFragmentArgs

/**
 * A simple [Fragment] subclass.
 * Use the [EditOneThemeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditOneThemeFragment : Fragment() {

    private lateinit var args: EditOneThemeFragmentArgs
    private lateinit var viewModel: EditOneThemeViewModel
    private lateinit var binding: FragmentEditOneThemeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = EditOneThemeFragmentArgs.fromBundle(requireArguments())
        binding = FragmentEditOneThemeBinding.inflate(
            inflater, container, false)

        initializeViewModel()
        initializeComponents()

        return binding.root
    }

    private fun initializeViewModel(){
        val application = requireActivity().application
        val dao = WordsDataBase.getInstance(application).getWordsDataBaseDao()
        val viewModelFactory = EditOneThemeViewModelFactory(dao, application, args)
        viewModel = ViewModelProvider(this, viewModelFactory)[EditOneThemeViewModel::class.java]
    }

    private fun initializeComponents(){
        val adapter = EditOneThemeAdapter()
        binding.wordsRv.adapter = adapter

        viewModel.words.observe(viewLifecycleOwner, Observer { words ->
            if (words != null){
                adapter.data = words
            }
        })

        adapter.onDeleteWordClick = {
            viewModel.deleteWord(it.wordID)
        }

        binding.addNewWordButton.setOnClickListener{
            if(binding.addWordEditText.text.toString() != ""){
                viewModel.addWord(binding.addWordEditText.text.toString(), args.editThemeId.toLong())
            }
        }
    }


}