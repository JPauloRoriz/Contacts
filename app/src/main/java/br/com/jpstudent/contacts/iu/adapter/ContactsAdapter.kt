package br.com.jpstudent.contacts.iu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jpstudent.contacts.R
import br.com.jpstudent.contacts.domain.entities.Contact

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {
    var contacts = listOf<Contact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var clickStar: ((Contact) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_contact, parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.binding(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameContact: TextView by lazy { itemView.findViewById(R.id.name_contact) }
        private val numberOfContact: TextView by lazy { itemView.findViewById(R.id.number_contact) }
        private val ckFavorite: CheckBox by lazy { itemView.findViewById(R.id.is_favorite) }

        fun binding(contact: Contact) {
            ckFavorite.setOnClickListener {
                clickStar?.invoke(contact)
            }
            nameContact.text = contact.name
            numberOfContact.text = contact.numberOfContact
            ckFavorite.isChecked = contact.isFavorite
        }
    }
}