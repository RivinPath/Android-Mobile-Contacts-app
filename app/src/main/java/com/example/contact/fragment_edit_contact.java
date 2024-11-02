package com.example.contact;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_edit_contact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_edit_contact extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private File photoFile;

    private AppData appDataViewModel;
    ImageView contactImage;

    String imagePath;

    String updatePath;

    //Camera
    ActivityResultLauncher<Intent> photoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) { // Use Activity.RESULT_OK here
                    Intent data = result.getData();
                    processPhotoResult(data);
                }
            });



    public fragment_edit_contact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_edit_contact.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_edit_contact newInstance(String param1, String param2) {
        fragment_edit_contact fragment = new fragment_edit_contact();
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
        View rootView = inflater.inflate(R.layout.fragment_edit_contact, container, false);

        contactImage = rootView.findViewById(R.id.displayImage);
        EditText name = rootView.findViewById(R.id.contactName);
        EditText number = rootView.findViewById(R.id.contactNumber);
        EditText email = rootView.findViewById(R.id.contactEmail);

        Button saveBtn = rootView.findViewById(R.id.saveButton);
        Button deleteBtn = rootView.findViewById(R.id.deleteButton);



        Bundle args = getArguments();


        int updateContact = 0;


        /*Bundle args = getArguments();*/
        if (args != null)
        {
            updateContact = args.getInt("newContactKey", 0); // Provide a default value if needed


        }

        if (updateContact == 1)
        {
            //
        }
        else
        {
            ContactDAO contactDAO = ContactDBInstance.
                    getDataBase(getActivity().getApplicationContext()).contactDAO();

            String contactName = "";


            if (args != null) {

                contactName = args.getString("theContact", "");
                //Toast.makeText(getContext(), "name  "+contactName, Toast.LENGTH_SHORT).show();

                ContactData contactDataBase =contactDAO.getSingleContactByName(contactName);

                name.setText(contactDataBase.getContactName());
                number.setText(contactDataBase.getContactNumber());
                email.setText(contactDataBase.getContactEmail());

                String contactImagePath = contactDataBase.getContactImagePath();
                Bitmap imageBitmap = loadImageFromPath(contactImagePath);
                contactImage.setImageBitmap(imageBitmap);

            }
        }




        //Bundle bundle = new Bundle();

        /*ContactDAO contactDAO = ContactDBInstance.
                getDataBase(getActivity().getApplicationContext()).contactDAO();

        String contactName = "";


        if (args != null) {

            contactName = args.getString("theContact", "");
            Toast.makeText(getContext(), "name  "+contactName, Toast.LENGTH_SHORT).show();

            ContactData contactDataBase =contactDAO.getSingleContactByName(contactName);

            name.setText(contactDataBase.getContactName());
            number.setText(contactDataBase.getContactNumber());
            email.setText(contactDataBase.getContactEmail());

            String contactImagePath = contactDataBase.getContactImagePath();
            Bitmap imageBitmap = loadImageFromPath(contactImagePath);
            contactImage.setImageBitmap(imageBitmap);

        }*/



        saveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int newContactKey = 0;


                /*Bundle args = getArguments();*/
                if (args != null) {
                    newContactKey = args.getInt("newContactKey", 0); // Provide a default value if needed


                }







                String get_name = name.getText().toString();
                String get_number =number.getText().toString();
                String get_email = email.getText().toString();

                /*bundle.putString("get_name", get_name);
                bundle.putString("get_number", get_number);
                bundle.putString("get_email", get_email);*/

                fragment_list listFragment = new fragment_list();

                //listFragment.setArguments(bundle);

                //Could be sus
                ContactDAO contactDAO = ContactDBInstance.getDataBase(getActivity().getApplicationContext()).contactDAO();

                //Ex

                String contactName = "";


                if (args != null) {

                    contactName = args.getString("theContact", "");
                    //Toast.makeText(getContext(), "name  "+contactName, Toast.LENGTH_SHORT).show();

                    //ContactData contactDataBase =contactDAO.getSingleContactByName(contactName);





                }

                ContactData contactDataBase =contactDAO.getSingleContactByName(contactName);

                //End Ex

                ContactData newContact = new ContactData();

                newContact.setContactName(get_name);
                newContact.setContactNumber(get_number);
                newContact.setContactEmail(get_email);

                //save image
                if (photoFile != null && photoFile.exists())
                {
                    imagePath = saveImageToStorage(photoFile);
                    newContact.setContactImagePath(imagePath);

                    if (newContactKey != 1)
                    {
                        contactDataBase.setContactImagePath(imagePath);
                    }


                    //Toast.makeText(getContext(), "not null  ", Toast.LENGTH_SHORT).show();

                    //updatePath = saveImageToStorage(photoFile);
                    //contactDataBase
                }


                if(get_name.isEmpty()|| get_number.isEmpty()|| get_email.isEmpty())
                {
                    Toast.makeText(getContext(), "Field is empty ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (newContactKey == 1)
                    {
                        Toast.makeText(getContext(), "Contact saved", Toast.LENGTH_SHORT).show();

                        contactDAO.insert(newContact);
                    }
                    else
                    {
                        //ContactDAO contactDAO = ContactDBInstance.getDataBase(getActivity().getApplicationContext()).contactDAO();

                        String updateName = name.getText().toString();
                        String updateNumber =number.getText().toString();
                        String updateEmail = email.getText().toString();

                        //ContactData updateContact = new ContactData();

                        //String contactName = "";


                        if (args != null) {

                            contactName = args.getString("theContact", "");
                            //Toast.makeText(getContext(), "name  "+contactName, Toast.LENGTH_SHORT).show();

                            //ContactData contactDataBase =contactDAO.getSingleContactByName(contactName);

                            contactDataBase.setContactName(updateName);
                            contactDataBase.setContactNumber(updateNumber);
                            contactDataBase.setContactEmail(updateEmail);

                            //String contactImagePath = contactDataBase.getContactImagePath();
                            //Bitmap imageBitmap = loadImageFromPath(contactImagePath);
                            //contactDataBase.setContactImagePath(imagePath);

                            if (photoFile != null && photoFile.exists())
                            {
                                //imagePath = saveImageToStorage(photoFile);
                                //imagePath = contactDataBase.getContactImagePath();
                                contactDataBase.setContactImagePath(imagePath);
                            }
                            else
                            {
                                //Toast.makeText(getContext(), "image is null  ", Toast.LENGTH_SHORT).show();
                            }

                            Toast.makeText(getContext(), "Contact Updated ", Toast.LENGTH_SHORT).show();

                            contactDAO.update(contactDataBase);

                        }


                    }
                }





                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.FragmentFrame, listFragment);
                transaction.addToBackStack(null);


                transaction.commit();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int newContactKey = 0;


                if (args != null) {
                    newContactKey = args.getInt("newContactKey", 0);

                }

                if (newContactKey == 1)
                {
                    Toast.makeText(getContext(), "Contact Never Saved", Toast.LENGTH_SHORT).show();

                    fragment_list listFragment = new fragment_list();
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.FragmentFrame, listFragment);
                    transaction.addToBackStack(null);


                    transaction.commit();
                }
                else
                {
                    ContactDAO contactDAO = ContactDBInstance.
                            getDataBase(getActivity().getApplicationContext()).contactDAO();

                    String contactName = "";


                    if (args != null)
                    {

                        contactName = args.getString("theContact", "");
                        //Toast.makeText(getContext(), "name  " + contactName, Toast.LENGTH_SHORT).show();

                        ContactData contactDataBase = contactDAO.getSingleContactByName(contactName);

                        Toast.makeText(getContext(), "Contact " +contactName+" Deleted", Toast.LENGTH_SHORT).show();

                        contactDAO.delete(contactDataBase);



                        fragment_list listFragment = new fragment_list();
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.FragmentFrame, listFragment);
                        transaction.addToBackStack(null);


                        transaction.commit();


                    }
                }


            }
        });

        contactImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                photoButtonClicked();
            }
        });



        return rootView;


    }



    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);


        outState.putString("imagePath", imagePath);
    }

    //Camera

    private void photoButtonClicked()
    {

        photoFile = new File(requireContext().getFilesDir(),"photo.jpg");
        Uri cameraUri = FileProvider.getUriForFile(requireContext(), requireActivity().getPackageName() + ".fileprovider", photoFile);
        Intent photoIntent = new Intent();
        photoIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT,cameraUri);

        PackageManager pm = requireActivity().getPackageManager();
        for(ResolveInfo a : pm.queryIntentActivities(
                photoIntent, PackageManager.MATCH_DEFAULT_ONLY)) {

            requireActivity().grantUriPermission(a.activityInfo.packageName, cameraUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }

        photoLauncher.launch(photoIntent);

        imagePath = saveImageToStorage(photoFile);

    }


    protected void processPhotoResult(Intent data)
    {
        Bitmap photo = BitmapFactory.decodeFile(photoFile.toString());
        contactImage.setImageBitmap(photo);
    }

    private String saveImageToStorage(File imageFile)
    {
        String imagePath = null;
        try {

            File imageDir = new File(requireContext().getFilesDir(), "images");
            if (!imageDir.exists())
            {
                imageDir.mkdirs();
            }


            String fileName = "image" + System.currentTimeMillis() + ".jpg";
            File destFile = new File(imageDir, fileName);


            if (imageFile.exists())
            {
                imageFile.renameTo(destFile);
                imagePath = destFile.getAbsolutePath();
            }
            else
            {
                //Toast.makeText(getContext(), "it's not getting", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagePath;
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