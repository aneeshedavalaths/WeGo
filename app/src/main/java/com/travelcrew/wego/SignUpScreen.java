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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class SignUpScreen extends AppCompatActivity {

    EditText name_field,emailId,pwd,mobileNum;
    Button signIn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_sign_up_screen);

        name_field=findViewById(R.id.NameField);
        emailId=findViewById(R.id.EmailField);
        pwd=findViewById(R.id.PasswordField);
        mobileNum=findViewById(R.id.MobileField);
        signIn=findViewById(R.id.SignButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String name = name_field.getText().toString();
                String email = emailId.getText().toString();
                String password = pwd.getText().toString();
                String mobileNo = mobileNum.getText().toString();

                Users user_class = new Users(name,email,password,mobileNo);

                if (password.isEmpty())
                {
                    pwd.setError("Please enter password");
                }
                else if (email.isEmpty()) {
                    emailId.setError("Please enter your email address");
                }
                else if (name.isEmpty()) {
                    name_field.setError("Please enter your name");
                }
                else if (mobileNo.isEmpty()) {
                    mobileNum.setError("Please enter your mobile number");
                }
                else if (password.length() < 8) {
                    pwd.setError("Password must be atleast 8 characters");
                }
                else if (mobileNo.length() < 10) {
                    mobileNum.setError("Enter a valid mobile number");
                }
                else {
                    reference.child(mobileNo).setValue(user_class);
                    Toast.makeText(SignUpScreen.this, "Account created", Toast.LENGTH_SHORT).show();

                    Intent loginIntent = new Intent(getApplicationContext(), LoginScreen.class);
                    startActivity(loginIntent);
                }

            }
        }) ;

    }
}

