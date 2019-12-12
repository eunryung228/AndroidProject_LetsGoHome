package com.example.letsgohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

public class InfoOption extends AppCompatActivity
{
    EditText name;
    EditText pw;

    RadioGroup radioGroup;
    RadioButton radioButtonArray[]=new RadioButton[5];


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_option);

        SharedPreferences pref=getSharedPreferences("memFile", MODE_PRIVATE);

        name=(EditText) findViewById(R.id.name);
        pw=(EditText) findViewById(R.id.password);

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
}

class callSwitchListener implements CompoundButton.OnCheckedChangeListener
{
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        if(isChecked)
        {

        }
    }
}