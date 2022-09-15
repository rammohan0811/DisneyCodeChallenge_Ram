package com.example.disneycodechallenge_ram.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.disneycodechallenge_ram.app.MainApplication
import com.example.disneycodechallenge_ram.db.AppDataBase
import com.example.disneycodechallenge_ram.db.NonReservedGuest
import com.example.disneycodechallenge_ram.db.ReservedGuest

class SearchGuestRepository(context: Context) {

    var dao = AppDataBase
        .getInstance(context)
        ?.GuestDao()

    fun insertDataToDataBase() {
        val reservedGuestList: List<ReservedGuest>
            = listOf(
            ReservedGuest(null,"Dale Gibson", false),
            ReservedGuest(null,"Jerome Gibson", false),
            ReservedGuest(null,"Sam Gibson", false),

            )

        for (item in reservedGuestList) {
            dao?.insertReserveGuest(item)
        }


        val nonReservedGuestList: List<NonReservedGuest>
                = listOf(
            NonReservedGuest(null,"Linda Gibson", false),
            NonReservedGuest(null,"Margarate Gibson", false),
            NonReservedGuest(null,"Bobby Gibson", false),

            )

        for (item in nonReservedGuestList) {
            dao?.insertNonReserveGuest(item)
        }
    }

    fun fetchReservedGuestList(): List<ReservedGuest>? {
        return dao?.fetchReservedGuest()
    }

    fun fetchNonReservedGuestList(): List<NonReservedGuest>? {
        return dao?.fetchNonReservedGuest()
    }

    fun deleteAllData(){
        dao?.deleteReserveGuest()
        dao?.deleteNonReserveGuest()
    }
}