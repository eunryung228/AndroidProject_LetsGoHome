package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainPage extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
        Gson gson=new Gson();

        String myst=pref.getString("myStation", null);
        Type type=new TypeToken<ArrayList<String>>(){}.getType();

        ArrayList<String> myList=gson.fromJson(myst, type);
        String name=pref.getString("name", null);

        Log.d("확인", name);

        for(int i=0; i<myList.size(); i++)
        {
            Log.d("확인", myList.get(i));
        }
    }
}
