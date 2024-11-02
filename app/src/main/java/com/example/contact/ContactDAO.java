package com.example.contact;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDAO
{
    @Insert
    void insert(ContactData... data);

    @Update
    void update(ContactData data);

    @Delete
    void delete(ContactData data);

    @Query("SELECT * FROM contacts")
    List<ContactData> getAllContacts();


    //Maybe change this to an object
    @Query("SELECT * FROM contacts WHERE contact_name = :name")
    List<ContactData> getContactByName(String name);

    @Query("SELECT * FROM contacts WHERE id = :contactId")
    ContactData getContactById(long contactId);

    @Query("SELECT * FROM contacts WHERE contact_name = :name")
    ContactData getSingleContactByName(String name);




}
