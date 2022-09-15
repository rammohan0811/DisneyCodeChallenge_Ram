package com.example.disneycodechallenge_ram.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneycodechallenge_ram.db.NonReservedGuest
import com.example.disneycodechallenge_ram.db.ReservedGuest
import com.example.disneycodechallenge_ram.repository.SearchGuestRepository
import kotlinx.coroutines.launch

class FragmentSelectGuestViewModel(private val searchGuestRepository: SearchGuestRepository): ViewModel() {

    init {
        deleteDataFromDataBase()
        insertDataToDataBase()
    }

    private val _reservedGuestList: MutableLiveData<List<ReservedGuest>> = MutableLiveData()
    val reservedGuestList: LiveData<List<ReservedGuest>>
        get() = _reservedGuestList


    private val _nonReservedGuestList: MutableLiveData<List<NonReservedGuest>> = MutableLiveData()
    val nonReservedGuestList: LiveData<List<NonReservedGuest>>
        get() = _nonReservedGuestList


    private fun insertDataToDataBase() = viewModelScope.launch {
        searchGuestRepository.insertDataToDataBase()
    }

    private fun deleteDataFromDataBase() = viewModelScope.launch {
        searchGuestRepository.deleteAllData()
    }

    fun getReservedGuestList() = viewModelScope.launch {
        _reservedGuestList.value = searchGuestRepository.fetchReservedGuestList()
    }

    fun getNonReservedGuestList() = viewModelScope.launch {
        _nonReservedGuestList.value = searchGuestRepository.fetchNonReservedGuestList()
    }

}