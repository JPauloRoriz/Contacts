package br.com.jpstudent.contacts.usecase.exceptions

class DataEmptyException(val errorMessage : String) : Exception(errorMessage)
class NameLenghtException(val errorMessage : String) : Exception(errorMessage)
class FormatNumberInvalidException(val errorMessage : String) : Exception(errorMessage)