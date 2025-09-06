package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay

@Database(entities = [ItemDisplay::class], version = 2, exportSchema = false)

abstract class ItemDataBase :RoomDatabase(){
    abstract fun itemDao() :ItemDao


        // Singleton Design Pattern
          companion object{
                 @Volatile
         private var INSTANCE: ItemDataBase?= null
          fun getInstance(context: Context): ItemDataBase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    // Creating the database object
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDataBase::class.java,
                        "items_dp"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
        }
    }


