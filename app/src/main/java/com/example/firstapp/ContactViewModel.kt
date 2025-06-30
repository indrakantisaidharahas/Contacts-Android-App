package com.example.firstapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.SortedMap

class ContactViewModel(private val dao: Conatactdao):ViewModel() {
    private val _sorttype= MutableStateFlow(SortType.FIRST_NAME)
private val _state= MutableStateFlow(ContactState())
private val _contact=_sorttype.flatMapLatest {
    sortType-> when(sortType){
        SortType.FIRST_NAME-> dao.getContactsOrderByFirstName()
        SortType.LAST_NAME-> dao.getContactsOrderByLastName()
        SortType.PH_NO-> dao.getContactsOrderByPhNo()
    }
}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),emptyList())
val state=combine(_state,_sorttype,_contact) {
    state,sortType,contacts->state.copy(contacts=contacts,sorttype=sortType)
}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),ContactState())



    fun onEvent(event: ContactEvent){
        when(event){
            is ContactEvent.HideDialog -> {
                _state.update{it.copy(isAddingContact=false
                )}
            }
            is ContactEvent.SaveContact -> {
               val firstname=state.value.firstname
                val lastname=state.value.lastname
                val phno=state.value.Phonenumber
                if(firstname.isBlank()||lastname.isBlank()||phno.isBlank()){
                    return
                }
                val contact= Contacts(firstname=firstname,lastname=lastname,phonenumber=phno)
                viewModelScope.launch {
                    dao.insertContact(contact)
                }
                _state.update { it.copy(isAddingContact = false,firstname="",lastname="",Phonenumber="") }


            }
            is ContactEvent.SetFirstName -> {
                _state.update{
                    it.copy(firstname=event.firstname)
                }
            }
            is ContactEvent.SetLastName -> {
                _state.update{
                    it.copy(lastname=event.lastname)
                }
            }
            is ContactEvent.SetPhoneNo -> {
                _state.update{
                    it.copy(Phonenumber=event.phonenumber)
                }
            }
            is  ContactEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }
            is ContactEvent.SortContacts -> {
                _sorttype.value=event.sorttype

            }
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }
        }
    }
}