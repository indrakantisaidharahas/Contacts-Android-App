package com.example.firstapp

sealed interface ContactEvent {
    object SaveContact:ContactEvent
    data class  DeleteContact(val contact: Contacts):ContactEvent
    data class SetFirstName(val firstname:String):ContactEvent
    data class SetLastName(val lastname:String):ContactEvent
    data class SetPhoneNo(val phonenumber:String):ContactEvent
    object ShowDialog:ContactEvent
    object HideDialog:ContactEvent

    data class SortContacts(val sorttype: SortType):ContactEvent

}