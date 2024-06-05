package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
    TextView Error;
    EditText Username;
    EditText Password;
    EditText Confirm_Password;
    Button SignUp;
    TextView SignIn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHelper(this); // Initialize your DatabaseHelper
        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Confirm_Password = (EditText) findViewById(R.id.confirmpassword);
        SignUp = (Button) findViewById(R.id.signupbtn);
        Error = (TextView) findViewById(R.id.error); // Initialize Error TextView

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String confirm_password = Confirm_Password.getText().toString().trim();

                if (!password.equals(confirm_password)) {
                    Error.setText("Mật khẩu không khớp!");
                    return;
                }
                long value = db.addUser(username, password);
                if (value != -1) {
                    Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(Registration.this, MainActivity.class);
                    startActivity(moveToLogin);
                } else {
                    Error.setText("Registration failed, try again!");
                }
            }
        });

        SignIn = (TextView) findViewById(R.id.signin);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    private void openLoginActivity() {
        Intent login = new Intent(Registration.this, MainActivity.class);
        startActivity(login);
    }
}
