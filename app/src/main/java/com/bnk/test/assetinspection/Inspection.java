package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Inspection extends AppCompatActivity {
    private MyData[] mData = {
            new MyData("1000001-101", "냉장고", "조영재", "2020.12.23"),
            new MyData("1000001-102", "냉장고", "가나다", "2020.12.20"),
            new MyData("1000002-101", "TV", "홍길동", "2020.12.25"),
            new MyData("1000003-101", "컴퓨터", "최홍준", "2020.12.22"),
            new MyData("1000007-101", "노트북", "", "")
    };
    private CardAdapter cAdapter;
    private ListView lView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);

        lView = (ListView) findViewById(R.id.list_item);
        cAdapter = new CardAdapter(this, mData);
        lView.setAdapter(cAdapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Inspection.this, "선택!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}