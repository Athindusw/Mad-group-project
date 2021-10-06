package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class user_profileedit extends AppCompatActivity {

    TextView fullName,email,phoneNo,uname,fullName1,uname1;
    TextView fullnameLable, usernameLable;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    ImageView image;
    Button mlogout, editButton;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profileedit);

        //hooks
        fullName = findViewById(R.id.prName);
        fullName1 = findViewById(R.id.fullname_field);
        email = findViewById(R.id.prEmail);
        phoneNo = findViewById(R.id.prPhon);
        image = findViewById(R.id.profile_image);
        editButton = findViewById(R.id.editbtn);


        uname = findViewById(R.id.username_field);
        uname1 = findViewById(R.id.pUname);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                phoneNo.setText(documentSnapshot.getString("phoneno"));
                fullName.setText(documentSnapshot.getString("fname"));
                email.setText(documentSnapshot.getString("email"));
                uname1.setText(documentSnapshot.getString("uname"));

                uname.setText(documentSnapshot.getString("uname"));
                fullName1.setText(documentSnapshot.getString("fname"));


            }
        });


        //showAllUserData();


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open gallery
                Intent i = new Intent(v.getContext(),ProfileDelete.class);
                i.putExtra("fname",fullName.getText().toString());
                i.putExtra("email",email.getText().toString());
                i.putExtra("phoneno",phoneNo.getText().toString());
                i.putExtra("uname",uname1.getText().toString());
                startActivity(i);
//

            }
        });


//        editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //open Gallery in Phone
//                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(openGalleryIntent,1000);
//            }
//        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();

                //image.setImageURI(imageUri);

               uploadImageToFirebase(imageUri);


            }
        }

    }

    private void uploadImageToFirebase(Uri imageUri) {
        // uplaod image to firebase storage
        final StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(image);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed.", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void uploadImageToFirebase(Uri imageUri) {
//        //upload image to firebase storage
//        StorageReference fileRef = storageReference.child("profile.jpg");
//        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//            }
//        })
//    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout from system
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
        finish();


    }


//    private void showAllUserData() {
//        Intent intent = getIntent();
//        String user_username = intent.getStringExtra("username");
//        String user_name = intent.getStringExtra("name");
//        String user_email = intent.getStringExtra("email");
//        String user_phoneNo = intent.getStringExtra("phoneNo");
//        String user_password = intent.getStringExtra("password");
//
//        fullnameLable.setText(user_name);
//        usernameLable.setText(user_username);
//        fullname.getEditText().setText(user_name);
//        fullname.getEditText().setText(user_email);
//        fullname.getEditText().setText(user_phoneNo);
//        fullname.getEditText().setText(user_password);
//
//    }
}