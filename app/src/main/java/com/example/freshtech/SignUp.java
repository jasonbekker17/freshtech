package com.example.freshtech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText fullname, email, password, repassword;
    TextView btnsignup ,btnsignin;
    DBHelper DB;
    final String secretKey="donottouch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullname=(EditText) findViewById(R.id.etfname);
        email=(EditText) findViewById(R.id.ETemail);
        password=(EditText) findViewById(R.id.ETpassword);
        repassword=(EditText) findViewById(R.id.etrepassword);
        btnsignup=(TextView) findViewById(R.id.btnsignup);
        btnsignin=(TextView) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);


        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignIn.class);
                startActivity(intent);
                finish();
            }
        });



        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 =email.getText().toString();
                String fullname1=fullname.getText().toString();
                String pass1=password.getText().toString();
                String repass1=repassword.getText().toString();
;



                if(email1.equals("")||pass1.equals("")||fullname1.equals("")||repass1.equals(""))
                    Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                else{
                    if(pass1.equals(repass1)){

                        Boolean checkemail = DB.checkemail(email1);
                        if(checkemail == false){
                            Boolean insert = DB.insertData(email1,fullname1,pass1);
                            if(insert == true){
                                Toast.makeText(SignUp.this, "Registered sucessfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUp.this, "User Already exists please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUp.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }



}