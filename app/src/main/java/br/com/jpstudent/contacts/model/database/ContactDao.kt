package br.com.jpstudent.contacts.model.database

import androidx.room.*
import br.com.jpstudent.contacts.model.entitymodel.ContactModel

@Dao
interface ContactDao {
    @Insert
    fun addContact(contactModel : ContactModel)

    @Update
    fun updateContact(contactModel : ContactModel)

    @Query("select * from ContactModel")
    fun getAllContacts():List<ContactModel>

    @Query("select * from ContactModel where isFavorite = (:isFavorite)")
    fun getFavoriteContacts(isFavorite: Boolean = true):List<ContactModel>
}