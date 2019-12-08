package com.example.letsgohome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity
{
    MaterialCalendarView calendarView;
    EventDecorator eventDecorator;

    Button buttonAdd;

    ArrayList<CalendarDay> dates=new ArrayList<CalendarDay>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        DBHelper dbHelper=new DBHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        dbHelper.resetDB(db);

        buttonAdd=(Button) findViewById(R.id.btnAdd);

        calendarView=(MaterialCalendarView) findViewById(R.id.calendar);
        calendarView.addDecorator(new WeekendDecorator());

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected)
            {
                if(eventDecorator.isIn(date))
                    buttonAdd.setText("날짜 삭제");
                else
                    buttonAdd.setText("날짜 추가");
            }
        });
        eventDecorator=new EventDecorator(Color.DKGRAY);
    }

    public void mOnClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnAdd:
                if(buttonAdd.getText()=="날짜 추가")
                {
                    eventDecorator.AddItem(calendarView.getSelectedDate());
                    calendarView.addDecorator(eventDecorator);
                    String date="";
                    date+=String.valueOf(calendarView.getSelectedDate().getYear());
                    date+="-"+String.valueOf(calendarView.getSelectedDate().getMonth());
                    date+="-"+String.valueOf(calendarView.getSelectedDate().getDay());

                    DBHelper dbHelper=new DBHelper(this);
                    SQLiteDatabase db=dbHelper.getWritableDatabase();
                    db.execSQL("INSERT INTO CALENDAR (DATE) VALUES (?) ", new String[] {date});
                    db.close();

                    buttonAdd.setText("날짜 삭제");
                }
                else
                {
                    eventDecorator.RemoveItem(calendarView.getSelectedDate());
                    calendarView.addDecorator(eventDecorator);
                    String date="";
                    date+=String.valueOf(calendarView.getSelectedDate().getYear());
                    date+="-"+String.valueOf(calendarView.getSelectedDate().getMonth());
                    date+="-"+String.valueOf(calendarView.getSelectedDate().getDay());

                    DBHelper dbHelper=new DBHelper(this);
                    SQLiteDatabase db=dbHelper.getWritableDatabase();
                    db.execSQL("DELETE FROM CALENDAR WHERE DATE ='"+date+"'");
                    db.close();

                    buttonAdd.setText("날짜 추가");
                }
                break;
            case R.id.btnBack:
                Intent intent=new Intent();
                finish();
                break;
        }
    }
}


