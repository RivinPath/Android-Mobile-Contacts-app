package com.example.contact;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.Contact;
import com.example.contact.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListVH>
{

    //ArrayList<Contact> contactData;

    private AppData viewModel;

    private List<Contact> contactData;

    public ListAdapter(List<Contact> contactData, AppData viewModel)
    {
        this.contactData = contactData;
        this.viewModel =  viewModel;
    }

    @NonNull
    @Override
    public ListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_contact,parent,false);
        ListVH listVH = new ListVH(view, this);
        return listVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ListVH holder, int position)
    {
        Contact singleData = contactData.get(position);

        //Ex

        //ContactData Data =

        //End Ex

        holder.name.setText(singleData.getName());

        holder.image.setImageBitmap(singleData.getImageBitmap());

        holder.name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (viewModel != null)
                {
                    viewModel.setContact(singleData);
                    String name = singleData.getName();
                    Bitmap image = singleData.getImageBitmap();
                    Log.d("pressed name", "name is  " + name);
                    Log.d("pressed number", "numbner is  " + image);
                }
                else
                {
                    Log.d("viewModel empty", "viewModel in adapter is empty  ");
                }


                fragment_view viewContact = new fragment_view();
                FragmentManager fragmentManager = ((AppCompatActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.FragmentFrame, viewContact);
                //Ex
                transaction.addToBackStack(null);
                //End Ex
                transaction.commit();
            }
        });



        //Ex
        holder.bind(position);
        //End Ex

    }

    @Override
    public int getItemCount()
    {
        return contactData.size();
    }


    public List<Contact> getContactData()
    {
        return contactData;
    }

    public interface OnItemClickListener
    {
        void onItemClick(ContactData contactData);
    }




}
