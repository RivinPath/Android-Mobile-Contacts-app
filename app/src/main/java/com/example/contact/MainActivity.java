package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG_LIST_FRAGMENT = "listFragment";
    private static final String TAG_EDIT_FRAGMENT = "editFragment";
    private static final String TAG_VIEW_FRAGMENT = "viewFragment";

    private FragmentManager fragmentManager;
    private fragment_list listFragment;
    private fragment_edit_contact editFragment;
    private fragment_view viewFragment;

    private AppData appDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fragmentManager = getSupportFragmentManager();

        //Ex
        appDataViewModel = new ViewModelProvider(this).get(AppData.class);
        //End Ex


        if (savedInstanceState == null)
        {

            listFragment = new fragment_list();
            editFragment = new fragment_edit_contact();

            appDataViewModel = new ViewModelProvider(this).get(AppData.class);


            fragmentManager.beginTransaction()
                    .add(R.id.FragmentFrame, listFragment, TAG_LIST_FRAGMENT)
                    .commit();
        }
        else
        {

            listFragment = (fragment_list) fragmentManager.findFragmentByTag(TAG_LIST_FRAGMENT);
            editFragment = (fragment_edit_contact) fragmentManager.findFragmentByTag(TAG_EDIT_FRAGMENT);
            viewFragment = (fragment_view) fragmentManager.findFragmentByTag(TAG_VIEW_FRAGMENT);
        }

    }

    public AppData getAppDataViewModel() {
        return appDataViewModel;
    }


    public void showEditFragment()
    {
        fragmentManager.beginTransaction()
                .replace(R.id.FragmentFrame, editFragment, TAG_EDIT_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    public void showListFragment()
    {
        fragmentManager.popBackStack();
    }
}