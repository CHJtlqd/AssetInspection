package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AssetDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);

        Intent intent = getIntent();
        MyData myData = (MyData) intent.getSerializableExtra("data");

        TextView empe_nm = (TextView) findViewById(R.id.detail_empe_nm);
        empe_nm.setText(myData.empe_nm);
    }

    public void backArrow(View v) {
        finish();
    }
}