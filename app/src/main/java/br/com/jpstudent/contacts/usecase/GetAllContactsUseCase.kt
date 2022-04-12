package br.com.jpstudent.contacts.usecase

import br.com.jpstudent.contacts.domain.entities.Contact
import br.com.jpstudent.contacts.model.repository.ContactsRepository

class GetAllContactsUseCase(
    private val repository : ContactsRepository
) {

    fun getAllContacts() : List<Contact>{
        return repository.getAllContact()
    }
}