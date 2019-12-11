package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letsgohome.calendar.MyCalendar;
import com.example.letsgohome.ktx.KTXInformation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
        Gson gson=new Gson();

        String myst=pref.getString("myStation", null);
        String homest=pref.getString("hometownStation", null);
        Type type=new TypeToken<ArrayList<String>>(){}.getType();

        ArrayList<String> myList=gson.fromJson(myst, type);
        ArrayList<String> homeList=gson.fromJson(homest, type);

        for (int i=0; i<myList.size(); i++)
        {
            Log.d("check", myList.get(i));
        }
        for (int i=0; i<homeList.size(); i++)
        {
            Log.d("check", homeList.get(i));
        }

        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Date> days=new ArrayList<>();

        DBHelper dbHelper=new DBHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT DATE FROM CALENDAR ORDER BY DATE", null);

        try
        {
            Date date;
            while (cursor.moveToNext())
            {
                date=df.parse(cursor.getString(0));
                days.add(date);
                Log.d("db", cursor.getString(0));
            }
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        db.close();
        Log.d("db", "--------------------------");


        Date today=new Date();
        if(days.size()>=1)
        {
            boolean isFindToday=false;
            Date befDay=new Date();
            befDay.setYear(-1000);// 오늘의 before 중 가장 큰 날짜 저장 (최근에 집 다녀온 날짜)
            // 초기화를 1000년 전으로 한 이유는 new Date()는 오늘 날짜라서 befDay가 저장이 안 되기 때문에
            Date aftDay=new Date(); // 오늘의 after 중 가장 작은 날짜 저장 (최근에 집 갈 날짜)
            aftDay.setYear(1000);
            // 초기화를 1000년 후로 한 이유는 new Date()는 오늘 날짜라서 befDay가 저장이 안 되기 때문에

            for (int i=0; i<days.size(); i++)
            {
                if(df.format(today).equals(df.format(days.get(i))))
                    isFindToday=true;
                else if(today.after(days.get(i)))
                {
                    if(befDay.before(days.get(i)))
                        befDay=days.get(i);
                }
                else if(today.before(days.get(i)))
                {
                    if(aftDay.after(days.get(i)))
                        aftDay=days.get(i);
                }
            }

            if(isFindToday)
            {
                textMeet.setText("· 오늘 가족을 만나러 가요!");

                if(df.format(aftDay).contains("2900"))
                    textNextMeet.setText("· 다음 일정은 아직 정하지 않았어요!");
                else
                {
                    long diff=aftDay.getTime()-today.getTime();
                    long aftMeet=diff/(24*60*60*1000)+1;
                    textNextMeet.setText("· 다음 가족을 만날 날까지 "+String.valueOf(aftMeet)+"일");
                }
            }
            else
            {
                if(df.format(befDay).contains("900")) // 초기 값이라면
                    textMeet.setText("· 가족을 마지막으로 만난 때가 언제인가요?");
                else
                {
                    long diff=today.getTime()-befDay.getTime();
                    long lastMeet=diff/(24*60*60*1000);
                    textMeet.setText("· 가족을 못 만난 지 어느덧 "+String.valueOf(lastMeet)+"일");
                }

                if(df.format(aftDay).contains("2900"))
                    textNextMeet.setText("· 아직 가족을 만날 계획이 없어요.. \uD83D\uDE22");
                else
                {
                    long diff=aftDay.getTime()-today.getTime();
                    long aftMeet=diff/(24*60*60*1000)+1;
                    textNextMeet.setText("· 다음 가족을 만날 날까지 "+String.valueOf(aftMeet)+"일");
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(Menu.NONE, 1, Menu.NONE, "역 정보 수정");
        menu.add(Menu.NONE, 2, Menu.NONE, "회원 정보 수정");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case 1:
                Intent intent=new Intent(getApplicationContext(), StationOption.class);
                startActivityForResult(intent, 2000);
                return true;
            case 2:
                Toast.makeText(this, "회원 정보 수정 추가할 것~", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mOnClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCal:
                Intent intent1=new Intent(getApplicationContext(), MyCalendar.class);
                startActivityForResult(intent1, 1001); // calendar: 1001
                break;
            case R.id.btnKtx:
                Intent intent2=new Intent(getApplicationContext(), KTXInformation.class);
                startActivityForResult(intent2, 1002); // ktx: 1002
                break;
        }
    }
}
