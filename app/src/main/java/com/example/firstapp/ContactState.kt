package com.example.firstapp

data class ContactState(
    val contacts:List<Contacts> =emptyList(),
    val firstname:String= "",
    val lastname:String = "",
    val Phonenumber:String="",
    val isAddingContact:Boolean=false,
    val sorttype: SortType =SortType.FIRST_NAME

)
 