package com.bnk.test.assetinspection;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.bnk.test.assetinspection.Entity.AxSvymTmrd;
import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;

import java.util.List;

public class Inspection extends AppCompatActivity {
    // 총 대상항목 수
    private int assetCount;

    // 조사확인 항목 수
    private LiveData<Integer> assetCheckCount;
    private TextView tmrdNm, assetCheck, assetAllCount, searchText;
    private AxSvymTmrd tmrd;
    private CardAdapter cAdapter;
    private ListView lView;
    private AppDataBase dataBase;
    private LiveData<List<InfoAndItmqAndFaxmCgp>> assetList;
    private Spinner searchSpinner, searchCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);

        dataBase = AppDataBase.getInstance(this);

        MyApplication myApp = (MyApplication) getApplication();
        tmrd = myApp.getAxSvymTmrd();

        lView = (ListView) findViewById(R.id.list_item);
        tmrdNm = (TextView) findViewById(R.id.textView);
        assetCheck = findViewById(R.id.asset_check_count);
        assetAllCount = findViewById(R.id.asset_count);

        // 조회조건 spinner
        searchSpinner = findViewById(R.id.search_inspection);
        searchCheck = findViewById(R.id.search_check);
        searchText = findViewById(R.id.search_text);

        tmrdNm.setText(tmrd.asvyTmrdNm);
        assetCount = dataBase.axSvymTrgtItmqDao().countAllTrgtItmq(tmrd.axSvymTmrdId);
        assetAllCount.setText(String.valueOf(assetCount));
        assetList = dataBase.axSvymTrgtItmqDao().getAllInfo(tmrd.axSvymTmrdId);
        assetCheckCount = dataBase.axSvymTrgtItmqDao().countCplTrgtItmq(tmrd.axSvymTmrdId);
        assetCheckCount.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                assetCheck.setText(integer.toString());
            }
        });
        assetList.observe(this, new Observer<List<InfoAndItmqAndFaxmCgp>>() {
            @Override
            public void onChanged(List<InfoAndItmqAndFaxmCgp> infoAndItmqAndFaxmCgps) {
                cAdapter = new CardAdapter(Inspection.this, infoAndItmqAndFaxmCgps);
                lView.setAdapter(cAdapter);

                lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(Inspection.this, "선택!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AssetDetail.class);
                        intent.putExtra("axFaxmInfo", cAdapter.getItem(position).axFaxmInfo);
                        intent.putExtra("check", "Inspection");
                        startActivity(intent);
                    }
                });

                searchSpinner.setSelection(searchSpinner.getSelectedItemPosition());
                if (searchSpinner.getSelectedItem().toString().equals("확인 여부")) {
                    searchCheck.post(new Runnable() {
                        @Override
                        public void run() {
                            if (searchCheck.getSelectedItemPosition() == 0) {       // 미확인
                                setListViewFilter("미확인");
                            } else if (searchCheck.getSelectedItemPosition() == 1) { // 확인
                                setListViewFilter("확인");
                            }
                        }
                    });
                } else {
                    searchText.post(new Runnable() {
                        @Override
                        public void run() {
                            setListViewFilter(searchText.getText().toString());
                        }
                    });
                }
            }
        });

        searchCheck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String search = (String) parent.getItemAtPosition(position);
                if (search.equals("확인")) {
                    setListViewFilter("확인");
                } else if (search.equals("미확인")) {
                    setListViewFilter("미확인");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String search = (String) parent.getItemAtPosition(position);
                if (search.equals("확인 여부")) {
                    searchText.setVisibility(View.GONE);
                    searchCheck.setVisibility(View.VISIBLE);
                    searchCheck.post(new Runnable() {
                        @Override
                        public void run() {
                            searchCheck.setSelection(0);
                            setListViewFilter("미확인");
                        }
                    });
                    Log.d("test", String.valueOf(searchCheck.getSelectedItemPosition()) + "+++++");
                } else {
                    searchText.setVisibility(View.VISIBLE);
                    searchCheck.setVisibility(View.GONE);
                    searchText.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                setListViewFilter(searchText.getText().toString());
            }
        });
    }

    public void backArrow(View v) {
        finish();
    }

    private void setListViewFilter(String filterText) {
        String sText = searchSpinner.getSelectedItem().toString() + "##" + filterText;
        ((CardAdapter) lView.getAdapter()).getFilter().filter(sText);
    }
}