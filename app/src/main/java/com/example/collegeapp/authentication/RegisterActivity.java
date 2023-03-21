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

    EditText user_email,user_pass,user_name,user_mobile,user_student_id;
    Button reg_btn;
    private String name,email,password,mobile_no,student_id;
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

        user_mobile=findViewById(R.id.user_mobile);
        user_student_id=findViewById(R.id.user_student_id);

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




    private void validateData() {

        name=user_name.getText().toString();
        mobile_no=user_mobile.getText().toString();
        student_id=user_student_id.getText().toString();
        email=user_email.getText().toString();
        password=user_pass.getText().toString();

        if(name.isEmpty())
        {
            user_name.setError("Required");
            user_name.requestFocus();
        }
        else if(mobile_no.isEmpty())
        {
            user_mobile.setError("Required");
            user_mobile.requestFocus();
        }
        else if(student_id.isEmpty())
        {
            user_student_id.setError("Required");
            user_student_id.requestFocus();
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
                            {
//                                Toast.makeText(RegisterActivity.this, "User register via email authentication"+FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                uploadData();
                            }
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





        Student student=new Student(name,email,student_id,mobile_no,"no",mAuth.getUid());

        dbRef.child(student_id).setValue(student)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()) {

                            Toast.makeText(RegisterActivity.this, "Register Successfully :p.   A request is sent to admin for verification , you will shortly get email for verification status, then you can login , ", Toast.LENGTH_SHORT).show();


                             DatabaseReference rf=FirebaseDatabase.getInstance().getReference();
                             HashMap<String,String> mp=new HashMap<>();

                             mp.put(FirebaseAuth.getInstance().getCurrentUser().getUid(),"no");
                             rf.child("STATUS").setValue(mp)
                                             .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<Void> task) {
                                                     Toast.makeText(RegisterActivity.this, "Status set", Toast.LENGTH_SHORT).show();
                                                 }
                                             });






                              openLogin();


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

    private void openMain() {

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}