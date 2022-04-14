package br.com.jpstudent.contacts.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import br.com.jpstudent.contacts.R
import br.com.jpstudent.contacts.domain.entities.Contact
import br.com.jpstudent.contacts.domain.entities.TabsTypeContacts
import br.com.jpstudent.contacts.usecase.GetAllContactsUseCase
import br.com.jpstudent.contacts.usecase.GetFavoriteContactsUseCase
import br.com.jpstudent.contacts.usecase.SaveContactUseCase
import br.com.jpstudent.contacts.usecase.UpdateContactUseCase
import br.com.jpstudent.contacts.usecase.exceptions.DataEmptyException
import br.com.jpstudent.contacts.usecase.exceptions.FormatNumberInvalidException
import br.com.jpstudent.contacts.usecase.exceptions.NameLenghtException

class ContactsViewModel(
    private val context: Context,
    private val useCaseSaveContact: SaveContactUseCase,
    private val useCaseGetAllContacts: GetAllContactsUseCase,
    private val getFavoriteContactsUseCase: GetFavoriteContactsUseCase,
    private val updateContactUseCase: UpdateContactUseCase
) : ViewModel() {
    val oppenBottomSheetLiveData = MutableLiveData<Unit?>()
    val successAddContactLiveData = MutableLiveData<String>()
    private val tabSelectedLiveData = MutableLiveData<TabsTypeContacts>(TabsTypeContacts.TabAll)

    var contactsLiveData = switchMap(tabSelectedLiveData) { currentTab->
        return@switchMap MutableLiveData(
            when(currentTab){
                TabsTypeContacts.TabAll -> useCaseGetAllContacts.getAllContacts()
                TabsTypeContacts.TabFavorites -> getFavoriteContactsUseCase.getFavoriteContacts()
            }
        )
    }

    fun tapOnSave(name: String, number: String) {
        try {
            useCaseSaveContact.saveContact(name, number)
            successAddContactLiveData.value = context.getString(R.string.contact_save)
            refresh()

        } catch (exception: DataEmptyException) {
            successAddContactLiveData.value = context.getString(R.string.data_isempty)
        } catch (exception: NameLenghtException) {
            successAddContactLiveData.value = context.getString(R.string.lengt_name)
        } catch (exception: FormatNumberInvalidException) {
            successAddContactLiveData.value = context.getString(R.string.format_invalid)
        }

    }

    fun tapOnAddContact() {
        oppenBottomSheetLiveData.value = null
    }

    fun selectTab(position: Int) {
        tabSelectedLiveData.value = TabsTypeContacts.getTabByPosition(position)
    }
    fun refresh(){
        tabSelectedLiveData.value = tabSelectedLiveData.value
    }

    fun tapOnChangeFavorite(contact: Contact) {
        contact.isFavorite = !contact.isFavorite
        updateContactUseCase.updateContact(contact)
        tabSelectedLiveData.value = tabSelectedLiveData.value
    }
}