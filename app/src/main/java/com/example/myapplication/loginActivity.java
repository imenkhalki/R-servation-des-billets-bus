package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityLoginBinding;

public class loginActivity extends AppCompatActivity {
 ActivityLoginBinding binding;
 DataBaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper=new DataBaseHelper(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= binding.loginEmail.getText().toString();
                String password= binding.loginPassword.getText().toString();


                if (email.equals("")|| password.equals( "" ))
                    Toast.makeText( loginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT ).show();
                else {
                    boolean checkCredentials = databaseHelper.checkEmailPassword( email,password );
                    if (checkCredentials){
                        Toast.makeText( loginActivity.this, "Login Successfully", Toast.LENGTH_SHORT ).show();
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity( intent );

                    }else{
                        Toast.makeText( loginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT ).show();
                    }
                }



            }
        } );
        binding.loginRedirectext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginActivity.this, SingupActivity.class);
                startActivity(intent);


            }
        } );

        }
}