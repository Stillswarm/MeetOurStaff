package com.example.meetourstaff

data class AppUiState(
    val people: List<Person> = listOf(),
    val personSelected: Boolean = false,
    val currentPerson: Person? = null,
    val currentPersonId: Int? = null
)