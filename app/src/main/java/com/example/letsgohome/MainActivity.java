package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity
{
    ArrayAdapter<CharSequence> adhome1, adhome2;
    Spinner homeSpin2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                            // 디비 추가하고 난 뒤에 이 함수 추가하기
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
                            // 디비 추가하고 난 뒤에 이 함수 추가하기
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
}