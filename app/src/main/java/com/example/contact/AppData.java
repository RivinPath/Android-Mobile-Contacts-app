package com.example.contact;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;

public class AppData extends ViewModel
{
    private MutableLiveData<String> name;
    private MutableLiveData<String> number;
    private MutableLiveData<String> email;
    private MutableLiveData<Integer> image;

    private MutableLiveData<ContactData> selectedContact;
    private MutableLiveData<Contact> setContact;

    //Sus
    public AppData()
    {
        name = new MutableLiveData<>();
        name.setValue("");

        number = new MutableLiveData<>();
        number.setValue("");

        email = new MutableLiveData<>();
        email.setValue("");

        image = new MutableLiveData<>();
        image.setValue(0);

        selectedContact = new MutableLiveData<>();

        setContact = new MutableLiveData<>();

    }


    public void setSelectedContact(ContactData contact)
    {
        selectedContact.setValue(contact);
    }

    public void setContact(Contact contact)
    {
        setContact.setValue(contact);
    }

    /*public MutableLiveData<ContactData> getSelectedContact()
    {
        return selectedContact;
    }*/

    public LiveData<ContactData> getSelectedContact()
    {
        return selectedContact;
    }
    /*public MutableLiveData<Contact> getSetContact()
    {
        return setContact;
    }*/

    public LiveData<Contact> getSetContact()
    {
        return setContact;
    }
}
