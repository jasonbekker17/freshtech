package com.example.freshtech;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    DBHelper DB;

    EditText meal_input, ingredients_input, price_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        meal_input=(EditText) findViewById(R.id.meal_input);
        ingredients_input=(EditText) findViewById(R.id.ingredients_input);
        price_input=(EditText) findViewById(R.id.price_input);
        add_button=(Button) findViewById(R.id.add_button);
        DB = new DBHelper(this);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String meal =meal_input.getText().toString();
                String ingredients =ingredients_input.getText().toString();
                String price =price_input.getText().toString();




                if(meal.equals("")||ingredients.equals("")||price.equals(""))
                    Toast.makeText(AddActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                else{

                    if(meal.equals(meal)){

                        Boolean checkemeal = DB.checkemeal(meal);
                        if(checkemeal == false){
                            Boolean insert = DB.insertMeal(meal,ingredients,price);
                            if(insert == true){
                                Toast.makeText(AddActivity.this, "Meal Added sucessfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(AddActivity.this, "Adding mealfailed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(AddActivity.this, "Meal already exist please add new ingredients", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AddActivity.this, "Unknown meal", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}
