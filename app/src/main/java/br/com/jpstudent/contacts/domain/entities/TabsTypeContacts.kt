package br.com.jpstudent.contacts.domain.entities

sealed class TabsTypeContacts(position: Int) {
    object TabAll : TabsTypeContacts(0)
    object TabFavorites : TabsTypeContacts(1)

    companion object {
        fun getTabByPosition(position: Int): TabsTypeContacts {
            return when (position) {
                0 -> TabAll
                1 -> TabFavorites
                else -> TabAll
            }
        }
    }
}