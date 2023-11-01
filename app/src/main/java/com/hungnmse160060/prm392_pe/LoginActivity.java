package com.hungnmse160060.prm392_pe;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    EditText username;
    EditText password;
    Button signUpButton;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();

        signUpButton = findViewById(R.id.signupText);
        signUpButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupAcitivity.class);
            startActivity(intent);
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener((view) -> {
            String usernameStr = username.getText().toString();
            String passwordStr =  password.getText().toString();
            boolean ok = true;
            if (usernameStr.isEmpty()) {
                username.setError("Email can not be empty");
                ok = false;
            }
            if (passwordStr.isEmpty()) {
                password.setError("password can not be empty");
                ok = false;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(usernameStr).matches()) {
                username.setError("Email not match");
                ok = false;
            }
            if (ok) {
                auth.signInWithEmailAndPassword(usernameStr, passwordStr)
                        .addOnSuccessListener(
                        new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
            }
        } );
    }
}