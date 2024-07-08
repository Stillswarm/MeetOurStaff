package com.example.meetourstaff

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _repository = StaffRepository()
    val repository = _repository
    private var _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
        resetHomeScreen()
    }

    fun resetHomeScreen() {
        _uiState.update {
            it.copy(
                people = repository.fetchPeople(),
                personSelected = false,
                currentPerson = null
            )
        }
    }

    fun getAbbreviatedName(firstName: String, lastName: String): String {
        return lastName + ", " + firstName.first() + "."
    }

    private fun getPerson(id: Int): Person {
        return repository.fetchPerson(id)!!
    }

    fun onCardClick(id: Int) {
        _uiState.update {
            it.copy(
                personSelected = true,
                currentPersonId = id,
                currentPerson = getPerson(id)
            )
        }
    }

    fun showNavIcon(): Boolean {
        return _uiState.value.personSelected
    }

    fun navigateLeft(id: Int) {
        if (id != 0) {
            _uiState.update {
                it.copy(currentPersonId = id - 1)
            }
        }

        updatePerson()
    }

    fun navigateRight(id: Int) {
        if (id < repository.getSize() - 1) {
            _uiState.update {
                it.copy(currentPersonId = id + 1)
            }
        }

        updatePerson()
    }

    private fun updatePerson() {
        _uiState.update {
            it.copy(
                currentPerson = repository.fetchPerson(it.currentPersonId!!)
            )
        }
    }

    fun getStaffList(): List<Person> {
        return repository.fetchPeople()
    }
}