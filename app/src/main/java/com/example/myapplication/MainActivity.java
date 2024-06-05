package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView Error;
    EditText Username;
    EditText Password;
    Button Login;
    TextView Registration;
    SQLiteConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.loginbtn);
        Error = (TextView) findViewById(R.id.error); // Initialize here
        db = new SQLiteConnector(this); // Assuming you have a constructor

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim();
                String password = Password.getText().toString().trim();
                try {
                    boolean res = db.checkUserSignIn(username, password);
                    if (res) {
                        Intent login = new Intent(MainActivity.this, HomeScreen.class);
                        startActivity(login);
                    } else {
                        Error.setText("Invalid Username or Password");
                    }
                } catch (Exception e) {
                    Error.setText("Error logging in. Please try again.");
                }
            }
        });

        Registration = (TextView) findViewById(R.id.registration);
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationActivity();
            }
        });
    }

    private void openRegistrationActivity() {
        Intent registration = new Intent(MainActivity.this, Registration.class);
        startActivity(registration);
    }
}
