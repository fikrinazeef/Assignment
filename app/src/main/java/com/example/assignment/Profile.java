package com.example.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    TextView farid,fikri,uitm,copy;
    Button sourcecode;
    ImageView yed,sam;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        farid = (TextView) findViewById(R.id.farid);
        fikri = (TextView) findViewById(R.id.fikri);
        uitm = (TextView) findViewById(R.id.uitm);
        copy = (TextView) findViewById(R.id.copy);
        yed = (ImageView) findViewById(R.id.yed);
        sam = (ImageView) findViewById(R.id.sam);

        dialog= new Dialog(this);

        sourcecode = (Button) findViewById(R.id.sourcecode);

        sourcecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/fikrinazeef/Assignment"));
                startActivity(intent);
            }
        });
        yed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileyed();
            }
        });
        sam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilesam();
            }
        });
    }
    private void profilesam(){
        dialog.setContentView(R.layout.alertprofilefikri);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView= dialog.findViewById(R.id.sam);
        Button ok=dialog.findViewById(R.id.okay);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
          //      Toast.makeText(Profile.this,"Thank You",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
    private void profileyed(){
        dialog.setContentView(R.layout.alertprofilefarid);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView= dialog.findViewById(R.id.sam);
        Button ok=dialog.findViewById(R.id.okayed);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //      Toast.makeText(Profile.this,"Thank You",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bmi:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}