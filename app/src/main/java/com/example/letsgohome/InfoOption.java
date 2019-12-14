package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.Manifest.permission.READ_CALL_LOG;

public class InfoOption extends AppCompatActivity
{
    EditText name;
    EditText pw;

    TextView call_name1;
    TextView call_phone1;
    TextView call_name2;
    TextView call_phone2;

    RadioGroup radioGroup;
    RadioButton radioButtonArray[]=new RadioButton[5];

    Switch switchCall;
    LinearLayout linearLayout;

    ArrayList<String> nameList=new ArrayList<>();
    ArrayList<String> phoneList=new ArrayList<>();

    boolean usePassword;
    boolean useCall;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_option);

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        Gson gson=new Gson();

        name=(EditText) findViewById(R.id.name);
        pw=(EditText) findViewById(R.id.password);

        call_name1=(TextView) findViewById(R.id.cName1);
        call_phone1=(TextView) findViewById(R.id.cPhone1);
        call_name2=(TextView) findViewById(R.id.cName2);
        call_phone2=(TextView) findViewById(R.id.cPhone2);

        linearLayout=(LinearLayout) findViewById(R.id.callLayout);
        switchCall=(Switch) findViewById(R.id.switchCall);



        switchCall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                int permissionCheck= ContextCompat.checkSelfPermission(getApplicationContext(), READ_CALL_LOG);
                if(permissionCheck== PackageManager.PERMISSION_DENIED)
                {
                    ActivityCompat.requestPermissions(InfoOption.this, new String[]
                            { READ_CALL_LOG }, 200 );
                }

                SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                if(isChecked)
                {
                    linearLayout.setVisibility(View.VISIBLE);
                    editor.putBoolean("useCall", true);
                    editor.apply();
                }
                else
                {
                    linearLayout.setVisibility(View.INVISIBLE);
                    editor.putBoolean("useCall", false);
                    editor.apply();
                }
            }
        });

        usePassword=pref.getBoolean("usePassword", false);
        useCall=pref.getBoolean("useCall", false);

        String nm=pref.getString("callNameList", null);
        String ph=pref.getString("callPhoneList", null);
        Type type=new TypeToken<ArrayList<String>>(){}.getType();

        nameList=gson.fromJson(nm, type);
        phoneList=gson.fromJson(ph, type);

        call_name1.setText(nameList.get(0));
        call_name2.setText(nameList.get(1));
        call_phone1.setText(phoneList.get(0));
        call_phone2.setText(phoneList.get(1));


        name.setText(pref.getString("name", null));
        pw.setText(pref.getInt("password", 0)+"");

        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);

        radioButtonArray[0]=(RadioButton) findViewById(R.id.radioParent);
        radioButtonArray[1]=(RadioButton) findViewById(R.id.radioDad);
        radioButtonArray[2]=(RadioButton) findViewById(R.id.radioMom);
        radioButtonArray[3]=(RadioButton) findViewById(R.id.radioGrandpa);
        radioButtonArray[4]=(RadioButton) findViewById(R.id.radioGrandma);
        radioButtonArray[pref.getInt("image", 0)].setChecked(true);

        for (int i=0; i<5; i++)
        {
            final int pos=i;
            radioButtonArray[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();

                    editor.remove("image");
                    editor.putInt("image", pos);
                    editor.apply();
                    ((MainPage) MainPage.mContext).setImage(pos);
                }
            });
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
        usePassword=pref.getBoolean("usePassword", false);
        useCall=pref.getBoolean("useCall", false);

        switchCall.setChecked(useCall);
        if(useCall)
            linearLayout.setVisibility(View.VISIBLE);
        else
            linearLayout.setVisibility(View.INVISIBLE);
    }

    public void mOnClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnName:
                if(name.length()==0)
                    Toast.makeText(this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
                else
                {
                    SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();

                    editor.remove("name");
                    editor.putString("name", name.getText().toString());
                    editor.apply();

                    Toast.makeText(this, "닉네임이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnPassword:
                if(pw.length()!=4)
                    Toast.makeText(this, "4자리 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show();
                else
                {
                    SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();

                    editor.remove("password");
                    editor.putInt("password", Integer.parseInt(pw.getText().toString()));
                    editor.apply();

                    Toast.makeText(this, "패스워드가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void mAddPhoneNumbers(View v)
    {
        switch (v.getId())
        {
            case R.id.btnAddPhone1:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, 0);
                break;
            case R.id.btnAddPhone2:
                Intent intent2 = new Intent(Intent.ACTION_PICK);
                intent2.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent2, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK)
        {
            SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);
            SharedPreferences.Editor editor=pref.edit();
            Gson gson=new Gson();

            String nm=pref.getString("callNameList", null);
            String ph=pref.getString("callPhoneList", null);
            Type type=new TypeToken<ArrayList<String>>(){}.getType();

            nameList=gson.fromJson(nm, type);
            phoneList=gson.fromJson(ph, type);

            Cursor cursor = getContentResolver().query(data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            cursor.moveToFirst();

            if(requestCode==0)
            {
                call_name1.setText(cursor.getString(0));
                call_phone1.setText(cursor.getString(1));

                nameList.set(0, cursor.getString(0));
                phoneList.set(0, cursor.getString(1));

                String strName=gson.toJson(nameList);
                String strPhone=gson.toJson(phoneList);

                editor.remove("callNameList");
                editor.remove("callPhoneList");
                editor.putString("callNameList", strName);
                editor.putString("callPhoneList", strPhone);
                editor.apply();
            }
            else
            {
                call_name2.setText(cursor.getString(0));
                call_phone2.setText(cursor.getString(1));

                nameList.set(1, cursor.getString(0));
                phoneList.set(1, cursor.getString(1));

                String strName=gson.toJson(nameList);
                String strPhone=gson.toJson(phoneList);

                editor.remove("callNameList");
                editor.remove("callPhoneList");
                editor.putString("callNameList", strName);
                editor.putString("callPhoneList", strPhone);
                editor.apply();
            }
            cursor.close();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}