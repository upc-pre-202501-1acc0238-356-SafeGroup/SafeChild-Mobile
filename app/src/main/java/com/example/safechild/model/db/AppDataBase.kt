package com.example.safechild.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.safechild.model.beans.PaymentMethod
import com.example.safechild.model.dao.PaymentMethodDAO


@Database(entities = [PaymentMethod::class], version = 2)
abstract class AppDataBase : RoomDatabase(){
    abstract fun paymentMethodDao(): PaymentMethodDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase{
            val tempInstance = INSTANCE

            if(tempInstance!= null){
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                )
                    .build()
                INSTANCE= instance
                return instance
            }

        }
    }

}