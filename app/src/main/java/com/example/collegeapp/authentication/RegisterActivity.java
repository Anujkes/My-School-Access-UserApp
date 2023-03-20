package com.example.collegeapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.MainActivity;
import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText user_email,user_pass,user_name;
    Button reg_btn;
    private String name,email,password;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference ;
    private DatabaseReference dbRef ;
    private TextView click_here_for_login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
        user_pass=findViewById(R.id.user_pass);
        reg_btn=findViewById(R.id.reg_btn);
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        reference= database.getReference();

        click_here_for_login=findViewById(R.id.click_here_for_login);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }


        });



        click_here_for_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
           openMain();
        }
    }

    private void openMain() {

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void validateData() {

        name=user_name.getText().toString();
        email=user_email.getText().toString();
        password=user_pass.getText().toString();

        if(name.isEmpty())
        {
            user_name.setError("Required");
            user_name.requestFocus();
        }
        else if(email.isEmpty())
        {
            user_email.setError("Required");
            user_email.requestFocus();
        }
        else if(password.isEmpty())
        {
            user_pass.setError("Required");
            user_pass.requestFocus();
        }
        else
        {
           createUser();
        }





    }

    private void createUser() {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           uploadData();
                        } else {

                            Toast.makeText(RegisterActivity.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


    }

    private void uploadData() {

    dbRef=reference.child("users");
    String key=dbRef.push().getKey();

        HashMap<String,String>user=new HashMap<>();
        user.put("key",key);
        user.put("name",name);
        user.put("email",email);

        dbRef.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                             Toast.makeText(RegisterActivity.this, "Register Successfully :p", Toast.LENGTH_SHORT).show();
                             openMain();


                        }
                        else{

                            Toast.makeText(RegisterActivity.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void openLogin() {

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}