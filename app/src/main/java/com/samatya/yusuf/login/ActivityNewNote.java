package com.samatya.yusuf.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityNewNote extends AppCompatActivity {

    LoginDataBaseAdapter2 loginDataBaseAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        final EditText title = (EditText)findViewById(R.id.editText6);
        final EditText note = (EditText)findViewById(R.id.editText7);
        Button kaydet = (Button)findViewById(R.id.button4);

        loginDataBaseAdapter2 = new LoginDataBaseAdapter2(this);
        loginDataBaseAdapter2=loginDataBaseAdapter2.open();


        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String baslik = title.getText().toString();
                String not = note.getText().toString();


                if(baslik.equals("") || not.equals("")){
                    Toast.makeText(getApplicationContext(),"Başlık veya not boş olamaz !",Toast.LENGTH_LONG).show();
                }else{
                loginDataBaseAdapter2.insertEntry(baslik,not);

                Intent i = new Intent(getBaseContext(),ActivityLoginned.class);
                startActivity(i);
                }
            }
        });



    }



}
