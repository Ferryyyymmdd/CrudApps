package com.ferrysaptawan.crudapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password, confirmPassword;
    Button btnSignup, btnHaveAccount;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        btnSignup = findViewById(R.id.signup);
        btnHaveAccount = findViewById(R.id.haveaccount);
        DB = new DBHelper(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String repass = confirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {
                    Toast.makeText(MainActivity.this, "Semua Field Wajib Diisi", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        boolean checkUser = DB.checkUsername(user);
                        if (!checkUser) {
                            boolean insert = DB.insertData(user, pass);
                            if (insert) {
                                Toast.makeText(MainActivity.this, "Registrasi Sukses", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Username sudah digunakan", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Password tidak sesuai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
