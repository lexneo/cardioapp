package com.lexneoapps.cardioapp.ui


import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.lexneoapps.cardioapp.R
import com.lexneoapps.cardioapp.databinding.FragmentSettingsBinding
import com.lexneoapps.cardioapp.other.Constants.KEY_NAME
import com.lexneoapps.cardioapp.other.Constants.KEY_WEIGHT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {


    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private var _binding: FragmentSettingsBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFieldsFromSharedPref()

        binding.btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPref()
            if (success){
                Snackbar.make(view, "Saved changes",Snackbar.LENGTH_LONG).show()
            }else{
                Snackbar.make(view, "Please fill out all the fields",Snackbar.LENGTH_LONG).show()

            }
        }


    }

    private fun loadFieldsFromSharedPref()  {
        val name = sharedPreferences.getString(KEY_NAME,"Name")
        val weight = sharedPreferences.getFloat(KEY_WEIGHT,80f)

        binding.etName.setText(name)
        binding.etWeight.setText(weight.toString())

    }

    private fun applyChangesToSharedPref() : Boolean{
        val nameText = binding.etName.text.toString()
        val weightText = binding.etWeight.text.toString()
        if (nameText.isEmpty() || weightText.isEmpty() ){
            return false
        }

        sharedPreferences.edit()
            .putString(KEY_NAME,nameText)
            .putFloat(KEY_WEIGHT,weightText.toFloat())
            .apply()
        return true
    }
}