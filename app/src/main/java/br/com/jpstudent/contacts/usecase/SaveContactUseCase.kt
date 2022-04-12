package br.com.jpstudent.contacts.usecase

import android.content.Context
import br.com.jpstudent.contacts.R
import br.com.jpstudent.contacts.model.repository.ContactsRepository
import br.com.jpstudent.contacts.usecase.exceptions.DataEmptyException
import br.com.jpstudent.contacts.usecase.exceptions.FormatNumberInvalidException
import br.com.jpstudent.contacts.usecase.exceptions.NameLenghtException

class SaveContactUseCase(
    private val repository : ContactsRepository,
    private val context : Context
) {
    fun saveContact(name: String, number: String) {
        if(name.isEmpty() || number.isEmpty()){
            throw DataEmptyException(context.getString(R.string.data_isempty))
        }
        if(name.length < 3){
            throw NameLenghtException(context.getString(R.string.lengt_name))
        }
        if(number.length != 11){
            throw FormatNumberInvalidException(context.getString(R.string.format_invalid))
        }

        repository.addContact(name, number)
    }
}