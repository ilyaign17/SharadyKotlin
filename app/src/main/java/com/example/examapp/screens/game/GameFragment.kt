package com.example.examapp.screens.game

import android.os.Bundle
import android.text.format.DateUtils
import android.transition.Visibility
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.examapp.R
import com.example.examapp.database.WordsDataBase
import androidx.navigation.fragment.findNavController
import com.example.examapp.databinding.FragmentGameBinding
import com.example.examapp.screens.choice.ChoiceOptionsViewModel
import com.example.examapp.screens.choice.ChoiceOptionsViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private lateinit var args: GameFragmentArgs
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        args = GameFragmentArgs.fromBundle(requireArguments())
        binding = FragmentGameBinding.inflate(
            inflater, container, false)

        initializeViewModel()

        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
            updateWordText()
            updateScoreText()
        }

        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()

            updateWordText()
            updateScoreText()
        }

        updateWordText()
        updateScoreText()

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { isFinished ->
            if (isFinished) {
                gameFinished()
                viewModel.onGameFinishComplete()
            }
        })

        viewModel.currentTime.observe(viewLifecycleOwner, Observer { seconds ->
            binding.timeTextView.text = DateUtils.formatElapsedTime(seconds)
        })

        return binding.root
    }

    private fun initializeViewModel(){
        val application = requireActivity().application
        val dao = WordsDataBase.getInstance(application).getWordsDataBaseDao()
        val viewModelFactory = GameFactory(dao, application, args)
        viewModel = ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]

    }

    private fun updateWordText() {
        binding.wordTextView.text = viewModel.word

    }

    private fun updateScoreText() {
        binding.scoreTextView.text = viewModel.score.toString()
    }

    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToFinishGameFragment(viewModel.score)
        findNavController().navigate(action)
    }
}