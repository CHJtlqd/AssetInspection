package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
    }

    public void inspection(View v) {
        Toast.makeText(v.getContext(), "재물조사", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), Inspection.class);
        startActivity(intent);
    }

    public void listAllAssets(View v) {
        Toast.makeText(v.getContext(), "자산목록", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), AllAssetList.class);
        startActivity(intent);
    }
}