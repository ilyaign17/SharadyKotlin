package com.example.examapp.screens.finishGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.examapp.R
import com.example.examapp.databinding.FragmentFinishGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [FinishGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FinishGameFragment : Fragment() {
    private lateinit var args: FinishGameFragmentArgs
    private lateinit var binding: FragmentFinishGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = FinishGameFragmentArgs.fromBundle(requireArguments())
        binding = FragmentFinishGameBinding.inflate(
            inflater, container, false)

        binding.scoreText.text = args.score.toString()

        binding.toHomeButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_finishGameFragment_to_mainMenuFragment)
        }
        return binding.root
    }

}