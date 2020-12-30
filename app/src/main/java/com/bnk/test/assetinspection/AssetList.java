package com.bnk.test.assetinspection;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
    private Spinner searchSpinner;
    private TextView searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);

        dataBase = AppDataBase.getInstance(this);

        searchSpinner = findViewById(R.id.search_asset);
        searchText = findViewById(R.id.search_text);

        lView = findViewById(R.id.asset_list_item);

        infoLists = dataBase.axFaxmInfoDao().getAll();
        infoLists.observe(this, new Observer<List<InfoAndItmqAndFaxmCgp>>() {
            @Override
            public void onChanged(List<InfoAndItmqAndFaxmCgp> infoAndItmqAndFaxmCgps) {
                cAdapter = new CardAdapterAsset(AssetList.this, infoAndItmqAndFaxmCgps);
                lView.setAdapter(cAdapter);



                lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(AssetList.this, "선택!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AssetDetail.class);
                        intent.putExtra("axFaxmInfo", cAdapter.getItem(position).axFaxmInfo);
                        intent.putExtra("check", "AssetList");
                        startActivity(intent);
                    }
                });

                searchSpinner.setSelection(searchSpinner.getSelectedItemPosition());
                searchText.post(new Runnable() {
                    @Override
                    public void run() {
                        String filterText = searchSpinner.getSelectedItem().toString() + "##" + searchText.getText();
                        ((CardAdapterAsset) lView.getAdapter()).getFilter().filter(filterText);
                    }
                });
            }
        });

        // search filter
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String filterText = searchSpinner.getSelectedItem().toString() + "##" + searchText.getText();
                ((CardAdapterAsset) lView.getAdapter()).getFilter().filter(filterText);
            }
        });

    }


    public void requestBtnOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rwd_apl_yn:
                Toast.makeText(this, "보수신청 추후 업데이트 예정입니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_return_yn:
                Toast.makeText(this, "반납신청 추후 업데이트 예정입니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void backArrow(View v) {
        finish();
    }
}