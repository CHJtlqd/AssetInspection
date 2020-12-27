package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bnk.test.assetinspection.Entity.AssetInfoDetail;
import com.bnk.test.assetinspection.Entity.AxSvymTmrd;
import com.bnk.test.assetinspection.Entity.AxSvymTrgtItmq;
import com.bnk.test.assetinspection.Entity.Emp;

public class AssetDetail extends AppCompatActivity {
    private TextView astFlctLoc, astDtlCd, xpnitArtlNm, xpnitDtenNm, mdlNm, cmdtSn, aqsDtYear,
    astCgpNm, astCgpNo, astCgpDeptNm, tmrdVdDt, tmrdCgpNm;
    private AxSvymTrgtItmq axSvymTrgtItmq;
    private AppDataBase dataBase;
    private AxSvymTmrd axSvymTmrd;
    private AssetInfoDetail assetInfoDetail;
    private Emp tmrdCgp, cgp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);

        MyApplication myApplication = (MyApplication) getApplication();

        // INIT
        // 자산정보
        astFlctLoc = findViewById(R.id.ast_flct_loc);
        astDtlCd = findViewById(R.id.ast_dtl_cd);
        xpnitArtlNm = findViewById(R.id.xpnit_artl_nm);
        xpnitDtenNm = findViewById(R.id.xpnit_dten_nm);
        mdlNm = findViewById(R.id.mdl_nm);
        cmdtSn = findViewById(R.id.cmdt_sn);
        aqsDtYear = findViewById(R.id.aqs_dt_year);

        // 사용자
        astCgpNm = findViewById(R.id.ast_cgp_nm);
        astCgpNo = findViewById(R.id.ast_cgp_no);
        astCgpDeptNm = findViewById(R.id.ast_cgp_dept_nm);

        // 현황
        tmrdVdDt = findViewById(R.id.tmrd_vd_dt);
        tmrdCgpNm = findViewById(R.id.tmrd_cgp_nm);


        dataBase = AppDataBase.getInstance(this);
        axSvymTmrd = myApplication.getAxSvymTmrd();
        Intent intent = getIntent();
        axSvymTrgtItmq = dataBase.axSvymTrgtItmqDao().findOneItmq(axSvymTmrd.axSvymTmrdId, intent.getLongExtra("axFaxmInfo",-1));
        assetInfoDetail = dataBase.assetInfoDao().getAssetInfoDetailById(axSvymTrgtItmq.axFaxmInfoId);

        astFlctLoc.setText(assetInfoDetail.flctLoc);
        astDtlCd.setText(assetInfoDetail.astCd+"-"+assetInfoDetail.astDtlCd);
        xpnitArtlNm.setText(assetInfoDetail.xpnitAptlNm);
        xpnitDtenNm.setText(assetInfoDetail.xpnitDtemNm);
        mdlNm.setText(assetInfoDetail.mdlNm);
        cmdtSn.setText(assetInfoDetail.cmdtSn);
        aqsDtYear.setText(assetInfoDetail.aqsDt.substring(0,4));

        // 사용자
        cgp = dataBase.assetInfoDao().getCgp(axSvymTrgtItmq.axFaxmInfoId);
        astCgpNm.setText(cgp.empNm);
        astCgpNo.setText(String.valueOf(cgp.empNo));
        astCgpDeptNm.setText(cgp.empDeptNm);

        // 확인자
//        tmrdCgp = dataBase.empDao().getEmpByEmpNo();
    }

    public void backArrow(View v) {
        finish();
    }
}