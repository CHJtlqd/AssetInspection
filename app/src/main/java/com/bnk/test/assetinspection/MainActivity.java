package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bnk.test.assetinspection.DAO.AssetInfoDao;
import com.bnk.test.assetinspection.DAO.EmpDao;
import com.bnk.test.assetinspection.Entity.AssetInfoDetail;
import com.bnk.test.assetinspection.Entity.AxFaxmInfo;
import com.bnk.test.assetinspection.Entity.AxFaxcClsf;
import com.bnk.test.assetinspection.Entity.Emp;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDataBase dataBase;
    private Emp emp;
    private TextView textView;
    private int id = 20;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        // DB생성
        dataBase = AppDataBase.getInstance(this);

//        // UI 갱신 (라이브데이터 Observer 이용, 해당 디비값이 변화가 생기면 실행됨)
//        dataBase.axFaxmInfoDao().getAllProduct().observe(this, new Observer<List<AssetInfoDetail>>() {
//            @Override
//            public void onChanged(List<AssetInfoDetail> emps) {
//                textView.setText(emps.toString());
//            }
//        });
//        //DB 데이터 불러오기 (SELECT)
//        textView.setText(dataBase.axFaxmInfoDao().getAllProduct().toString());
//        btn = findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                new InsertEmpTask(dataBase.empDao()).execute(new EMP(id++, "최홍준", "개발팀", "123"));
//                new InsertAxFaxmClsfTask(dataBase.axFaxcClsfDao()).execute(new AxFaxcClsf(id));
//                new InsertAxFaxmInfoTask(dataBase.axFaxmInfoDao()).execute(new AxFaxmInfo(id+1,id++));
//            }
//        });
    }

//    public static class InsertAxFaxmInfoTask extends AsyncTask<AxFaxmInfo, Void, Void>{
//        private AssetInfoDao mAssetInfoDao;
//        InsertAxFaxmInfoTask(AssetInfoDao assetInfoDao){
//            mAssetInfoDao = assetInfoDao;
//        }
//        @Override
//        protected Void doInBackground(AxFaxmInfo... axFaxmInfos) {
//            mAssetInfoDao.insertProduct(axFaxmInfos[0]);
//            return null;
//        }
//    }
//
//    public static class InsertEmpTask extends AsyncTask<Emp, Void, Void> {
//        private EmpDao mEmpDao;
//
//        public InsertEmpTask(EmpDao empDao) {
//            this.mEmpDao = empDao;
//        }
//
//        @Override
//        protected Void doInBackground(Emp... emps) {
//            mEmpDao.insertEmp(emps[0]);
//            return null;
//        }
//    }
//
//    public static class InsertAxFaxmClsfTask extends AsyncTask<AxFaxcClsf, Void, Void>{
//        private AxFaxcClsfDao mAxFaxcClsfDao;
//        public InsertAxFaxmClsfTask(AxFaxcClsfDao axFaxcClsfDao){
//            mAxFaxcClsfDao = axFaxcClsfDao;
//        }
//        @Override
//        protected Void doInBackground(AxFaxcClsf... axFaxcClsfs) {
//            mAxFaxcClsfDao.insertAxFaxcClsf(axFaxcClsfs[0]);
//            return null;
//        }
//    }
}