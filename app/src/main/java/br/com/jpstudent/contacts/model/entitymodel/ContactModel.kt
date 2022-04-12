package br.com.jpstudent.contacts.model.entitymodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ContactModel (
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo val name : String,
    @ColumnInfo val numberOfContact : String,
    @ColumnInfo var isFavorite : Boolean,
) : Parcelable
