package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ProfileDelete extends AppCompatActivity {

    public static final String TAG="TAG";
    //Button ddelete;
    EditText proffullname, profuname, profemail,profphoneno;
    ImageView updateImage;
    FirebaseAuth fAuth;
    Button saveBtn, deleteBtn;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    DocumentReference documentReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        Intent data     = getIntent();
        String name     = data.getStringExtra("fname");
        String email    = data.getStringExtra("email");
        String phoneNo  = data.getStringExtra("phoneno");
        String username = data.getStringExtra("uname");

        fAuth  = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user   = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();



       proffullname = findViewById(R.id.proName);
       profuname    = findViewById(R.id.pUname);
       profemail    = findViewById(R.id.prEmail);
       profphoneno  = findViewById(R.id.prPhon);
       updateImage  = findViewById(R.id.pimage);
       saveBtn      = findViewById(R.id.savebtn);
       deleteBtn    = findViewById(R.id.delete);

        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(updateImage);
            }
        });

        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });




       saveBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(proffullname.getText().toString().isEmpty() || profemail.getText().toString().isEmpty() || profphoneno.getText().toString().isEmpty() || profuname.getText().toString().isEmpty()){
                   Toast.makeText(ProfileDelete.this, " 1 or more field empty", Toast.LENGTH_SHORT).show();
                   return;
               }
               String email = profemail.getText().toString();
               user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       DocumentReference  docRef = fStore.collection("users").document(user.getUid());
                       Map <String,Object>  edited = new HashMap<>();
                       edited.put("email",email);
                       edited.put("fname",proffullname.getText().toString());
                       edited.put("phoneno",profphoneno.getText().toString());
                       edited.put("uname",profuname.getText().toString());
                       docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                               Toast.makeText(ProfileDelete.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(getApplicationContext(),user_profileedit.class));

                           }
                       });
                       Toast.makeText(ProfileDelete.this, "Changed the Email", Toast.LENGTH_SHORT).show();

                   }


               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(ProfileDelete.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });
           }
       });

       proffullname.setText(name);
       profuname.setText(username);
       profemail.setText(email);
       profphoneno.setText(phoneNo);

       profemail.setText(user.getEmail());

       deleteBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder builder= new AlertDialog.Builder(ProfileDelete.this);
               builder.setTitle("Delete Profile");
               builder.setMessage("Deleted");
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   Toast.makeText(ProfileDelete.this,"Account Deleted",Toast.LENGTH_LONG).show();
//                                   Intent intent = new Intent(ProfileDelete.this, ProfileDelete.class);
//                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                   startActivity(intent);
                               }else{
                                   Toast.makeText(ProfileDelete.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                               }

                           }
                       });


                   }
               });
               builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });
               builder.show();

           }
       });

        Log.d(TAG, "onCreate: " + name + " " + email + " " + phoneNo + " " + username );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();

                //profileImage.setImageURI(imageUri);

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
                        Picasso.get().load(uri).into(updateImage);
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
        public void DeleteProfile(View view){
            showDialog();
        }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileDelete.this);
        builder.setMessage("Are You Sure to delete Profile");
        builder.setTitle("Delete");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfileDelete.this, "Profile Deleted", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


                    }
                });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                             dialogInterface.dismiss();


            }
        });

    }




}


// ddelete.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        AlertDialog.Builder builder=new AlertDialog.Builder(DisplayDelivaryDetails.this);
//        builder.setTitle("Delete Delivery Details");
//        builder.setMessage("Delete...?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
//        {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
////                       FirebaseDatabase.getInstance().getReference().child("Delivery")
////                               .child(getRef(Transliterator.Position).removeValue();
//        deleteRecord(dlvry1.getId());
//        //Toast.makeText(v.getApp,"Successfully Deleted", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(DisplayDelivaryDetails.this,delivary.class);
//        startActivity(intent);
//        }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        }
//        });
//        builder.show();
//        }
//        });
//        };