package com.example.compprijava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class razlog2 extends AppCompatActivity {


    ImageView pauza;
    ImageView poslovanRazlog;
    ImageView krajRadnogVremena;
    Button exit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_razlog2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pauza=findViewById(R.id.imageView1);
        poslovanRazlog=findViewById(R.id.imageView2);
        krajRadnogVremena=findViewById(R.id.imageView3);
        exit = findViewById(R.id.exit);
/*
        imageView.add(pauza);
        imageView.add(poslovanRazlog);
        imageView.add(krajRadnogVremena);

        slike.add(R.drawable.exit);
        slike.add(R.drawable.fork);
        slike.add(R.drawable.clock);
*/
        button();


    }
/*
    public void insertovanjeSlika() {
        for (int i = 0; i < imageView.size(); i++) {
            if (imageView.get(i) != null) {
                imageView.get(i).setImageResource(slike.get(i));
            }

        }
    }
*/
    public void button(){

       pauza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String razlog = "Dorucak";
                intent.putExtra("RAZLOG", razlog);
                setResult(RESULT_OK, intent);
                finish();
            }
        });



        poslovanRazlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String razlog = "Poslovan razlog";
                intent.putExtra("RAZLOG", razlog);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        krajRadnogVremena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String razlog = "Kraj radnog vremena";
                intent.putExtra("RAZLOG", razlog);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();

            }
        });



    }



}
