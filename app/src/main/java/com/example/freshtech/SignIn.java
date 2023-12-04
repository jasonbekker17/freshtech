package com.example.freshtech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    TextView btnsignup1,btnsignin1;
    EditText ETemail,ETpassword;


    DBHelper DB;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ETemail=(EditText)findViewById(R.id.ETemail);
        ETpassword=(EditText)findViewById(R.id.ETpassword);
        btnsignin1=(TextView)findViewById(R.id.btnsignin1);

        btnsignup1 = (TextView) findViewById(R.id.btnsignup1);
        DB = new DBHelper(this);


        btnsignin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = ETemail.getText().toString();
                String password = ETpassword.getText().toString();




                if(email.equals("")||password.equals(""))
                    Toast.makeText(SignIn.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkemailpassword = DB.checkemailpassword(email, password);
                    if(checkemailpassword == true){
                        Toast.makeText(SignIn.this, "Sign in sucessfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnsignup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });

    }

}