package com.example.disneycodechallenge_ram.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NonReservedGuest")
data class NonReservedGuest(@PrimaryKey(autoGenerate = true)var guestID: Int? = null,
                            val guestName: String,
                            var isSelected: Boolean = false)
