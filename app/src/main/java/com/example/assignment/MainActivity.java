package com.example.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText height;
    EditText weight;
    Button button,reset;
    TextView resultbmi;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.log);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);

        button = (Button) findViewById(R.id.button);
        resultbmi = (TextView) findViewById(R.id.resultbmi);
        reset = (Button) findViewById(R.id.reset);

        sharedPref = this.getSharedPreferences("height", Context.MODE_PRIVATE);
        sharedPref = this.getSharedPreferences("weight", Context.MODE_PRIVATE);

        float height1 = sharedPref.getFloat("height", 0);
        height.setText(String.valueOf(height1));

        float weight1 = sharedPref.getFloat("weight", 0);
        weight.setText(String.valueOf(weight1));

        button.setOnClickListener(this);
        reset.setOnClickListener(this);


    }
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super .onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(getApplicationContext(),Profile.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:

                try {

                    String S1 = weight.getText().toString();
                    String S2 = height.getText().toString();

                    float weightValue = Float.parseFloat(S1);
                    float heightValue = Float.parseFloat(S2) ;
                    float heightcm = heightValue / 100 ;

                    String BMIresult,calculation;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putFloat("height", heightValue);
                    editor.putFloat("weight", weightValue);
                    editor.apply();

                    float bmi = weightValue / (heightcm * heightcm);
                    String Risk;

                    if(bmi < 18.4){
                        BMIresult = "Underweight";
                        Risk  = "Malnutrition Risk";

                    }else if(bmi >= 18.5 && bmi <= 24.99){
                        BMIresult = "Normal Weight";
                        Risk  = "Low Risk";

                    }else if (bmi >= 25 && bmi <= 29.99){
                        BMIresult = "Overweight";
                        Risk  = "Enhanced Risk";

                    }else if (bmi >= 30 && bmi <= 34.99){
                        BMIresult = "Moderately Obese";
                        Risk  = "Medium Risk";

                    }else if (bmi >= 35 && bmi <= 39.99){
                        BMIresult = "Severely Obese";
                        Risk  = "High Risk";
                    }

                   else if(bmi >= 40 && bmi <= 100.0){
                        BMIresult = "Very Severely Obese";
                        Risk  = "Very High Risk";

                    }
                   else{
                        BMIresult = "No result found";
                        Risk  = "No result found";

                    }
                    calculation = "Your Result : " + String.format("%.2f", bmi) + "\n"+ "\n" + "You are : " +
                            BMIresult + "\n" + "\n" + "Health Risk : " + Risk;

                    resultbmi.setText(calculation);


                }catch (Exception ex){

                    LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                    final View views = factory.inflate(R.layout.alertdialog,null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setView(views);

                    alertDialogBuilder.setNegativeButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    Toast.makeText(this,"Please insert height and weight",Toast.LENGTH_SHORT).show();
                }
            break;

            case R.id.reset:
                reset();

            default:
        }

    }
    private void reset() {

        int reset = 0;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("height", reset);
        editor.putFloat("weight", reset);
        editor.apply();

        height.setText("");
        weight.setText("");

    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}