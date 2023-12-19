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
        binding=ActivitySingupBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot());
        dataBaseHelper =new DataBaseHelper(  this );
        binding.signupbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String nom=binding.nom.getText().toString();
                 String prenom=binding.prenom.getText().toString();
                 Integer num= binding.num.getText().length();
                String email=binding.signupEmail.getText().toString();
                String password=binding.signupPassword.getText().toString();
                String ConfirmPassword=binding.signupConfirm.getText().toString();
 if (email.equals("") || password.equals("")|| ConfirmPassword.equals(""))
     Toast.makeText( SingupActivity.this, "", Toast.LENGTH_SHORT ).show();
 else{
     if (password.equals(ConfirmPassword)   ){
         boolean checkUserEmail=dataBaseHelper.checkemail( email );
         if (checkUserEmail==false){
             boolean insert = dataBaseHelper.insertData(nom,prenom,num,email,password);

             if (insert==true){
                 Toast.makeText( SingupActivity.this, "signup Successfully", Toast.LENGTH_SHORT ).show();
            Intent intent = new Intent(getApplicationContext(),loginActivity.class);
              startActivity( intent );
              

             }else{
                 Toast.makeText( SingupActivity.this, "signup Faild", Toast.LENGTH_SHORT ).show();
             }

         }else {
             Toast.makeText( SingupActivity.this, "User already exists , please login", Toast.LENGTH_SHORT ).show();
         }
     } else {
         Toast.makeText( SingupActivity.this, "Invalid Password", Toast.LENGTH_SHORT ).show();
     }
 }
            }



        } );

        binding.signupbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),loginActivity.class);
                startActivity( intent );



            }
        } );


    }
}