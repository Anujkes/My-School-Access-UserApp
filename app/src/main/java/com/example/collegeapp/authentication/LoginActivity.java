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

public class LoginActivity extends AppCompatActivity {

    private TextView click_here_for_register,forget_pass;
    private EditText user_email,user_pass;
    private Button login_btn;
    private String email,password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        click_here_for_register=findViewById(R.id.click_here_for_register);

        user_email=findViewById(R.id.user_email);
        user_pass=findViewById(R.id.user_pass);
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

    private void openRegister() {
        startActivity(new Intent(this,RegisterActivity.class));
        finish();

    }

    private void validateData() {


        email=user_email.getText().toString();
        password=user_pass.getText().toString();

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
                           openMain();

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
}