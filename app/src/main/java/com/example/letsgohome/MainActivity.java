package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.renderscript.ScriptGroup;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    TextView text;
    String data;

    String key="JlFKlwNcTBPfWE9XQoIM4Z1B%2FF3UL%2BlShTo739VP%2FE2Gh7WB8elM4fLEnvONxT9ITGj81ct9WrEjzXY0VmC7Yw%3D%3D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView)findViewById(R.id.result);
    }

    public void mOnClick(View v){

        switch(v.getId()){
            case R.id.button:
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        data= getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                text.setText(data); //TextView에 문자열  data 출력
                            }
                        });
                    }
                }).start();
                break;
        }
    }


    protected String getXmlData() {
        StringBuffer buffer=new StringBuffer();

        String url="http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo?serviceKey=JlFKlwNcTBPfWE9XQoIM4Z1B%2FF3UL%2BlShTo739VP%2FE2Gh7WB8elM4fLEnvONxT9ITGj81ct9WrEjzXY0VmC7Yw%3D%3D&numOfRows=10&pageNo=1&depPlaceId=NAT010000&arrPlaceId=NAT011668&depPlandTime=20161001&trainGradeCode=00";

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
                        buffer.append("파싱 시작\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if (tag.equals("item")) ;
                        else if (tag.equals("adultcharge")) ; // pass
                        else if (tag.equals("arrplacename")) {
                            buffer.append("도착지: ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("arrplandtime")) {
                            buffer.append("도착 시간: ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("depplacename")) {
                            buffer.append("출발지: ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("depplandtime")) {
                            buffer.append("출발 시간: ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("traingradename")) {
                            buffer.append("차량 종류: ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();
                        if (tag.equals("item"))
                            buffer.append("\n");
                        break;
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            buffer.append(e.getMessage().toString());
        }
        buffer.append("파싱 끝\n");

        return buffer.toString();
    }
}