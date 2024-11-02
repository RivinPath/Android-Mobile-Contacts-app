package com.example.contact;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class ContactData
{
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "contact_name")
    private String contactName;
    @ColumnInfo(name = "contact_number")
    private String contactNumber;

    @ColumnInfo(name = "contact_email")
    private String contactEmail;

    @ColumnInfo(name = "contact_image")
    private String contactImagePath;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    public String getContactImagePath()
    {
        return contactImagePath;
    }

    public void setContactImagePath(String contactImagePath)
    {
        this.contactImagePath = contactImagePath;
    }
}
