package com.example.safechild.model.beans.payments

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_methods")
data class PaymentMethod(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo(name = "card_holder_name")
    var cardHolderName: String,

    @ColumnInfo(name = "card_number")
    var cardNumber: String,

    @ColumnInfo(name = "expiry_date")
    var expiryDate: String,

    @ColumnInfo(name = "cvv")
    var cvv: String,

    @ColumnInfo(name = "card_Type")
    var cardType: String // VISA, Mastercard, ...

)