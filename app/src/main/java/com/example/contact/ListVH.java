package com.example.contact;

import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListVH extends RecyclerView.ViewHolder
{
    public ImageView image;
    public TextView name;
    private AppData appDataViewModel;

    private ContactData contactData;
    private Contact contact;

    //Ex
    private int position;

    private fragment_list fragmentList;

    private ListAdapter adapter;



    //End Ex



    public ListVH(@NonNull View itemView, ListAdapter adapter)
    {
        super(itemView);
        this.adapter = adapter;



        image = itemView.findViewById(R.id.contact_image);
        name = itemView.findViewById(R.id.contact_name);

        /*name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                //Ex
                AppData appDataViewModel = new ViewModelProvider((MainActivity) itemView.getContext()).get(AppData.class);

                if (position != RecyclerView.NO_POSITION)
                {

                        // Set the selected contact data
                        appDataViewModel.setSelectedContact(contactData);
                        appDataViewModel.setContact(contact);


                        fragment_view viewContact = new fragment_view();
                        FragmentManager fragmentManager = ((AppCompatActivity) view.getContext()).getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.FragmentFrame, viewContact);
                        //Ex
                        transaction.addToBackStack(null);
                        //End Ex
                        transaction.commit();

                }
            }

        });*/

    }

    //Ex
    public void bind(int position)
    {
        this.position = position;
    }

    //End Ex
}
