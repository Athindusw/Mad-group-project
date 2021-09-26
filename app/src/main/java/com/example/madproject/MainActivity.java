package com.example.madproject;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText name, price;
    Food food;
    RadioButton koththu, rice, burger;
    Uri imageUri;
    ImageView img;
    Button add, back, upload;
    Bitmap bitmap;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseStorage storage;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.editTxt1);
        price = findViewById(R.id.editTxt2);
        add = findViewById(R.id.button2);
        back = findViewById(R.id.button3);
        upload = findViewById(R.id.button1);
        food = new Food();
        koththu = findViewById(R.id.rButton1);
        rice = findViewById(R.id.rButton2);
        burger = findViewById(R.id.rButton3);
        img=findViewById(R.id.img);

        storage = FirebaseStorage.getInstance();

        reference = FirebaseDatabase.getInstance().getReference().child("Food");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    i = (int)dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(), "Here " + bundle.getString("some"), Toast.LENGTH_SHORT).show();

            }
        }

        //run below method on img click
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Products.class));
                finish();
            }
        });
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result != null){
                        img.setImageURI(result);
                        imageUri = result;
                        //food.setFoodImage(String.valueOf(img));

                    }
                }
    });

    private void uploadImage(){
        //progress dialogue
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Uploading...");


        //to access the result, set  it as global.
        if(imageUri != null){
            dialog.show();

            final StorageReference h = storage.getReference().child("images/" + System.currentTimeMillis() + "." + getFileExtension(imageUri));
//store image file

            h.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    h.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String radio1 = koththu.getText().toString();
                            String radio2 = rice.getText().toString();
                            String radio3 = burger.getText().toString();


                            try {
                                if (TextUtils.isEmpty(name.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Enter name", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(price.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Enter price", Toast.LENGTH_SHORT).show();
                                else {
                                    food.setFoodName(name.getText().toString().trim());
                                    food.setFoodPrice(price.getText().toString().trim());

                                    reference.child(String.valueOf(i+1)).setValue(food);

                                    if(koththu.isChecked()){
                                        food.setFoodCategory(radio1);
                                        reference.child(String.valueOf(i+1)).setValue(food);
                                    }else if(rice.isChecked()){
                                        food.setFoodCategory(radio2);
                                        reference.child(String.valueOf(i+1)).setValue(food);
                                    }else {
                                        food.setFoodCategory(radio3);
                                        reference.child(String.valueOf(i+1)).setValue(food);
                                    }
//
//                                    HashMap<String,String> hashMap = new HashMap<>();
//                                    hashMap.put(img,String.valueOf(uri));

                                    food.setFoodImage(String.valueOf(uri));
                                    reference.child(String.valueOf(i+1)).setValue(food);
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                                }
                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Price", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Could not saved", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });

//            h.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                    h.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Uri> task) {
//
//                            HashMap<ImageView, String> hashMap = new HashMap<>();
//                            hashMap.put(img,String.valueOf(task));
//
////                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
////                                @Override
////                                public void onComplete(@NonNull Task<Void> task) {
////                                    Toast.makeText(MainActivity.this, "kkkkkkkkkkkkkkkkkkk", Toast.LENGTH_SHORT).show();
////
////                                }
////                            });
//                            //reference.child(String.valueOf(task));
//                        }
//                    });
//                    if(task.isSuccessful()){
//                        dialog.dismiss();
//                        //food.setFoodImage(task.getResult().toString());
//                        //String food1 = new String(reference.getDownloadUrl().toString())
//                        Toast.makeText(MainActivity.this, "image uploaded successfully", Toast.LENGTH_SHORT).show();
//
//                    } else{
//                        dialog.dismiss();
//                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            });
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
}