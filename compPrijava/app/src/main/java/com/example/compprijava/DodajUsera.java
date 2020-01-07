package com.example.compprijava;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

public class DodajUsera extends AppCompatActivity {

    Button button1;
    Button button2;

    EditText editText1;
    EditText editText2;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_usera);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button1 = findViewById(R.id.potvrdi);
        button2 = findViewById(R.id.buttonPonisti);

        editText1 = findViewById(R.id.textViewID);
        editText2 = findViewById(R.id.textViewIme);
        editText3 = findViewById(R.id.textViewPrezime);

        potvrdi();
        ponisti();


    }

    public void potvrdi() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertKorisnici();

            }
        });
    }

    public void insertKorisnici(){
            Intent  intent = new Intent();
            String token = editText1.getText().toString();
            String ime = editText2.getText().toString();
            String prezime = editText3.getText().toString();

            intent.putExtra("TOKEN", token);
            intent.putExtra("IME",ime);
            intent.putExtra("PREZIME", prezime);
            setResult(RESULT_OK,intent);

        finish();

        }
        public void ponisti(){
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResult(RESULT_CANCELED);
                    finish();
                }
            });
        }

    }


