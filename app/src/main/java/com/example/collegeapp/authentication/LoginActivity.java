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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private TextView click_here_for_register,forget_pass,user_student_id;
    private EditText user_email,user_pass;
    private Button login_btn;
    private String email,password,student_id;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         db=FirebaseDatabase.getInstance();

         ref=db.getReference();

        click_here_for_register=findViewById(R.id.click_here_for_register);

        user_email=findViewById(R.id.user_email);
        user_pass=findViewById(R.id.user_pass);
        //user_student_id=findViewById(R.id.user_student_id);

        login_btn=findViewById(R.id.login_btn);

        forget_pass=findViewById(R.id.forget_pass);
        auth=FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });







        click_here_for_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             openRegister();
            }


        });


   forget_pass.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {

           openForgetPasswordActivity();

       }


   });


    }


    @Override
    protected void onStart() {
        super.onStart();
       FirebaseAuth auth=FirebaseAuth.getInstance();
       FirebaseUser user=auth.getCurrentUser();

       if(user!=null)
       {

           DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("STATUS");

           myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   String value = dataSnapshot.getValue(String.class);
                   // Do something with the value
                   if(value.equals("yes"))
                   {
                      openMain();
                   }

               }

               @Override
               public void onCancelled(DatabaseError error) {
                   // Failed to read value
               }
           });

       }
//




    }

    private void openRegister() {

        startActivity(new Intent(this,RegisterActivity.class));
        finish();

    }

    private void validateData() {


        email=user_email.getText().toString();
        password=user_pass.getText().toString();
//        student_id=user_student_id.getText().toString();
//        if(student_id.isEmpty())
//        {
//            user_student_id.setError("Required");
//            user_student_id.requestFocus();
//        }
//       else
//
        if(email.isEmpty())
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
            LoginUser();
        }





    }

    private void LoginUser() {

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                           checkStatus();

                        } else {
                            Toast.makeText(LoginActivity.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }



                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void openMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void openForgetPasswordActivity() {
        startActivity(new Intent(this, ForgetPasswordActivity.class));

    }

    private void checkStatus() {

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("STATUS");

        myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                // Do something with the value
                if(value.equals("yes"))
                {
                    openMain();
                }
                else
                    Toast.makeText(LoginActivity.this, "Admin has not verified your account yet. Please wait ....", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }



}

