package br.com.jpstudent.contacts.usecase

import br.com.jpstudent.contacts.domain.entities.Contact
import br.com.jpstudent.contacts.model.repository.ContactsRepository

class GetFavoriteContactsUseCase(
    private val repository : ContactsRepository
) {

    fun getFavoriteContacts() : List<Contact>{
        return repository.getFavoriteContacts()
    }
}