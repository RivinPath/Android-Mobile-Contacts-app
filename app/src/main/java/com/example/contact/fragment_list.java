package com.example.contact;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.contact.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_list extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    String someDemon = "Some Demon";

    String someName;

    public FloatingActionButton addContact;

    private AppData dataViewModel;

    public List<ContactData> contactList;

    public fragment_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_list.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_list newInstance(String param1, String param2) {
        fragment_list fragment = new fragment_list();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment



        //Could be sus
        ContactDAO contactDAO = ContactDBInstance.
                getDataBase(getActivity().getApplicationContext()).contactDAO();

        contactList = contactDAO.getAllContacts();



        List<Contact> contactData = new ArrayList<>();

        //


        for (ContactData contactDataItem : contactList) {
            String contactName = contactDataItem.getContactName();
            String contactImagePath = contactDataItem.getContactImagePath();


            //int imageResource = getImageResourceFromPath(contactImagePath);

            Bitmap imageBitmap = loadImageFromPath(contactImagePath);

            Contact contact = new Contact(contactName, imageBitmap);

            contactData.add(contact);

            //contactData.add(new Contact(contactName, imageBitmap/*imageResource R.drawable.profile*/));
        }





        //contactData.add(new Contact(someDemon,R.drawable.profile));


        //dataViewModel = new ViewModelProvider(this).get(AppData.class);
        dataViewModel = new ViewModelProvider(requireActivity()).get(AppData.class);




        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView rv = rootView.findViewById(R.id.recycler_list);
        rv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false));

        //here
        ListAdapter adapter = new ListAdapter(contactData, dataViewModel);
        rv.setAdapter(adapter);

        FloatingActionButton addContact = rootView.findViewById(R.id.addContactButton);

        addContact.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle bundle = new Bundle();
                int newContact = 1;
                bundle.putInt("newContactKey", newContact);





                fragment_edit_contact editFragment = new fragment_edit_contact();

                editFragment.setArguments(bundle);

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.FragmentFrame, editFragment);
                transaction.addToBackStack(null);


                transaction.commit();

            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);


        outState.putString("someDemon", someDemon);

    }

    private int getImageResourceFromPath(String imagePath)
    {



        if (imagePath == null)
        {

            return R.drawable.profile;
        }



        String imageName = imagePath.substring(0, imagePath.lastIndexOf('.'));


        int resourceId = getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());

        if (resourceId == 0)
        {

            resourceId = R.drawable.grand;
        }

        return resourceId;
    }

    private Bitmap loadImageFromPath(String imagePath)
    {
        if (imagePath == null) {

            return BitmapFactory.decodeResource(getResources(), R.drawable.profile);
        }


        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        if (bitmap == null)
        {

            return BitmapFactory.decodeResource(getResources(), R.drawable.profile);
        }

        return bitmap;
    }

}