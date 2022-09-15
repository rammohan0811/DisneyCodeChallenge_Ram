package com.example.disneycodechallenge_ram.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GuestDao {

    @Insert
    fun insertNonReserveGuest(nonReservedGuest: NonReservedGuest)

    @Query("Select * from NonReservedGuest")
    fun fetchNonReservedGuest(): List<NonReservedGuest>

    @Insert
    fun insertReserveGuest(reservedGuest: ReservedGuest)

    @Query("Select * from ReservedGuest")
    fun fetchReservedGuest(): List<ReservedGuest>

    @Query("DELETE FROM ReservedGuest")
    fun deleteReserveGuest()

    @Query("DELETE FROM NonReservedGuest")
    fun deleteNonReserveGuest()
}