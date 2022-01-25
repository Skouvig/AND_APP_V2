package com.example.andappv2.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.andappv2.MainActivity;
import com.example.andappv2.R;
import com.example.andappv2.ui.home.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Profile extends AppCompatActivity {

    private static HomeFragment HomeFragment;
    private ProfileViewModel profileViewModel;

    private FirebaseFirestore db;

    TextView name, mail, totalClicks;
    Button logout, backbtn, savebtn;
    EditText editTextName, editTextMail, editTextAge, editTextUserName;
    int noOfClicks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = FirebaseFirestore.getInstance();

        backbtn = findViewById(R.id.backbtn);
        savebtn = findViewById(R.id.savebtn);
        logout = findViewById(R.id.logout);

        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        totalClicks = findViewById(R.id.totalClicks);
        editTextName = findViewById(R.id.editTextName);
        editTextMail = findViewById(R.id.editTextMail);
        editTextAge = findViewById(R.id.editTextAge);
        editTextUserName = findViewById(R.id.editTextUserName);


        //noOfClicks is fetched from shared preferences
        SharedPreferences sp = getApplicationContext().getSharedPreferences("TotalClicks", Context.MODE_PRIVATE);
        int noOfClicks = sp.getInt("clicks",0);
        totalClicks.setText(Integer.toString(noOfClicks));



        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount !=null)
        {
            name.setText(signInAccount.getDisplayName());
            mail.setText(signInAccount.getEmail());

        } else {
            name.setText("");
            mail.setText("");

        }

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                FirebaseAuth.getInstance().signOut();
                name.setText("Login to update profile");
                mail.setText("");

                editTextName.setVisibility(View.INVISIBLE);
                editTextMail.setVisibility(View.INVISIBLE);
                editTextAge.setVisibility(View.INVISIBLE);
                editTextUserName.setVisibility(View.INVISIBLE);
                totalClicks.setVisibility(View.INVISIBLE);

                Toast.makeText(Profile.this, "You have logget out", Toast.LENGTH_LONG).show();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                onBackPressed();
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String name =  editTextName.getText().toString().trim();
                String mail = editTextMail.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String username = editTextUserName.getText().toString().trim();
                String noOfClicks = totalClicks.getText().toString().trim();

                if (!hasValidationErrors(name, mail, age, username)) {

                    CollectionReference dbProfile = db.collection("profiles");

                    Profiles profiles = new Profiles(
                            name,
                            mail,
                            username,
                            Integer.parseInt(age),
                            Integer.parseInt(noOfClicks)
                    );

                    dbProfile.add(profiles)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Profile.this, "Profile Saved", Toast.LENGTH_LONG).show();

                                }
                            })

                        .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Profile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
    }
    //Checks if the editText fields is filled out correctly
    private boolean hasValidationErrors(String name, String mail, String age, String username){
        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return true;
        }

        if (mail.isEmpty()) {
            editTextMail.setError("Mail required");
            editTextMail.requestFocus();
            return true;
        }

        if (age.isEmpty()) {
            editTextAge.setError("Age required");
            editTextAge.requestFocus();
            return true;
        }

        if (username.isEmpty()) {
            editTextUserName.setError("Username required");
            editTextUserName.requestFocus();
            return true;
        }
        return false;
    }

}