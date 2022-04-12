package br.com.jpstudent.contacts.model.repository

import br.com.jpstudent.contacts.domain.entities.Contact
import br.com.jpstudent.contacts.model.database.ContactDao
import br.com.jpstudent.contacts.model.entitymodel.ContactModel

class ContactsRepository(
    private val contactDao: ContactDao
) {
    fun addContact(name: String, number: String) {
        val contactModel = ContactModel(
            name = name,
            numberOfContact = number,
            isFavorite = false
        )

        contactDao.addContact(
            ContactModel(
                contactModel.id,
                contactModel.name,
                contactModel.numberOfContact,
                contactModel.isFavorite
            )
        )
    }

    fun getAllContact(): List<Contact> {
        return contactDao.getAllContacts().map { contactModel ->
            Contact(
                contactModel.id,
                contactModel.name,
                contactModel.numberOfContact,
                contactModel.isFavorite
            )
        }
    }
    fun getFavoriteContacts(): List<Contact> {
        return contactDao.getFavoriteContacts().map { contactModel ->
            Contact(
                contactModel.id,
                contactModel.name,
                contactModel.numberOfContact,
                contactModel.isFavorite
            )
        }
    }

    fun updateContact(contact : Contact){
        contactDao.updateContact(ContactModel(
            contact.id,
            contact.name,
            contact.numberOfContact,
            contact.isFavorite
        ))
    }
}