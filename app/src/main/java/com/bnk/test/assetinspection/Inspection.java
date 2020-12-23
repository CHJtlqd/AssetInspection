package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Inspection extends AppCompatActivity {
    private CardAdapter cAdapter;
    private ListView lView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);

        lView = (ListView) findViewById(R.id.list_item);
        cAdapter = new CardAdapter(this);
        lView.setAdapter(cAdapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Inspection.this, "선택!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}