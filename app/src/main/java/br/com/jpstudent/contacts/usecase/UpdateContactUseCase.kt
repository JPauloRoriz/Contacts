package br.com.jpstudent.contacts.usecase

import br.com.jpstudent.contacts.domain.entities.Contact
import br.com.jpstudent.contacts.model.repository.ContactsRepository

class UpdateContactUseCase(
    private val repository: ContactsRepository
) {
    fun updateContact(contact : Contact){
        repository.updateContact(contact)
    }

}