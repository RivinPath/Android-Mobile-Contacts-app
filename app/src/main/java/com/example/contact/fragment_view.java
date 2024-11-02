package com.example.contact;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_view#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_view extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AppData appDataViewModel;

    public fragment_view() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_view.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_view newInstance(String param1, String param2) {
        fragment_view fragment = new fragment_view();
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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_view, container, false);

        ImageView displayImage = rootView.findViewById(R.id.displayContactImage);
        TextView displayName = rootView.findViewById(R.id.displayContactName);
        TextView displayNumber = rootView.findViewById(R.id.displayContactNumber);
        TextView displayEmail = rootView.findViewById(R.id.displayContactEmail);

        Button editBtn = rootView.findViewById(R.id.editButton);
        Button backBtn = rootView.findViewById(R.id.backButton);

        // Ex
        appDataViewModel = new ViewModelProvider(requireActivity()).get(AppData.class);




        ContactDAO contactDAO = ContactDBInstance.
                getDataBase(getActivity().getApplicationContext()).contactDAO();

        //ContactData contactDataBase;

        //Copy of ContactData Approach

        /*appDataViewModel.getSetContact().observe(getViewLifecycleOwner(), new Observer<Contact>()
        {
            @Override
            public void onChanged(Contact contactData)
            {
                // This method is called whenever the selectedContact LiveData changes
                if (contactData != null)
                {
                    // Handle the updated ContactData here
                    String contactName = contactData.getName();




                    displayName.setText(contactName);


                }
                else
                {
                    Toast.makeText(getContext(), "no data sent", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        appDataViewModel.getSetContact().observe(getViewLifecycleOwner(), new Observer<Contact>()
        {
            @Override
            public void onChanged(Contact contactData)
            {
                // This method is called whenever the selectedContact LiveData changes
                if (contactData != null)
                {
                    // Handle the updated ContactData here
                    String contactName = contactData.getName();
                    Bitmap image = contactData.getImageBitmap();


                    ContactData contactDataBase =contactDAO.getSingleContactByName(contactName);
                    String contactNumber = contactDataBase.getContactNumber();
                    String contactEmail = contactDataBase.getContactEmail();


                    displayName.setText(contactName);
                    displayImage.setImageBitmap(image);
                    displayNumber.setText(contactNumber);
                    displayEmail.setText(contactEmail);

                    //Toast.makeText(getContext(), "Data sent", Toast.LENGTH_SHORT).show();
                    Log.d("view not null", "contact data is not null");

                    editBtn.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            appDataViewModel.setSelectedContact(contactDataBase);

                            Bundle contactBundle = new Bundle();

                            contactBundle.putString("theContact", contactName);


                            //ContactData contactDataBase =contactDAO.getSingleContactByName(contactName);

                            fragment_edit_contact editFragment = new fragment_edit_contact();

                            editFragment.setArguments(contactBundle);

                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.FragmentFrame, editFragment);
                            transaction.addToBackStack(null);


                            transaction.commit();
                        }
                    });

                }
                else
                {
                    Toast.makeText(getContext(), "no data sent", Toast.LENGTH_SHORT).show();
                    Log.d("view not null", "contact data is null");
                }
            }
        });


        /*editBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //ContactData contactDataBase =contactDAO.getSingleContactByName(contactName);

                fragment_edit_contact editFragment = new fragment_edit_contact();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.FragmentFrame, editFragment);
                transaction.addToBackStack(null);


                transaction.commit();
            }
        });*/

        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fragment_list listFragment = new fragment_list();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.FragmentFrame, listFragment);
                transaction.addToBackStack(null);


                transaction.commit();
            }
        });



        return rootView;

    }
}