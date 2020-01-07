package com.example.compprijava;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.muddzdev.styleabletoast.StyleableToast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> iDtoken = new ArrayList<>();

    SQLhelper help;

    Button test;
    Button sub;
    Button odjava;
    Button backSp;
    Button dodaj;

    EditText editText;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        help = new SQLhelper(this);
        test = findViewById(R.id.test);
        sub = findViewById(R.id.prijava);
        editText = findViewById(R.id.text1);
        odjava = findViewById(R.id.odjava);
        backSp = findViewById(R.id.buttonBackSpace);
        dodaj = findViewById(R.id.dodaj);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        brojevi();
        buttonTest();
        submitButton();
        colletingRazlog();
        backSpace();
        dadajUsera();

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    //insertovanje brojeva

    public void brojevi() {
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("0");
                editText.append("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("1");
                editText.append("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("2");
                editText.append("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("3");
                editText.append("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("4");
                editText.append("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("5");
                editText.append("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("6");
                editText.append("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("7");
                editText.append("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("8");
                editText.append("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("9");
                editText.append("9");
            }
        });
    }

    //brisanje texta iz text view
    public void buttonTest() {
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                list.clear();


            }
        });

    }

    public void backSpace() {
        backSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                int a = list.size();
                if (list.size() == 0) {
                } else {
                    list.remove(a - 1);
                    for (int i = 0; i < list.size(); i++) {
                        editText.append(list.get(i));
                    }
                }
            }
        });
    }

    //privremena metoda
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    //metoda za prijavu
    public void submit(String broj) {
        String id = "";
        String ime = "";
        String prezime = "";
        String token = "";

        Cursor res = help.takeDataToken(broj);
        Calendar cal = Calendar.getInstance();

        String date = DateFormat.getDateInstance().format(cal.getTime());
        String time = DateFormat.getTimeInstance().format(cal.getTime());

        while (res.moveToNext()) {

            id = res.getString(0);
            ime = res.getString(1);
            prezime = res.getString(2);
            token = res.getString(3);
        }

        if (token.contentEquals(broj)) {

            help.insertDataPrijava(id, ime, prezime, date, time);

           StyleableToast.makeText(this, "Uspjesno ste se prijavili"+ ","+" "+ime, R.style.toastOK).show();

            editText.getText().clear();
            list.clear();

            //showMessage("Uspjesno", "Prijavili ste se");

        } else
            StyleableToast.makeText(this, "ID broj ne postoji", R.style.toastError).show();
        editText.setText("");
        list.clear();


        //showMessage("Error", "Neispravan kod");
    }

    public void submitButton() {
        sub.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String broj = editText.getText().toString();
                        if (broj.isEmpty())

                            StyleableToast.makeText(MainActivity.this, "Prazan unos", R.style.toastError).show();

                        else
                            submit(broj);

                    }
                }
        );
    }

    //metoda za unosenje podataka kad se user odjavljuje
    public void odjava(String broj, String razlog) {

        String id = "";
        String ime = "";
        String prezime = "";
        String token = "";

        Calendar cal = Calendar.getInstance();
        String datum = DateFormat.getDateInstance().format(cal.getTime());
        String vrijeme = DateFormat.getTimeInstance().format(cal.getTime());

        Cursor cr = help.takeDataToken(broj);

        while (cr.moveToNext()) {

            id = cr.getString(0);
            ime = cr.getString(1);
            prezime = cr.getString(2);
            token = cr.getString(3);
        }

        if (broj.equals(token)) {

            help.insertDataOdjava(id, ime, prezime, datum, vrijeme, razlog);
            StyleableToast.makeText(this, "Uspjesno ste se odjavili"+","+" "+ime, R.style.toastOK).show();
            editText.getText().clear();
            list.clear();
            //showMessage("Uspjesno", "Odjavili ste se");
        } else
            StyleableToast.makeText(this,"ID broj ne postoji",R.style.toastError).show();
        editText.getText().clear();
        list.clear();

    }

    public void colletingRazlog() {
        odjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String broj = editText.getText().toString();
                if (broj.isEmpty() ) {
                    StyleableToast.makeText(MainActivity.this, "Prazan unos", R.style.toastError).show();
                }
                else if (tokenCompare(broj)){
                    Intent intent = new Intent(MainActivity.this, razlog2.class);
                    startActivityForResult(intent, 1);
                }

                else{

                    StyleableToast.makeText(MainActivity.this, "ID broj ne postoji", R.style.toastError).show();
                    editText.getText().clear();
                    list.clear();
                    iDtoken.clear();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            String razlog = data.getStringExtra("RAZLOG");
            String broj = editText.getText().toString();
            odjava(broj, razlog);

        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            String ime = data.getStringExtra("IME");
            String prezime = data.getStringExtra("PREZIME");
            String token = data.getStringExtra("TOKEN");
            if(tokenCompare(token)){
                StyleableToast.makeText(this, "ID broj vec postoji", R.style.toastError).show();
                editText.getText().clear();
                list.clear();
                iDtoken.clear();

            }
            else {

                help.insertDataKorisnici(ime, prezime, token);
                StyleableToast.makeText(this, "Uspjesno ste dodali korisnika",  R.style.toastOK).show();
                editText.getText().clear();
                list.clear();
                iDtoken.clear();
            }
        }
        else if(resultCode==RESULT_CANCELED){
            StyleableToast.makeText(this,"Ponisteno", R.style.toastError).show();
            editText.getText().clear();
            list.clear();

        }

    }

    public void dadajUsera() {
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String broj = editText.getText().toString();
                if(broj.equals("1111")){
                    Intent intent = new Intent(MainActivity.this, DodajUsera.class);
                    startActivityForResult(intent, 2);
                }
                else if(broj.equals(""))
                    StyleableToast.makeText(MainActivity.this, "Prazan unos", R.style.toastError).show();
                else
                    StyleableToast.makeText(MainActivity.this, "ID broj nije ispravan", R.style.toastError).show();


            }
        });

    }

    public Boolean tokenCompare(String broj) {

        Cursor res = help.compareToken();

        while (res.moveToNext()) {
            int i = 0;
            iDtoken.add(res.getString(i));
            i++;

        }

        for (int i = 0; i < iDtoken.size(); i++) {
            iDtoken.get(i);
        }
        if (iDtoken.contains(broj)) {
            return true;
        } else
            return false;

    }


}

