package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
    TextView Error;
    EditText Username;
    EditText Email;
    EditText Password;
    EditText Confirm_Password;
    Button SignUp;
    TextView SignIn;
    SQLiteConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new SQLiteConnector(this);
        Username = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Confirm_Password = findViewById(R.id.confirmpassword);
        SignUp = findViewById(R.id.signupbtn);
        Error = findViewById(R.id.error);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String confirm_password = Confirm_Password.getText().toString().trim();

                if (!validateEmail(email)) {
                    Error.setText("Email không hợp lệ!");
                    return;
                }

                if (!password.equals(confirm_password)) {
                    Error.setText("Mật khẩu không khớp!");
                    return;
                }

                if (db.checkUserExists(username)) {
                    Error.setText("Tên người dùng đã tồn tại!");
                    return;
                }

                if (db.checkUserSignUp(email)) {
                    Error.setText("Email đã tồn tại!");
                    return;
                }

                User user = new User(username, email, password);
                db.addUser(user);
                Toast.makeText(Registration.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent moveToLogin = new Intent(Registration.this, MainActivity.class);
                startActivity(moveToLogin);
            }
        });

        SignIn = findViewById(R.id.signin);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    private boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void openLoginActivity() {
        Intent login = new Intent(Registration.this, MainActivity.class);
        startActivity(login);
    }
}
