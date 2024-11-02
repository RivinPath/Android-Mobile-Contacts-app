package com.example.contact;

import android.content.Context;

import androidx.room.Room;

public class ContactDBInstance
{
    public static ContactsDataBase dataBase;

    public static ContactsDataBase getDataBase(Context context)
    {
        if (dataBase == null)
        {
            dataBase = Room.databaseBuilder(context, ContactsDataBase.class, "app_database")
                    .allowMainThreadQueries().build();
        }


        return dataBase;
    }

}
