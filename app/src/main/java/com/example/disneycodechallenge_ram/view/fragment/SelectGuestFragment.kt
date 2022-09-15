package com.example.disneycodechallenge_ram.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disneycodechallenge_ram.R
import com.example.disneycodechallenge_ram.databinding.FragmentSelectGuestBinding
import com.example.disneycodechallenge_ram.db.NonReservedGuest
import com.example.disneycodechallenge_ram.db.ReservedGuest
import com.example.disneycodechallenge_ram.repository.SearchGuestRepository
import com.example.disneycodechallenge_ram.util.AppConstant
import com.example.disneycodechallenge_ram.view.adapter.NonReserveGuestAdapter
import com.example.disneycodechallenge_ram.view.adapter.ReserveGuestAdapter
import com.example.disneycodechallenge_ram.viewmodel.FragmentSelectGuestViewModel
import com.google.android.material.snackbar.Snackbar

class SelectGuestFragment : Fragment(R.layout.fragment_select_guest) {

    private lateinit var binding: FragmentSelectGuestBinding
    private lateinit var viewModel: FragmentSelectGuestViewModel
    private lateinit var reserveGuestAdapter: ReserveGuestAdapter
    private lateinit var nonReserveGuestAdapter: NonReserveGuestAdapter

    private var isReserveGuestSelected = false
    private var isNonReserveGuestSelected = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectGuestBinding.bind(view)
        val searchGuestRepository = this.activity?.let { SearchGuestRepository(it) }
        viewModel = searchGuestRepository?.let { FragmentSelectGuestViewModel(it) }!!

        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        //Initialize the adapter
        initAdapter()

        //fetch the initial Guest list
        fetchGuestList()

        binding.btnContinue.setOnClickListener {

            if (isReserveGuestSelected && isNonReserveGuestSelected) {
                //Navigate to conflict screen
                navigateToConfirmationScreen(AppConstant.NAV_TYPE_CONFLICT)
            } else if(isReserveGuestSelected) {
                //Show confirmation screen
                navigateToConfirmationScreen(AppConstant.NAV_TYPE_CONFIRM)
            } else {
                showSnackBar(it)
            }
        }
    }

    private fun showSnackBar(it: View) {
        val snackbar = Snackbar.make(it, "", Snackbar.LENGTH_INDEFINITE)

        // inflate the custom_snackbar_view created previously
        val customSnackView: View =
            layoutInflater.inflate(R.layout.snacbar_custom_design, null)

        // set the background of the default snackbar as transparent
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)

        // now change the layout of the snackbar
        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

        // set padding of the all corners as 0
        snackbarLayout.setPadding(0, 0, 0, 0)

        // register the button from the custom_snackbar_view layout file
        val btnDismiss: ImageView = customSnackView.findViewById(R.id.btn_close)

        btnDismiss.setOnClickListener {
            snackbar.dismiss()
        }
        // add the custom snack bar layout to snackbar layout
        snackbarLayout.addView(customSnackView, 0)
        snackbar.show()
    }

    private fun navigateToConfirmationScreen(navType: String) {

        //Reset Flag values
        isNonReserveGuestSelected = false
        isReserveGuestSelected = false

        val bundle = Bundle()
        bundle.putString("nav_type", navType)
        var fragment = SelectConfirmationFragment()
        fragment.arguments = bundle

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.contentFrame, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun fetchGuestList() {
        viewModel.getReservedGuestList()
        viewModel.reservedGuestList.observe(viewLifecycleOwner) {
            with(binding) {
                reserveGuestAdapter.setData(it as ArrayList<ReservedGuest> /* = java.util.ArrayList<com.example.disneycodechallenge_ram.db.ReservedGuest> */)
            }
        }

        viewModel.getNonReservedGuestList()
        viewModel.nonReservedGuestList.observe(viewLifecycleOwner) {
            with(binding) {
                nonReserveGuestAdapter.setData(it as MutableList<NonReservedGuest> /* = java.util.ArrayList<com.example.disneycodechallenge_ram.db.ReservedGuest> */)
            }
        }
    }

    private fun initAdapter() {
        reserveGuestAdapter = ReserveGuestAdapter {
            isReserveGuestSelected = true
            enableContinueButton()
        }
        binding.rvReservedGuest.layoutManager = LinearLayoutManager(activity)
        binding.rvReservedGuest.adapter = reserveGuestAdapter

        nonReserveGuestAdapter = NonReserveGuestAdapter {
            isNonReserveGuestSelected = true
            enableContinueButton()
        }
        binding.rvNeedReserveGuest.layoutManager = LinearLayoutManager(activity)
        binding.rvNeedReserveGuest.adapter = nonReserveGuestAdapter
    }

    private fun enableContinueButton() {

        if (isNonReserveGuestSelected || isReserveGuestSelected) {
            binding.btnContinue.isEnabled = true
        }
    }

}