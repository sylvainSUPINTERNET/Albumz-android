package com.example.sylvain.albumz;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;

/**
 * Created by julien on 28/11/2017.
 */

public class UploadFragment extends Fragment implements View.OnClickListener {


    private FirebaseAuth mAuth;

    //User Auth via FireBase
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public FirebaseUser currentUser;


    private Uri selectedImageUri;


    //UI creation
    EditText albumName;
    Switch publicSwitch;
    Button album_submit;
    Button select;

    //User.Class
    Album Album_utils = new Album(); //constructor only to get Utils methods (clear, isExist etc )

    //user try to auth
    User userToAuthRegister;
    User userToAuthLogin;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.upload, container, false); //get current layout to get UI element

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //Login
        albumName = view.findViewById(R.id.albumName);
        publicSwitch = view.findViewById(R.id.publicAlbum);
        //Submit login
        album_submit = view.findViewById(R.id.album_submit);
        select = view.findViewById(R.id.select);
        select.setOnClickListener(this);
        album_submit.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();


        // Inflate the layout for this fragment

        return view;

    }


    public void onClickUploadImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose picture"), 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {

            selectedImageUri = data.getData();

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.select:

                onClickUploadImage(view);
                break;
            case R.id.album_submit:

                Uri file = selectedImageUri;

                final String key = mDatabase.child("messages").push().getKey();

                if (file != null) {

                    StorageReference imageRef = storageRef.child("images/" + key + " .jpg");
                    UploadTask uploadTask = null;
                    try {
                        uploadTask = imageRef.putStream(getActivity().getContentResolver().openInputStream(selectedImageUri)).addOnCompleteListener(new OnCompleteListener<UploadTask>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask> task) {
                                task.getResult().;
                            }
                        });
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }


        }


    }


}
