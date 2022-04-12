package br.com.jpstudent.contacts

import androidx.room.Room
import br.com.jpstudent.contacts.model.database.AppDatabase
import br.com.jpstudent.contacts.model.repository.ContactsRepository
import br.com.jpstudent.contacts.usecase.GetAllContactsUseCase
import br.com.jpstudent.contacts.usecase.GetFavoriteContactsUseCase
import br.com.jpstudent.contacts.usecase.SaveContactUseCase
import br.com.jpstudent.contacts.usecase.UpdateContactUseCase
import br.com.jpstudent.contacts.viewmodel.ContactsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
//database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database"
        ).allowMainThreadQueries().build()
    }

    //Dao
    factory { get<AppDatabase>().contactDao() }

    //repository
    factory { ContactsRepository(get()) }

    //usecase
    single { SaveContactUseCase(get(), get()) }
    single { GetAllContactsUseCase(get()) }
    single { GetFavoriteContactsUseCase(get()) }
    single { UpdateContactUseCase(get()) }

    //viewmodel
    viewModel { ContactsViewModel(get(), get(), get(), get(), get()) }

}