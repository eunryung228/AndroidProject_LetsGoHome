package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainPage extends AppCompatActivity
{
    String name;
    int pw;

    TextView textMeet;
    TextView textNextMeet;
    TextView textCall;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        textMeet=(TextView) findViewById(R.id.textMeet);
        textNextMeet=(TextView) findViewById(R.id.textNextMeet);
        textCall=(TextView) findViewById(R.id.textCall);

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

    @Override
    protected void onResume()
    {
        super.onResume();

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        ArrayList<Date> days=new ArrayList<>();

        DBHelper dbHelper=new DBHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT DATE FROM CALENDAR ORDER BY DATE", null);

        try
        {
            Date date;
            while (cursor.moveToNext())
            {
                date=dateFormat.parse(cursor.getString(0));
                days.add(date);
                Log.d("db", cursor.getString(0));
            }
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        db.close();

        /*
        for (int i=0; i<days.size(); i++)
        {
            Log.d("db", days.get(i).toString());
        }
         /*
            1. 각 날짜를 오늘과 비교함
            2. 오늘의 before()들 중 가장 큰 날짜를 찾음
            3. 오늘의 after()들 중 가장 작은 날짜를 찾음
            4. date 중 오늘이 있다면 다음으로 가족을 만날 날이 D-day라는 것을 명시, after은 무시해도 됨
            ...
        Date today=new Date();
        for (int i=0; i<=days.size(); i++)
        {
            if(today.before())
        }
        Date date2= dateFormat.parse(second);

        long lastMeet=date1.getTime()-date2

        long diff=date1.getTime()-date2.getTime();
        long diffDays=diff/(24*60*60*1000);
        Log.d("db", String.valueOf(diffDays));
            */

        //textMeet.setText("· 가족을 못 만난 지 어느덧 "+30일");
        // textNextMeet.setText("· 다음 가족을 만날 날까지 30일");

    }

    public void mOnClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCal:
                Intent intent=new Intent(getApplicationContext(), Calendar.class);
                startActivityForResult(intent, 1001); // calendar: 1001
                break;
            case R.id.btnKtx:
                DBHelper dbHelper=new DBHelper(this);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor=db.rawQuery("SELECT DATE FROM CALENDAR ORDER BY DATE", null);

                while (cursor.moveToNext())
                {
                    Log.d("db", cursor.getString(0));
                }
                db.close();
        }
    }
}
