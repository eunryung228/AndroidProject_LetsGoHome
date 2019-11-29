package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainPage extends AppCompatActivity
{
    String name;
    int pw;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
        Gson gson=new Gson();

        name=pref.getString("name", null);
        pw=pref.getInt("password", 0);

        TextView textHi=(TextView) findViewById(R.id.textHi);
        textHi.setText("안녕하세요 "+name+"님!");

        String myst=pref.getString("myStation", null);
        Type type=new TypeToken<ArrayList<String>>(){}.getType();

        ArrayList<String> myList=gson.fromJson(myst, type);

        Log.d("확인", Integer.toString(pw));
        Log.d("확인", name);

        for(int i=0; i<myList.size(); i++)
        {
            Log.d("확인", myList.get(i));
        }
    }
}
