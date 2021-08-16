package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (!isValidPassword(password)) {
                    Toast.makeText(SignUpActivity.this, "Password doesn't match rules"
                            , Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(SignUpActivity.this,LogInActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }
    Pattern lowerCase = Pattern.compile("^.*[a-z].*$");
    Pattern upperCase = Pattern.compile("^.*[A-Z].*$");
    Pattern number = Pattern.compile("^.*[0-9].*$");
    Pattern specialCharacter = Pattern.compile("^.*[^a-zA-Z0-9].*$");
    private Boolean isValidPassword(String password) {
// Checks if password length is less than 8
        if (password.length() < 8) {
            return false;
        }
// Returns false if password doesn't contain a lower case character
        if (!lowerCase.matcher(password).matches()) {
            return false;
        }
// Returns false if password doesn't contain an upper case character
        if (!upperCase.matcher(password).matches()) {
            return false;
        }
// Returns false if password doesn't contain a number
        if (!number.matcher(password).matches()) {
            return false;
        }
// Returns false if password doesn't contain a special character
        if (!specialCharacter.matcher(password).matches()) {
            return false;
        }
        return true;
    }
}