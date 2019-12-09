package com.example.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities=(database.class),exportSchema=false,version=1)
public abstract class mydatabase extends RoomDatabase {
    private static mydatabase instance;
    public static synchronized mydatabase getInstance(MainActivity context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getContext(),mydatabase.class,"Favourites")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    mydatabase(){}
    public abstract databaseDAO databaseDAO();

}
