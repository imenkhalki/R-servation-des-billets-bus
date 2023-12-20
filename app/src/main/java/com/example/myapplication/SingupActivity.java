package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivitySingupBinding;

public class SingupActivity extends AppCompatActivity {

    ActivitySingupBinding binding;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivitySingupBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        dataBaseHelper = new DataBaseHelper( this );

        binding.signupbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = binding.nom.getText().toString();
                String prenom = binding.prenom.getText().toString();
                String num = binding.num.getText().toString();
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if (email.equals( "" ) || password.equals( "" ) || confirmPassword.equals( "" )) {
                    Toast.makeText( SingupActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT ).show();
                } else {
                    if (password.equals( confirmPassword )) {
                        boolean checkUserEmail = dataBaseHelper.checkemail( email );
                        if (!checkUserEmail) {
                            boolean insert = dataBaseHelper.insertData( nom, prenom, num, email, password );

                            if (insert) {
                                Toast.makeText( SingupActivity.this, "Inscription réussie", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( getApplicationContext(), loginActivity.class );
                                startActivity( intent );
                            } else {
                                Toast.makeText( SingupActivity.this, "Échec de l'inscription", Toast.LENGTH_SHORT ).show();
                            }
                        } else {
                            Toast.makeText( SingupActivity.this, "L'utilisateur existe déjà, veuillez vous connecter", Toast.LENGTH_SHORT ).show();
                        }
                    } else {
                        Toast.makeText( SingupActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT ).show();
                    }
                }
            }
        } );


    }
}