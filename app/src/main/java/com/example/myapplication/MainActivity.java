package com.example.myapplication;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView Error;
    EditText Username;
    EditText Password;
    Button Login;
    TextView Registration;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.loginbtn);
        Error = (TextView) findViewById(R.id.error); // Initialize here
        db = new DatabaseHelper(this); // Assuming you have a constructor

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim();
                String password = Password.getText().toString().trim();
                try {
                    boolean res = db.checkUserLogin(username, password);
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