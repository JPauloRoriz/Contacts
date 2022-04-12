package br.com.jpstudent.contacts.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.jpstudent.contacts.model.entitymodel.ContactModel


@Database(entities = [ContactModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
        abstract fun contactDao() : ContactDao
}