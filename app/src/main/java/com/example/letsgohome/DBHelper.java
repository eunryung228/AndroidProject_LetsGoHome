package com.example.letsgohome;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PrinterId;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION=1;

    private String ids[];
    {
        ids=new String[]{"NAT010000", "NAT010032", "NAT010058", "NAT010091", "NAT010106", "NAT020040", "NAT130036", "NAT130070", "NAT130104", "NAT130126",
                "NAT011403", "NAT011079", "NAT011154", "NAT011298","NAT014244", "NAT014281", "NAT014331", "NAT014445", "NAT840051", "NAT750046", "NAT750106", "NAT750161", "NAT750189", "NAT750254",
                "NAT013239", "NAT013271", "NATC30058", "NAT060231", "NATC10325", "NATC10580",
                "NAT031857", "NAT882904", "NAT882936", "NAT883012", "NAT883086",
                "NAT011668", "NAT030057", "NAT030173", "NAT011524","NATH13717", "NAT750560", "NAT750653", "NAT750726", "NAT750760", "NAT750822",
                "NAT010239", "NAT010415", "NAT010570", "NAT010670", "NAT010754", "NAT020178", "NAT020346", "NAT020430", "NAT020471", "NAT020524",
                "NATN10230", "NATN10428", "NATN10625", "NATN10787", "NAT020986", "NAT021082", "NAT021175", "NAT021357", "NAT130944", "NAT140681",
                "NAT011833", "NAT011916", "NAT011972", "NAT012016", "NAT012054", "NAT012124", "NAT012270", "NAT012355", "NAT021478", "NAT021549",
                "NAT010848", "NAT010971", "NAT030254", "NAT030396", "NAT030508", "NAT030607", "NAT080045", "NAT080147", "NAT080216", "NAT080309",
                "NAT030667", "NAT030718", "NAT030879", "NAT031056", "NAT031179", "NAT031314", "NAT040133", "NAT040173", "NAT040257", "NAT040352",
                "NAT031486", "NAT031638", "NAT031998", "NAT032099", "NAT032212", "NAT032273", "NAT032313", "NAT032422", "NAT032489", "NAT032563",
                "NAT8B0504", "NAT8B0595", "NAT8B0671", "NAT8B0737", "NAT012546", "NAT012700", "NAT012775", "NAT012821", "NAT012903", "NAT012968",
                "NAT013747", "NAT013841", "NAT013967", "NAT014058", "NAT014150", "NAT880099", "NAT880177", "NAT880179", "NAT880281", "NAT880310"};
    }
    private String names[];
    {
        names= new String[]{"서울", "용산", "노량진", "영등포", "신도림", "상봉", "서빙고", "옥수", "왕십리", "청량리", "부강", "소정리", "전의", "조치원",
                "화명", "구포", "사상", "부산", "가야", "부전", "동래", "센텀", "신해운대", "송정","대구", "동대구",
                "인천공항T2", "주안", "검암", "인천공항T1", "광주송정", "효천", "서광주", "광주", "극락강",
                "대전", "서대전", "흑석리", "신탄진", "울산(통도사)", "남창", "덕하", "태화강", "효문", "호계",
                "안양", "수원", "오산", "서정리", "평택", "덕소", "양수", "국수", "아신", "양평",
                "횡성", "둔내", "평창", "진부", "동화", "원주", "반곡", "신림", "백마고지", "백양리",
                "옥천", "이원", "지탄", "심천", "각계", "영동", "황간", "추풍령", "봉양", "제천",
                "성환", "천안", "계룡", "연산", "논산", "강경", "아산", "온양온천", "신창", "도고온천",
                "용동", "함열", "익산", "김제", "신태인", "정읍", "삼례", "동산", "전주", "신리",
                "백양사", "장성", "나주", "다시", "함평", "무안", "몽탄", "일로", "임성리", "목포",
                "월포", "장사", "강구", "영덕", "김천", "아포", "구미", "사곡", "약목", "왜관",
                "상동", "밀양", "삼랑진", "원동", "물금", "한림정", "진영", "진례", "창원중앙", "창원"};
    }

    public DBHelper(Context context)
    {
        super(context, "stationdb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            // STATIONS DB
            StringBuffer sb=new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS STATIONS( ");
            sb.append("ID VARCHAR(20) PRIMARY KEY, ");
            sb.append("NAME VARCHAR(20) )");
            db.execSQL(sb.toString());

            for(int i=0; i<names.length; i++)
            {
                db.execSQL("INSERT INTO STATIONS"+ "(ID, NAME) VALUES ('"+ ids[i]+"', '"+ names[i]+"');");
            }

            // CALENDAR DB
            sb=new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS CALENDAR( ");
            sb.append("ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sb.append("DATE DATE )");
            db.execSQL(sb.toString());
            Log.d("db", "make calendar db");
        }
        catch (SQLiteException e)
        {
            Log.d("db", "sql error");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if(newVersion==DATABASE_VERSION)
        {
            db.execSQL("DROP TABLE STATIONS");
            db.execSQL("DROP TABLE CALENDAR");
            onCreate(db);
        }
    }

    public void resetDB(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE STATIONS");
        db.execSQL("DROP TABLE CALENDAR");
        onCreate(db);
    }
}