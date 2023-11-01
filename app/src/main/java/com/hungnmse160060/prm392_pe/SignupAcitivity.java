package com.hungnmse160060.prm392_pe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupAcitivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText username;
    EditText password;
    EditText passwordConfirm;
    Button signUpButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        signUpButton = findViewById(R.id.signupText);
        signUpButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignupAcitivity.this, LoginActivity.class);
            startActivity(intent);
        });

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.passwordConfirm);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener((view) -> {
            String usernameStr = username.getText().toString();
            String passwordStr =  password.getText().toString();
            String passwordConfirmStr =  passwordConfirm.getText().toString();
            boolean ok = true;
            if (usernameStr.isEmpty()) {
                username.setError("Email can not be empty");
                ok = false;
            }
            if (passwordStr.isEmpty()) {
                password.setError("password can not be empty");
                ok = false;
            }
            if (passwordConfirmStr.isEmpty()) {
                passwordConfirm.setError("passwordConfirm can not be empty");
                ok = false;
            }
            if (!passwordStr.equals(passwordConfirmStr)) {
                password.setError("password not match confirm");
                passwordConfirm.setError("password not match confirm");
                ok = false;
            }
                if (ok) {
                    auth.createUserWithEmailAndPassword(usernameStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupAcitivity.this, "SignUp Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupAcitivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(SignupAcitivity.this, "SignUp Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
        });
    }
}