package com.example.maria.mealcostcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float mealCostTotal = 0;
    float tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText mealCostAux = (EditText) findViewById(R.id.mealCost);
        final EditText taxAux = (EditText) findViewById(R.id.tax);
        final EditText tipAux = (EditText) findViewById(R.id.tip);
        final TextView total = (TextView) findViewById(R.id.total);
        findViewById(R.id.calculate).setOnClickListener(new View.OnClickListener() {

            // Logic for validation, input can't be empty
            @Override
            public void onClick(View v) {

                String str1 = mealCostAux.getText().toString();
                String str2 = taxAux.getText().toString();
                String str3 = tipAux.getText().toString();

                if(str1 == null || str1.length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter the meal cost in dollars", Toast.LENGTH_LONG).show();
                    return;
                }

                if(str2 == null || str2.length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter a tax percentage", Toast.LENGTH_LONG).show();
                    return;
                }

                if(str3 == null || str3.length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter a tip percentage", Toast.LENGTH_LONG).show();
                    return;
                }

                //Get the user values from the widget reference
                float mealCost = Float.parseFloat(str1);
                float tax = Float.parseFloat(str2);
                float tip = Float.parseFloat(str3);

                //Calculate MealCostTotal
                mealCostTotal = calculateMealCost(mealCost, tax, tip);
                total.setText(String.valueOf(mealCostTotal));
                return;

            }
        });
    }

    //Calculate MealCost
    private float calculateMealCost (float mealCost, float tax, float tip) {
        float costWithTax = (float) (mealCost*(1+(tax/100)));
        return Float.parseFloat(String.format("%.2f", costWithTax*(1+(tip/100))));
    }

}