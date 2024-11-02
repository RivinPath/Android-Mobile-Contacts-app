package com.example.contact;

import android.graphics.Bitmap;

public class Contact
{
    private String name;
    private String number;
    private String email;
    private int contact_image;
    private Bitmap imageBitmap;

    public Contact(String name, Bitmap imageBitmap)
    {
        this.name = name;

        this.imageBitmap = imageBitmap;

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getContact_image()
    {
        return contact_image;
    }

    public void setContact_image(int contact_image)
    {
        this.contact_image = contact_image;
    }



    public Bitmap getImageBitmap() {
        return imageBitmap;
    }
}
