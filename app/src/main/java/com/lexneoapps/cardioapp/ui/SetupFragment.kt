package com.lexneoapps.cardioapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lexneoapps.cardioapp.R
import com.lexneoapps.cardioapp.databinding.FragmentSetupBinding


class SetupFragment : Fragment(R.layout.fragment_run) {



    private var _binding: FragmentSetupBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetupBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvContinue.setOnClickListener{
            val action = SetupFragmentDirections.actionSetupFragmentToRunFragment()
            findNavController().navigate(action)
        }

    }
}