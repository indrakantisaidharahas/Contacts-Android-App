package com.example.firstapp
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Contacts(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
val firstname:String ,
val lastname:String,
val phonenumber:String
)
