package com.travelcrew.wego;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {

    EditText emailId, pwd;
    Button signbtn;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_login_screen);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("users");

        emailId = (EditText) findViewById(R.id.EmailField);
        pwd = (EditText) findViewById(R.id.PasswordField);

        signbtn = (Button) findViewById(R.id.SignButton);

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(emailId.getText().toString(), pwd.getText().toString());
            }
        });

    }

    private void signIn(final String email, final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(email).exists()) {
                    if (email.isEmpty()) {
                        Users login = dataSnapshot.child(email).getValue(Users.class);
                        assert login != null;
                        if (login.getPassword().equals(password)) {
                            Toast.makeText(LoginScreen.this, "Success login", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginScreen.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginScreen.this, "Email is not registered", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void onClick(View view){
        Intent intent=null;
        intent = new Intent(this,SignUpScreen.class);
        startActivity(intent);
    }
}
