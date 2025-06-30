package com.example.firstapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddContactDialog(state: ContactState, onEvent: (ContactEvent) -> Unit, modifier: Modifier = Modifier) {
    AlertDialog(
        onDismissRequest = {
            onEvent(ContactEvent.HideDialog)
        },
        title = { Text("Add Contact") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = state.firstname,
                    onValueChange = { onEvent(ContactEvent.SetFirstName(it)) },
                    placeholder = { Text(text = "First Name") }
                )
                TextField(
                    value = state.lastname,
                    onValueChange = { onEvent(ContactEvent.SetLastName(it)) },
                    placeholder = { Text(text = "Last Name") }
                )
                TextField(
                    value = state.Phonenumber,
                    onValueChange = { onEvent(ContactEvent.SetPhoneNo(it)) },
                    placeholder = { Text(text = "Phone Number") }
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(ContactEvent.SaveContact)
                }) {
                    Text(text = "Save Contact")
                }
            }
        },
        modifier = modifier
    )
}
