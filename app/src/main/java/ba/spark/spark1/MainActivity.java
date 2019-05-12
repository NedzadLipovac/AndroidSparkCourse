package ba.spark.spark1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TEST_USERNAME = "nedzad";
    private static final String TEST_PASSWORD = "nedzad12";
    public static final String MY_Preferences_Filename="package ba.spark.spark1.sharedpreferences";
    private EditText korIme;
    private EditText lozinka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout myLayout = (LinearLayout) this.findViewById(R.id.activity_main);
        myLayout.requestFocus();

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        SharedPreferences preferences=getSharedPreferences(MY_Preferences_Filename,MODE_PRIVATE);

        if (preferences.getBoolean("shouldRemember", false)) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        korIme = (EditText) findViewById(R.id.et_kor_ime);
        lozinka = (EditText) findViewById(R.id.et_lozinka);

        final CheckBox zapamtiMe = (CheckBox) findViewById(R.id.cb_zapamti_me);

        Button prijava = (Button) findViewById(R.id.btn_prijava);


//        korIme.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                Matcher matcher = Pattern.compile("[a-zA-Z0-9]+").matcher(korIme.getText().toString());
//
//                if (!matcher.matches()|| korIme.length()<3) {
//                    korIme.setError("error");
//                    Toast.makeText(MainActivity.this, "Korisnicko ime nije validno ", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        lozinka.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (lozinka.length() == 0) {
//                    lozinka.setError("error");
//                    Toast.makeText(MainActivity.this, "Unesite lozinku", Toast.LENGTH_LONG).show();
//
//
//                }
//            }
//        });
        prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (korIme.getText().toString().equals(MainActivity.TEST_USERNAME)
                        && lozinka.getText().toString().equals(MainActivity.TEST_PASSWORD)) {

                    Toast.makeText(getApplicationContext(), "Uspješna prijava.", Toast.LENGTH_SHORT)
                            .show();

                    //***********************************************************************************
                    SharedPreferences.Editor editor1=getSharedPreferences(MY_Preferences_Filename,MODE_PRIVATE).edit();
                    editor1.putString("username", korIme.getText().toString());
                    editor1.putString("password", lozinka.getText().toString());
                    editor1.putBoolean("shouldRemember", zapamtiMe.isChecked());
                    editor1.commit();


                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);

                }
                else {
                    Matcher matcher = Pattern.compile("[a-zA-Z0-9]+").matcher(korIme.getText().toString());
                       boolean name=false;
                       boolean pass=false;
                    if (!matcher.matches()|| korIme.length()<3) {
                        korIme.setError("error");
                     //   Toast.makeText(MainActivity.this, "Korisnicko ime nije validno ", Toast.LENGTH_LONG).show();
                             name=true;
                    }
                    if (lozinka.length() == 0) {
                        lozinka.setError("error");
                       // Toast.makeText(MainActivity.this, "Unesite lozinku", Toast.LENGTH_LONG).show();
                        pass=true;
                    }

                    if(pass && name)
                        Toast.makeText(MainActivity.this, "Korisnicko ime i lozinka nisu validni", Toast.LENGTH_LONG).show();
                     else if(pass && !name)
                        Toast.makeText(MainActivity.this, "Lozinka nije validna", Toast.LENGTH_LONG).show();
                     else if(!pass&&name)
                        Toast.makeText(MainActivity.this, "Korisnicko ime nije validno", Toast.LENGTH_LONG).show();
                    else if(!pass&&!name)
                    Toast.makeText(getApplicationContext(), "Neuspješna prijava.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}
