package com.example.collegeapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText user_email;
    private Button sendforgetEmail;
    private TextView click_here_for_login;
    private String email;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);



        user_email=findViewById(R.id.user_email);
        sendforgetEmail=findViewById(R.id.sendforgetEmail);
        click_here_for_login=findViewById(R.id.click_here_for_login);


        auth=FirebaseAuth.getInstance();



        click_here_for_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });


        sendforgetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             validateData();
            }
        });


    }

    private void openLogin() {

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    private void validateData() {


        email=user_email.getText().toString();


        if(email.isEmpty()) {
            user_email.setError("Required");
            user_email.requestFocus();
        }
        else
        {
           forgetPassword();
        }





    }

    private void forgetPassword() {

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(ForgetPasswordActivity.this, "Check your email ,a link is sent to your email", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ForgetPasswordActivity.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgetPasswordActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
}