package com.example.disneycodechallenge_ram.view.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.disneycodechallenge_ram.R
import com.example.disneycodechallenge_ram.databinding.FragmentSelectConfirmationBinding
import com.example.disneycodechallenge_ram.util.AppConstant

class SelectConfirmationFragment : Fragment(R.layout.fragment_select_confirmation) {

    private lateinit var binding: FragmentSelectConfirmationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectConfirmationBinding.bind(view)

        val navType = arguments?.getString("nav_type", "")


        if (navType.equals(AppConstant.NAV_TYPE_CONFIRM)) {
            binding.textConfirmation.text = getString(R.string.confirmation_text)
        } else {
            binding.textConfirmation.text = getString(R.string.conflict_text)
        }

        setHasOptionsMenu(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }
}