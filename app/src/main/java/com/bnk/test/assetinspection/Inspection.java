package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class Inspection extends AppCompatActivity {
    private MyData[] mData = {
            new MyData("1000001-101", "냉장고", "조영재", "2020.12.23"),
            new MyData("1000001-102", "냉장고", "가나다", "2020.12.20"),
            new MyData("1000002-101", "TV", "홍길동", "2020.12.25"),
            new MyData("1000003-101", "컴퓨터", "최홍준", "2020.12.22"),
            new MyData("1000007-101", "노트북", "강감찬", "")
    };
    private InspectionAdapter cAdapter;
    private ListView lView;
    private Spinner spinner;
    private String searchOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);

        lView = (ListView) findViewById(R.id.list_item);
        cAdapter = new InspectionAdapter(this, mData);
        lView.setAdapter(cAdapter);
        spinner = (Spinner) findViewById(R.id.spinner);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Inspection.this, "선택!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AssetDetail.class);
                intent.putExtra("data", mData[position]);
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

    public void backArrow(View v) {
        finish();
    }

}