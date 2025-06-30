package com.example.firstapp

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities=[Contacts::class], version = 1)
abstract class Database:RoomDatabase() {
abstract val dao: Conatactdao
}