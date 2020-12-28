package com.bnk.test.assetinspection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;

import java.util.List;

public class AssetList extends AppCompatActivity {
    private CardAdapterAsset cAdapter;
    private ListView lView;
    private AppDataBase dataBase;
    private LiveData<List<InfoAndItmqAndFaxmCgp>> infoLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);

        dataBase = AppDataBase.getInstance(this);

        lView = findViewById(R.id.asset_list_item);

        infoLists = dataBase.axFaxmInfoDao().getAll();
        infoLists.observe(this, new Observer<List<InfoAndItmqAndFaxmCgp>>() {
            @Override
            public void onChanged(List<InfoAndItmqAndFaxmCgp> infoAndItmqAndFaxmCgps) {
                cAdapter = new CardAdapterAsset(AssetList.this, infoAndItmqAndFaxmCgps);
                lView.setAdapter(cAdapter);
                lView.setItemsCanFocus(false);

                lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(AssetList.this, "선택!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AssetDetail.class);
                        intent.putExtra("axFaxmInfo", cAdapter.getItem(position).axFaxmInfo);
                        intent.putExtra("check","AssetList");
                        startActivity(intent);
                    }
                });
            }
        });

    }

    public void backArrow(View v) {
        finish();
    }
}