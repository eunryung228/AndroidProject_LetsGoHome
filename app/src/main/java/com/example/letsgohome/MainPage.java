package com.example.letsgohome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
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

import static android.Manifest.permission.READ_CALL_LOG;

public class MainPage extends AppCompatActivity
{
    String name;
    int pw;
    int imageNum;
    boolean useCall=false;

    ArrayList<String> phoneList=new ArrayList<>();
    ArrayList<String> nameList=new ArrayList<>();

    TextView textHi;
    TextView textMeet;
    TextView textNextMeet;
    TextView textCall;
    ImageButton btnOption;

    public static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btnOption=(ImageButton) findViewById(R.id.btnOption);
        textMeet=(TextView) findViewById(R.id.textMeet);
        textNextMeet=(TextView) findViewById(R.id.textNextMeet);
        textCall=(TextView) findViewById(R.id.textCall);

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);

        name=pref.getString("name", null);
        pw=pref.getInt("password", 0);
        imageNum=pref.getInt("image", 0);

        textHi=(TextView) findViewById(R.id.textHi);
        textHi.setText("안녕하세요 "+name+"님!");
        setImage(imageNum);

        mContext=this;
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<String>>(){}.getType();

        name=pref.getString("name", null);
        pw=pref.getInt("password", 0);
        imageNum=pref.getInt("image", 0);
        useCall=pref.getBoolean("useCall", false);

        if(useCall)
        {
            String callPhones=pref.getString("callPhoneList", null);
            String callNames=pref.getString("callNameList", null);
            nameList=gson.fromJson(callNames, type);
            phoneList=gson.fromJson(callPhones, type);

            textCall.setVisibility(View.VISIBLE);
            Date lastDay=loadCallLog(phoneList.get(0), phoneList.get(1));

            Date today=new Date();
            long diff=today.getTime()-lastDay.getTime();
            long lastCall=diff/(24*60*60*1000)+1;
            textCall.setText("· 전화를 못 한 지 어느덧 "+String.valueOf(lastCall)+"일");
        }
        else
            textCall.setVisibility(View.INVISIBLE);

        textHi.setText("안녕하세요 "+name+"님!");
        setImage(imageNum);

        String myst=pref.getString("myStation", null);
        String homest=pref.getString("hometownStation", null);

        ArrayList<String> myList=gson.fromJson(myst, type);
        ArrayList<String> homeList=gson.fromJson(homest, type);

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

    public void optionMenuClick(View v) {
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
        getMenuInflater().inflate(R.menu.option_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.menu1:
                        Intent intent = new Intent(getApplicationContext(), StationOption.class);
                        startActivity(intent);
                        return true;
                    case R.id.menu2:
                        Intent intCall = new Intent(getApplicationContext(), InfoOption.class);
                        startActivity(intCall);
                        return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }


    public void mOnClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCal:
                Intent intent1=new Intent(getApplicationContext(), MyCalendar.class);
                startActivity(intent1);
                break;
            case R.id.btnKtx:
                Intent intent2=new Intent(getApplicationContext(), KTXInformation.class);
                startActivity(intent2);
                break;
            case R.id.btnCall:
                if(useCall)
                {
                    final CharSequence[] nameItems={nameList.get(0), nameList.get(1)};
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("누구에게 전화를 하실건가요?");
                    builder.setItems(nameItems, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneList.get(which)));
                            startActivity(intent);
                        }
                    });
                    AlertDialog callDialog=builder.create();
                    callDialog.show();
                }
                else
                    Toast.makeText(this, "통화 시스템 OFF 상태입니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setImage(int p)
    {
        final int image[]={R.drawable.parent, R.drawable.dad, R.drawable.mom, R.drawable.grandpa, R.drawable.grandma};

        ImageView imageView=(ImageView) findViewById(R.id.image);
        imageView.setImageResource(image[p]);
    }

    private Date loadCallLog(String ph1, String ph2)
    {
        if (checkSelfPermission(READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "통화 목록 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show();
            return null;
        }

        Date result=new Date();
        Cursor cursor = getBaseContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, "DATE DESC");
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                int type=cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                String num=cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                long datems=cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                Date date=new Date(Long.valueOf(datems));
                if(num.equals(ph1) || num.equals(ph2))
                {
                    result=date;
                    break;
                }
            }
        }
        return result;
    }
}