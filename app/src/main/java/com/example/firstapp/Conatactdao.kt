package com.example.firstapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Conatactdao {
    @Query("SELECT * FROM Contacts ORDER BY firstname ASC")
     fun getContactsOrderByFirstName(): Flow<List<Contacts>>
    @Query("SELECT * FROM Contacts ORDER BY lastname ASC")
    fun getContactsOrderByLastName(): Flow<List<Contacts>>
    @Query("SELECT * FROM Contacts ORDER BY phonenumber ASC")
     fun getContactsOrderByPhNo(): Flow<List<Contacts>>
    @Insert
    suspend fun insertContact(contact: Contacts)
    @Delete
    suspend fun deleteContact(contact: Contacts)





}