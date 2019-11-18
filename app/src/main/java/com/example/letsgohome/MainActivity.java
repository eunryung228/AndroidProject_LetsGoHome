package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.letsgohome.MakeDB.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private DBHelper dbHelper;
    SQLiteDatabase db=null;
    ArrayAdapter<CharSequence> adhome1, adhome2;
    Spinner homeSpin2;

    List<String> list=new ArrayList<>();
    int myStation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DBHelper(this);
        db=dbHelper.getWritableDatabase();

        final Spinner homeSpin1=(Spinner) findViewById(R.id.home1);
        homeSpin2=(Spinner) findViewById(R.id.home2);

        adhome1=ArrayAdapter.createFromResource(this, R.array.region, R.layout.support_simple_spinner_dropdown_item);
        adhome1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        homeSpin1.setAdapter(adhome1);
        homeSpin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
            {
                if(homeSpin1.getItemAtPosition(p).equals("서울특별시"))
                {
                    adhome2=ArrayAdapter.createFromResource(MainActivity.this, R.array.seoul, R.layout.support_simple_spinner_dropdown_item);
                    adhome2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    homeSpin2.setAdapter(adhome2);
                    homeSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            myStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(homeSpin1.getItemAtPosition(p).equals("세종특별시"))
                {
                    adhome2=ArrayAdapter.createFromResource(MainActivity.this, R.array.sejong, R.layout.support_simple_spinner_dropdown_item);
                    adhome2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    homeSpin2.setAdapter(adhome2);
                    homeSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void mOnClick(View v)
    {
        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, list);
        ListView listView=(ListView) findViewById(R.id.stations);
        listView.setAdapter(adapter);

        switch (v.getId())
        {
            case R.id.addItem:
                list.add(homeSpin2.getItemAtPosition(myStation).toString());
                adapter.notifyDataSetChanged();


        }

    }

    /*
    db 정보 읽어오기
    public void mOnClick(View v)
    {
        TextView result=(TextView) findViewById(R.id.result);
        TextView result2=(TextView) findViewById(R.id.result2);

        Cursor cursor=db.rawQuery("SELECT * FROM STATIONS", null);

        while(cursor.moveToNext())
        {
            result.setText(cursor.getString(0));
            result2.setText(cursor.getString(1));
        }
    }
    */
}