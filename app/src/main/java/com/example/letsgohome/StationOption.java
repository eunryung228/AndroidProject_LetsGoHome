package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StationOption extends AppCompatActivity
{
    private int myRegion, myStation, hometownRegion, hometownStation;
    ListViewAdapter myAdapter, hometownAdapter;
    ListView listView1, listView2;

    ArrayAdapter<CharSequence> adhome1, adhome2;
    ArrayAdapter<CharSequence> adhometown1, adhometown2;

    Spinner homeSpin1, homeSpin2;
    Spinner hometownSpin1, hometownSpin2;

    ArrayList<String> myList=new ArrayList<>();
    ArrayList<String> homeList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
        Gson gson=new Gson();

        String myst=pref.getString("myStation", null);
        String homest=pref.getString("hometownStation", null);
        Type type=new TypeToken<ArrayList<String>>(){}.getType();

        myList=gson.fromJson(myst, type);
        homeList=gson.fromJson(homest, type);

        myAdapter=new ListViewAdapter();
        hometownAdapter=new ListViewAdapter();

        listView1=(ListView) findViewById(R.id.myStations);
        listView2=(ListView) findViewById(R.id.hometownStations);

        listView1.setAdapter(myAdapter);
        listView2.setAdapter(hometownAdapter);

        homeSpin1=(Spinner) findViewById(R.id.home1);
        homeSpin2=(Spinner) findViewById(R.id.home2);
        hometownSpin1=(Spinner) findViewById(R.id.hometown1);
        hometownSpin2=(Spinner) findViewById(R.id.hometown2);

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
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.seoul, R.layout.support_simple_spinner_dropdown_item);
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
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.sejong, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("부산광역시"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.busan, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("대구광역시"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.daegu, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("인천광역시"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.incheon, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("광주광역시"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.gwangju, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("대전광역시"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.daejeon, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("울산광역시"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.ulsan, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("경기도"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.gyeonggi, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("강원도"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.gangwon, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("충청북도"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.chungbuk, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("충청남도"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.chungnam, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("전라북도"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.jeonbuk, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("전라남도"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.jeonnam, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("경상북도"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.gyeongbuk, R.layout.support_simple_spinner_dropdown_item);
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
                else if(homeSpin1.getItemAtPosition(p).equals("경상남도"))
                {
                    myRegion=p;
                    adhome2=ArrayAdapter.createFromResource(StationOption.this, R.array.gyeongnam, R.layout.support_simple_spinner_dropdown_item);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        adhometown1=ArrayAdapter.createFromResource(this, R.array.region, R.layout.support_simple_spinner_dropdown_item);
        adhometown1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hometownSpin1.setAdapter(adhometown1);
        hometownSpin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
            {
                if(hometownSpin1.getItemAtPosition(p).equals("서울특별시"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.seoul, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("세종특별시"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.sejong, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("부산광역시"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.busan, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("대구광역시"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.daegu, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("인천광역시"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.incheon, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("광주광역시"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.gwangju, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("대전광역시"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.daejeon, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("울산광역시"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.ulsan, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("경기도"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.gyeonggi, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("강원도"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.gangwon, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("충청북도"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.chungbuk, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("충청남도"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.chungnam, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("전라북도"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.jeonbuk, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("전라남도"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.jeonnam, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("경상북도"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.gyeongbuk, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {
                        }
                    });
                }
                else if(hometownSpin1.getItemAtPosition(p).equals("경상남도"))
                {
                    hometownRegion=p;
                    adhometown2=ArrayAdapter.createFromResource(StationOption.this, R.array.gyeongnam, R.layout.support_simple_spinner_dropdown_item);
                    adhometown2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    hometownSpin2.setAdapter(adhometown2);
                    hometownSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int p, long id)
                        {
                            hometownStation=p;
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
        switch (v.getId())
        {
            case R.id.addItem:
                String stName=homeSpin2.getItemAtPosition(myStation).toString();
                boolean isFind=false;
                for(int i=0; i<myAdapter.getCount(); i++)
                {
                    if(myAdapter.getStationName(i)==stName)
                        isFind=true;
                }

                if(!isFind)
                {
                    boolean isIn=false;
                    if(hometownAdapter.getList().contains(stName))
                        isIn=true;

                    if(!isIn)
                    {
                        if(!myAdapter.addItem(homeSpin1.getItemAtPosition(myRegion).toString(),
                                stName))
                        {
                            Toast.makeText(this, "3개 이상 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(this, "고향 역에 추가된 역은 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "이미 추가된 역입니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addHometownItem:
                String htName=hometownSpin2.getItemAtPosition(hometownStation).toString();
                boolean ht_isFind=false;

                for(int i=0; i<hometownAdapter.getCount(); i++)
                {
                    if(hometownAdapter.getStationName(i)==htName)
                    {
                        ht_isFind=true;
                    }
                }

                if(!ht_isFind)
                {
                    boolean isIn = false;
                    if(myAdapter.getList().contains(htName))
                        isIn=true;

                    if (!isIn)
                    {
                        if (!hometownAdapter.addItem(hometownSpin1.getItemAtPosition(hometownRegion).toString(),
                                htName)) {
                            Toast.makeText(this, "3개 이상 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(this, "나의 집 주변 역에 추가된 역은 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "이미 추가된 역입니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEnd:
                if(myAdapter.getCount()==0 || hometownAdapter.getCount()==0)
                    Toast.makeText(this, "적어도 하나씩의 역을 추가해주세요.", Toast.LENGTH_SHORT).show();
                else
                {
                    SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();
                    Gson gson=new Gson();

                    String myst=gson.toJson(myAdapter.getList());
                    editor.remove("myStation");
                    editor.putString("myStation", myst);

                    String htst=gson.toJson(hometownAdapter.getList());
                    editor.remove("hometownStation");
                    editor.putString("hometownStation", htst);
                    editor.apply();

                    Intent intent=new Intent(getApplicationContext(), MainPage.class);
                    startActivityForResult(intent, 1000); // main code 1000
                }
                break;
        }
    }
}