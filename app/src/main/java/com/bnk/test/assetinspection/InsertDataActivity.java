package com.bnk.test.assetinspection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.bnk.test.assetinspection.DAO.EmpDao;
import com.bnk.test.assetinspection.Entity.AxFaxcClsf;
import com.bnk.test.assetinspection.Entity.AxFaxmCgp;
import com.bnk.test.assetinspection.Entity.AxFaxmInfo;
import com.bnk.test.assetinspection.Entity.AxSvymTmrd;
import com.bnk.test.assetinspection.Entity.AxSvymTrgtItmq;
import com.bnk.test.assetinspection.Entity.Emp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InsertDataActivity extends AppCompatActivity {
    private AppDataBase dataBase;
    private Emp emp;
    private TextView textView;
    private int id = 20;
    private Button btn;
    private Emp[] emps = {Emp.builder().empNm("최홍준").empNo(2021120).empDeptNm("모바일사업부").build(),
            Emp.builder().empNm("조영재").empNo(2021121).empDeptNm("경영지원부").build(),
            Emp.builder().empNm("허진기").empNo(2021122).empDeptNm("디지털전략부").build(),
            Emp.builder().empNm("김민석").empNo(2021123).empDeptNm("모바일사업부").build(),
            Emp.builder().empNm("서무성").empNo(2020120).empDeptNm("경영지원부").build(),
            Emp.builder().empNm("류호진").empNo(2020109).empDeptNm("모바일사업부").build(),
            Emp.builder().empNm("한준").empNo(2017108).empDeptNm("경영지원부").build()};

    private AxFaxcClsf[] clsfs = {AxFaxcClsf.builder().xpnitArtNm("업무용 동산").xpnitDtenNm("컴퓨터").build(),
            AxFaxcClsf.builder().xpnitArtNm("업무용 동산").xpnitDtenNm("복사기").build(),
            AxFaxcClsf.builder().xpnitArtNm("업무용 동산").xpnitDtenNm("공기정화기").build(),
            AxFaxcClsf.builder().xpnitArtNm("업무용 동산").xpnitDtenNm("냉장고").build(),
            AxFaxcClsf.builder().xpnitArtNm("업무용 동산").xpnitDtenNm("스캐너").build(),
    };
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private String today = simpleDateFormat.format(new Date());
    private int year = new Date().getYear();
    /**
     * 시리얼 넘버 = 자산코드 + 자산상세코드 + 모델명
     */
    private AxFaxmInfo[] infos = {AxFaxmInfo.builder().axfaxcClsfId(1).aqsDt(today).astCd("101011").astDtlCd("101").astNm("삼성 컴퓨터").flctLoc("강서산단").mdlNm("COM19GG").rwdAplYn("0").useYn("0").build(),
            AxFaxmInfo.builder().axfaxcClsfId(1).aqsDt(today).astCd("101011").astDtlCd("102").astNm("삼성 컴퓨터").flctLoc("강서산단").mdlNm("COM19GG").rwdAplYn("0").useYn("0").build(),
            AxFaxmInfo.builder().axfaxcClsfId(2).aqsDt(today).astCd("101084").astDtlCd("201").astNm("삼성 복사기").flctLoc("강서산단").mdlNm("CPM19GG").rwdAplYn("0").useYn("0").build(),
            AxFaxmInfo.builder().axfaxcClsfId(2).aqsDt(today).astCd("201084").astDtlCd("101").astNm("LG 복사기").flctLoc("강서산단").mdlNm("CPM7899").rwdAplYn("0").useYn("0").build(),
            AxFaxmInfo.builder().axfaxcClsfId(3).aqsDt(today).astCd("201011").astDtlCd("101").astNm("삼성 공기청정기").flctLoc("강서산단").mdlNm("AIM19GG").rwdAplYn("0").useYn("0").build(),
            AxFaxmInfo.builder().axfaxcClsfId(3).aqsDt(today).astCd("102011").astDtlCd("101").astNm("LG 공기청정기").flctLoc("강서산단").mdlNm("AIM20AG").rwdAplYn("0").useYn("0").build(),
            AxFaxmInfo.builder().axfaxcClsfId(4).aqsDt(today).astCd("301011").astDtlCd("103").astNm("대우 냉장고").flctLoc("강서산단").mdlNm("RFM19GG").rwdAplYn("0").useYn("0").build(),
            AxFaxmInfo.builder().axfaxcClsfId(4).aqsDt(today).astCd("311011").astDtlCd("142").astNm("LG 냉장고").flctLoc("강서산단").mdlNm("RFM30A7").rwdAplYn("0").useYn("0").build(),
            AxFaxmInfo.builder().axfaxcClsfId(5).aqsDt(today).astCd("671011").astDtlCd("101").astNm("삼성 스캐너").flctLoc("강서산단").mdlNm("SNMAO84").rwdAplYn("0").useYn("0").build(),
            AxFaxmInfo.builder().axfaxcClsfId(5).aqsDt(today).astCd("291011").astDtlCd("101").astNm("캐논 스캐너").flctLoc("강서산단").mdlNm("SNM7844").rwdAplYn("0").useYn("0").build()};

    private AxFaxmCgp[] cgps = {AxFaxmCgp.builder().axFaxmInfoId(1).cgpId(2021120).build(),
            AxFaxmCgp.builder().axFaxmInfoId(3).cgpId(2021120).build(),
            AxFaxmCgp.builder().axFaxmInfoId(4).cgpId(2021120).build(),
            AxFaxmCgp.builder().axFaxmInfoId(5).cgpId(2021121).build(),
            AxFaxmCgp.builder().axFaxmInfoId(6).cgpId(2021122).build(),
            AxFaxmCgp.builder().axFaxmInfoId(7).cgpId(2021123).build(),
            AxFaxmCgp.builder().axFaxmInfoId(8).cgpId(2020120).build(),
            AxFaxmCgp.builder().axFaxmInfoId(9).cgpId(2020109).build(),
            AxFaxmCgp.builder().axFaxmInfoId(2).cgpId(2017108).build()};

    private AxSvymTmrd[] tmrds = {AxSvymTmrd.builder().asvyTmrd(1).asvyTmrdNm("제 1회차 재물조사").asvyYy("2020").edt("20210131").strdt(today).tmrdStcd("02").vdDt("20210116").build()};

    private AxSvymTrgtItmq[] trgtItmqs = {AxSvymTrgtItmq.builder().axFaxmInfoId(1).axSvymTmrdId(1).vdApln(2021120).build(),
            AxSvymTrgtItmq.builder().axFaxmInfoId(3).axSvymTmrdId(1).vdApln(2021120).build(),
            AxSvymTrgtItmq.builder().axFaxmInfoId(5).axSvymTmrdId(1).vdApln(2021120).build(),
            AxSvymTrgtItmq.builder().axFaxmInfoId(7).axSvymTmrdId(1).vdApln(2021120).build(),
            AxSvymTrgtItmq.builder().axFaxmInfoId(9).axSvymTmrdId(1).vdApln(2021120).build()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        textView = findViewById(R.id.textView);
        StringBuilder sb;
        for (AxFaxmInfo info : infos){
            sb = new StringBuilder();
            sb.append(info.getAstCd()).append(info.getMdlNm()).append(info.getAstDtlCd());
            sb.reverse();
            info.setCmdtSn(sb.toString());
        }

        // DB생성
        dataBase = AppDataBase.getInstance(this);

        // UI 갱신 (라이브데이터 Observer 이용, 해당 디비값이 변화가 생기면 실행됨)
        dataBase.empDao().getAllEmp().observe(this, new Observer<List<Emp>>() {
            @Override
            public void onChanged(List<Emp> emps) {
                textView.setText(emps.toString());
            }
        });
        //DB 데이터 불러오기 (SELECT)
        textView.setText(dataBase.empDao().getAllEmp().toString());
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertEmpTask(dataBase.empDao()).execute(emps);
                new InsertAxFaxmClsfTask(dataBase.empDao()).execute(clsfs);
                new InsertAxFaxmInfoTask(dataBase.empDao()).execute(infos);
                new InsertAxFaxmCgp(dataBase.empDao()).execute(cgps);
                new InsertAxSvymTmrd(dataBase.empDao()).execute(tmrds);
                new InsertAxSvymTmrdTrgtItmq(dataBase.empDao()).execute(trgtItmqs);
            }
        });
    }

    public static class InsertAxSvymTmrdTrgtItmq extends AsyncTask<AxSvymTrgtItmq, Void, Void>{
        private EmpDao mAssetInfoDao;
        InsertAxSvymTmrdTrgtItmq(EmpDao assetInfoDao){
            mAssetInfoDao = assetInfoDao;
        }
        @Override
        protected Void doInBackground(AxSvymTrgtItmq... axFaxmInfos) {
            mAssetInfoDao.insertAxSvymTrgtItmq(axFaxmInfos);
            return null;
        }
    }

    public static class InsertAxSvymTmrd extends AsyncTask<AxSvymTmrd, Void, Void>{
        private EmpDao mAssetInfoDao;
        InsertAxSvymTmrd(EmpDao assetInfoDao){
            mAssetInfoDao = assetInfoDao;
        }
        @Override
        protected Void doInBackground(AxSvymTmrd... axFaxmInfos) {
            mAssetInfoDao.insertAxSvymTmrd(axFaxmInfos);
            return null;
        }
    }
    public static class InsertAxFaxmCgp extends AsyncTask<AxFaxmCgp, Void, Void>{
        private EmpDao mAssetInfoDao;
        InsertAxFaxmCgp(EmpDao assetInfoDao){
            mAssetInfoDao = assetInfoDao;
        }
        @Override
        protected Void doInBackground(AxFaxmCgp... axFaxmInfos) {
            mAssetInfoDao.insertAxFaxmCgp(axFaxmInfos);
            return null;
        }
    }

    public static class InsertAxFaxmInfoTask extends AsyncTask<AxFaxmInfo, Void, Void>{
        private EmpDao mAssetInfoDao;
        InsertAxFaxmInfoTask(EmpDao assetInfoDao){
            mAssetInfoDao = assetInfoDao;
        }
        @Override
        protected Void doInBackground(AxFaxmInfo... axFaxmInfos) {
            mAssetInfoDao.insertAxFaxmInfo(axFaxmInfos);
            return null;
        }
    }

    public static class InsertEmpTask extends AsyncTask<Emp, Void, Void> {
        private EmpDao mEmpDao;

        public InsertEmpTask(EmpDao empDao) {
            this.mEmpDao = empDao;
        }

        @Override
        protected Void doInBackground(Emp... emps) {
            mEmpDao.insertEmp(emps);
            return null;
        }
    }

    public static class InsertAxFaxmClsfTask extends AsyncTask<AxFaxcClsf, Void, Void>{
        private EmpDao mAxFaxcClsfDao;
        public InsertAxFaxmClsfTask(EmpDao axFaxcClsfDao){
            mAxFaxcClsfDao = axFaxcClsfDao;
        }
        @Override
        protected Void doInBackground(AxFaxcClsf... axFaxcClsfs) {
            mAxFaxcClsfDao.insertAxFaxcClsf(axFaxcClsfs);
            return null;
        }
    }
}