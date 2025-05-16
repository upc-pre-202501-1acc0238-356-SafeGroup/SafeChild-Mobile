package com.example.safechild.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.safechild.model.beans.PaymentMethod

@Dao
interface PaymentMethodDAO {
    @Query("select * from payment_methods")
    fun ListaPaymentMethods(): List<PaymentMethod>

    @Insert
    fun insert(paymentMethod: PaymentMethod)

    @Update
    fun update(paymentMethod: PaymentMethod)

    @Delete
    fun delete(paymentMethod: PaymentMethod)

}