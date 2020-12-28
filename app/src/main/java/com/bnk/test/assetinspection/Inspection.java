package com.bnk.test.assetinspection;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bnk.test.assetinspection.Entity.AxSvymTmrd;
import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;

import java.util.List;

public class  Inspection extends AppCompatActivity {
    // 총 대상항목 수
    private int assetCount;
    private TextView tmrdNm;
    private AxSvymTmrd tmrd;
    private MyData[] mData = {
            new MyData("1000001-101", "냉장고", "조영재", "2020.12.23"),
            new MyData("1000001-102", "냉장고", "가나다", "2020.12.20"),
            new MyData("1000002-101", "TV", "홍길동", "2020.12.25"),
            new MyData("1000003-101", "컴퓨터", "최홍준", "2020.12.22"),
            new MyData("1000007-101", "노트북", "강감찬", "")
    };
    private InspectionAdapter cAdapter;
    private ListView lView;
    private AppDataBase dataBase;
    private List<InfoAndItmqAndFaxmCgp> assetList;
    private Spinner spinner;
    private String searchOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);

        dataBase = AppDataBase.getInstance(this);

        MyApplication myApp = (MyApplication) getApplication();
        tmrd = myApp.getAxSvymTmrd();

        lView = (ListView) findViewById(R.id.list_item);
        cAdapter = new InspectionAdapter(this, assetList);
        tmrdNm = (TextView) findViewById(R.id.textView);
        tmrdNm.setText(tmrd.asvyTmrdNm);

        assetCount = dataBase.axSvymTrgtItmqDao().countAllTrgtItmq(tmrd.axSvymTmrdId);
        assetList = dataBase.axSvymTrgtItmqDao().getAllInfo(tmrd.axSvymTmrdId);

        lView.setAdapter(cAdapter);
        spinner = (Spinner) findViewById(R.id.spinner);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Inspection.this, "선택!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AssetDetail.class);
                intent.putExtra("axFaxmInfo", assetList.get(position).axFaxmInfo);
                intent.putExtra("check","Inspection");
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchOption = (String) parent.getItemAtPosition(position);
                Toast.makeText(parent.getContext(), searchOption, Toast.LENGTH_SHORT).show();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#111111"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setInspectionData(){

    }

    public void backArrow(View v) {
        finish();
    }

}