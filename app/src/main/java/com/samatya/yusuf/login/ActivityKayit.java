package com.samatya.yusuf.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityKayit extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        final EditText yeniKullanici = (EditText)findViewById(R.id.editText3);
        final EditText sifre = (EditText)findViewById(R.id.editText4);
        final EditText sifreTekrar = (EditText)findViewById(R.id.editText5);
        Button kayitBtn = (Button)findViewById(R.id.button3);
        TextView kayitList = (TextView)findViewById(R.id.textView3);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();



        kayitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = yeniKullanici.getText().toString();
                String s = sifre.getText().toString();
                String s2 = sifreTekrar.getText().toString();

                if(!s.equals(s2)) {
                    Toast.makeText(getApplicationContext(), "girilen şifreler eşleşmiyor!!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(a.equals("")||s.equals("")||s2.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Tekrar deneyiniz!!", Toast.LENGTH_LONG).show();
                    return;
                }else{

                    loginDataBaseAdapter.insertEntry(a, s);



                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i =new Intent(getBaseContext(),MainActivity.class);
                            startActivity(i);
                            //Do something after 100ms
                        }
                    }, 500);

                }
            }
        });



    }

}
