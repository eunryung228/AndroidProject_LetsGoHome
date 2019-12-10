package com.example.letsgohome.ktx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.StrictMode;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letsgohome.DBHelper;
import com.example.letsgohome.ListViewAdapter;
import com.example.letsgohome.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class KTXInformation extends AppCompatActivity
{
    KTXListViewAdapter adapter;
    ListView info;

    String data;

    String key="JlFKlwNcTBPfWE9XQoIM4Z1B%2FF3UL%2BlShTo739VP%2FE2Gh7WB8elM4fLEnvONxT9ITGj81ct9WrEjzXY0VmC7Yw%3D%3D";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ktxinformation);

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
        Gson gson=new Gson();

        String myst=pref.getString("myStation", null);
        String homest=pref.getString("hometownStation", null);
        Type type=new TypeToken<ArrayList<String>>(){}.getType();

        ArrayList<String> myList=gson.fromJson(myst, type);
        ArrayList<String> hometownList=gson.fromJson(homest, type);

        ArrayList<String> myKey=new ArrayList<>();
        ArrayList<String> homeKey=new ArrayList<>();

        DBHelper dbHelper=new DBHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor;

        for (int i=0; i<myList.size(); i++)
        {
            cursor=db.rawQuery("SELECT ID FROM STATIONS WHERE NAME='"+ myList.get(i) +"'", null);
            cursor.moveToNext();
            myKey.add(cursor.getString(0));
        }
        for (int i=0; i<hometownList.size(); i++)
        {
            cursor=db.rawQuery("SELECT ID FROM STATIONS WHERE NAME='"+ hometownList.get(i) +"'", null);
            cursor.moveToNext();
            homeKey.add(cursor.getString(0));
        }

        adapter=new KTXListViewAdapter();
        info=(ListView) findViewById(R.id.information);
        info.setAdapter(adapter);
    }

    public void mOnClick(View v){

        switch(v.getId())
        {
            case R.id.btnResult:
                new Thread(new Runnable() {

                    @Override
                    public void run()
                    {
                        // TODO Auto-generated method stub
                        data=getXmlData();
                        Log.d("check", data);

                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                // TODO Auto-generated method stub
                                int pos1=0; int pos2=0; int pos3=0; int pos4=0; int pos5=0; int pos6=0; int pos7=0;
                                KTXListViewItem item=new KTXListViewItem();
                                while (pos7>=0)
                                {
                                    pos1=data.indexOf("ch");
                                    pos2=data.indexOf("arp");
                                    pos3=data.indexOf("art");
                                    pos4=data.indexOf("depp");
                                    pos5=data.indexOf("dept");
                                    pos6=data.indexOf("veh");
                                    pos7=data.indexOf("ch", pos6);

                                    if(pos7==-1)
                                        pos7=data.length();

                                    item.setCharge(data.substring(pos1+2, pos2));
                                    item.setDepTime(data.substring(pos5+4, pos6)+" "+data.substring(pos4+4, pos5));
                                    item.setArrTime(data.substring(pos3+3, pos4)+" "+data.substring(pos2+3, pos3));
                                    item.setVehicle(data.substring(pos6+3, pos7));
                                    adapter.addItem(item);
                                    adapter.notifyDataSetChanged();
                                    item=new KTXListViewItem();

                                    if(pos7==data.length())
                                        break;
                                    data=data.substring(pos7);
                                }
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    protected String getXmlData()
    {
        String url="http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo?serviceKey=JlFKlwNcTBPfWE9XQoIM4Z1B%2FF3UL%2BlShTo739VP%2FE2Gh7WB8elM4fLEnvONxT9ITGj81ct9WrEjzXY0VmC7Yw%3D%3D&numOfRows=50&pageNo=1&depPlaceId=" +
                "NAT010000" +
                "&arrPlaceId=" +
                "NAT011668" +
                "&depPlandTime=" +
                "20191210" +
                "&trainGradeCode=" +
                "00";

        String result="";

        try {
            URL obj_url = new URL(url);
            URLConnection connection=obj_url.openConnection();
            connection.setReadTimeout(3000);
            InputStream is=connection.getInputStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            InputStream in = obj_url.openStream();
            xpp.setInput(new InputStreamReader(in, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if (tag.equals("item")) ;
                        else if (tag.equals("adultcharge")){
                            xpp.next();
                            result+="ch"+xpp.getText();
                        }else if (tag.equals("arrplacename")) {
                            xpp.next();
                            result+="arp"+xpp.getText();
                        } else if (tag.equals("arrplandtime")) {
                            xpp.next();
                            result+="art"+xpp.getText().substring(8, 12);
                        } else if (tag.equals("depplacename")) {
                            xpp.next();
                            result+="depp"+xpp.getText();
                        } else if (tag.equals("depplandtime")) {
                            xpp.next();
                            result+="dept"+xpp.getText().substring(8, 12);
                        } else if (tag.equals("traingradename")) {
                            xpp.next();
                            result+="veh"+xpp.getText();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();
                        if (tag.equals("item"))
                        break;
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return result;
    }
}