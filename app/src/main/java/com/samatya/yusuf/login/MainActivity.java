package com.samatya.yusuf.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        final EditText kullaniciAdi = (EditText)findViewById(R.id.editText);
        final EditText sifre = (EditText)findViewById(R.id.editText2);
        Button butonGiris = (Button)findViewById(R.id.button);
        Button butonKayit = (Button)findViewById(R.id.button2);

        butonKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getBaseContext(),ActivityKayit.class);
                startActivity(i);
            }
        });


       loginDataBaseAdapter = new LoginDataBaseAdapter(this);
       loginDataBaseAdapter=loginDataBaseAdapter.open();


       butonGiris.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

            String userName = kullaniciAdi.getText().toString();
            String userPass = sifre.getText().toString();
            String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

            if (storedPassword.equals(userPass)){

                Intent i = new Intent(getBaseContext(),ActivityLoginned.class);
                startActivity(i);
            }


           }
       });





    }
}
