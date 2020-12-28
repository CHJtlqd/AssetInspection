package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bnk.test.assetinspection.Entity.AxSvymTmrd;
import com.bnk.test.assetinspection.Entity.Emp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FirstPage extends AppCompatActivity {
    private AppDataBase dataBase;
    private TextView tmrdNm, tmrdPeriod;
    private AxSvymTmrd axSvymTmrd;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private String today = simpleDateFormat.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        tmrdNm = findViewById(R.id.tmrd_nm);
        tmrdPeriod = findViewById(R.id.tmrd_period);

        // DB생성
        dataBase = AppDataBase.getInstance(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // 현재 회차의 재물조사 확인 당일에 포함된 재물조사가 있다면 그 내용을 불러옴
        axSvymTmrd = dataBase.axSvymTmrdDao().getAxSvymTmrd(today);
        tmrdNm.setText(axSvymTmrd.asvyTmrdNm);
        StringBuilder sb = new StringBuilder();
        sb.append("조사기간  ");
        sb.append(axSvymTmrd.asvyYy).append(". ").append(axSvymTmrd.strdt.substring(4, 6)).append(". ").append(axSvymTmrd.strdt.substring(6, 8));
        sb.append(" ~ ");
        sb.append(axSvymTmrd.edt.substring(0,4)).append(". ").append(axSvymTmrd.edt.substring(4,6)).append(". ").append(axSvymTmrd.edt.substring(6,8));
        tmrdPeriod.setText(sb.toString());

        MyApplication myApp = (MyApplication) getApplication();
        myApp.setAxSvymTmrd(axSvymTmrd);
    }

    public void inspection(View v) {
        Toast.makeText(v.getContext(), "재물조사", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), Inspection.class);
        startActivity(intent);
    }

    public void listAllAssets(View v) {
        Toast.makeText(v.getContext(), "자산목록", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), AssetList.class);
        startActivity(intent);
    }
}