package com.example.contact;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ContactData.class}, version = 1)
public abstract class ContactsDataBase extends RoomDatabase {
    public abstract ContactDAO contactDAO();
}
