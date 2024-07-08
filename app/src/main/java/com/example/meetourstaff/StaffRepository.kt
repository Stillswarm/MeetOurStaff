package com.example.meetourstaff

class StaffRepository {

    fun fetchPeople(): List<Person> {
        return DataSource.people
    }

    fun fetchPerson(index: Int): Person? {
        return if (index >= DataSource.NO_OF_STAFF) {
            null
        } else {
            DataSource.people[index]
        }
    }

    fun getSize(): Int {
        return DataSource.NO_OF_STAFF
    }

}