package br.com.jpstudent.contacts.domain.entities

data class Contact(
    val id : Int,
    val name : String,
    val numberOfContact : String,
    var isFavorite : Boolean

)