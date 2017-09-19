package com.samatya.yusuf.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityLoginned extends AppCompatActivity {

    ListView lv;
    ArrayAdapter adapter;
    ArrayList<HashMap<String, String>> not_list;
    String not_lar[];
    String not_icerik[];
    int notID[];
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginned);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(),ActivityNewNote.class);
                startActivity(i);

            }
        });



        LoginDataBaseAdapter2 loginDataBaseAdapter2 = new LoginDataBaseAdapter2(this);
        loginDataBaseAdapter2=loginDataBaseAdapter2.open();

        not_list = loginDataBaseAdapter2.notListele();
        not_lar = new String[not_list.size()];
        not_icerik = new String[not_list.size()];
        notID = new int[not_list.size()];
        for(int i=0;i<not_list.size();i++){
            not_lar[i] = not_list.get(i).get("TITLE");
            //kitap_liste.get(0) bize arraylist içindeki ilk hashmap arrayini döner. Yani tablomuzdaki ilk satır değerlerini
            //kitap_liste.get(0).get("kitap_adi") //bize arraylist içindeki ilk hashmap arrayin anahtarı kitap_adi olan value döner
            not_icerik[i]=not_list.get(i).get("NOTE");
            notID[i] = Integer.parseInt(not_list.get(i).get("ID"));
            //Yukarıdaki ile aynı tek farkı değerleri integer a çevirdik.
        }

        lv = (ListView)findViewById(R.id.listView);

        adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,not_lar);
        lv.setAdapter(adapter);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int id,
                                    long arg3) {


                builder.setTitle(not_lar[id]).setMessage(not_icerik[id]).show();


            }
        });



    }

}
