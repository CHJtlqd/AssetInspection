package com.bnk.test.assetinspection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bnk.test.assetinspection.DAO.AxSvymTrgtItmqDao;
import com.bnk.test.assetinspection.Entity.AssetInfoDetail;
import com.bnk.test.assetinspection.Entity.AxSvymTmrd;
import com.bnk.test.assetinspection.Entity.AxSvymTrgtItmq;
import com.bnk.test.assetinspection.Entity.Emp;

import java.io.File;

public class AssetDetail extends AppCompatActivity {
    private TextView astFlctLoc, astDtlCd, xpnitArtlNm, xpnitDtenNm, mdlNm, cmdtSn, aqsDtYear,
            astCgpNm, astCgpNo, astCgpDeptNm, tmrdVdDt, tmrdCgpNm, infoVdDt, rmrkCntn;
    private ImageView resultPhoto;
    private ImageButton addPhoto;
    private AxSvymTrgtItmq axSvymTrgtItmq;
    private AppDataBase dataBase;
    private AxSvymTmrd axSvymTmrd;
    private AssetInfoDetail assetInfoDetail;
    private Emp tmrdCgp, cgp;
    private RelativeLayout reltmrdVdDt, reltmrdCgpNm, relInfoVdDt;
    private Intent intent;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);
        intent = getIntent();
        init();
    }

    private void init() {
        MyApplication myApplication = (MyApplication) getApplication();
        dataBase = AppDataBase.getInstance(this);

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

        axSvymTmrd = myApplication.getAxSvymTmrd();

        // 현황
        tmrdVdDt = findViewById(R.id.tmrd_vd_dt);
        tmrdCgpNm = findViewById(R.id.tmrd_cgp_nm);

        relInfoVdDt = findViewById(R.id.rel_info_vd_dt);
        reltmrdCgpNm = findViewById(R.id.rel_tmrd_cgp_nm);
        reltmrdVdDt = findViewById(R.id.rel_tmrd_vd_dt);

        infoVdDt = findViewById(R.id.info_vd_dt);
        rmrkCntn = findViewById(R.id.rmrk_cntn);

        resultPhoto = findViewById(R.id.result_photo);
        addPhoto = findViewById(R.id.add_photo);
        File sdcard = Environment.getExternalStorageDirectory();
        file = new File(sdcard.getAbsolutePath() + "/picture1.jpg");
        addPhoto.setVisibility(View.GONE);
        resultPhoto.setVisibility(View.GONE);

        setText();
    }

    private void setText() {
        axSvymTrgtItmq = dataBase.axSvymTrgtItmqDao().findOneItmq(axSvymTmrd.axSvymTmrdId, intent.getLongExtra("axFaxmInfo", -1));
        assetInfoDetail = dataBase.assetInfoDao().getAssetInfoDetailById(axSvymTrgtItmq.axFaxmInfoId);

        astFlctLoc.setText(assetInfoDetail.flctLoc);
        astDtlCd.setText(assetInfoDetail.astCd + "-" + assetInfoDetail.astDtlCd);
        xpnitArtlNm.setText(assetInfoDetail.xpnitAptlNm);
        xpnitDtenNm.setText(assetInfoDetail.xpnitDtemNm);
        mdlNm.setText(assetInfoDetail.mdlNm);
        cmdtSn.setText(assetInfoDetail.cmdtSn);
        aqsDtYear.setText(assetInfoDetail.aqsDt.substring(0, 4));

        // 사용자
        cgp = dataBase.assetInfoDao().getCgp(axSvymTrgtItmq.axFaxmInfoId);
        astCgpNm.setText(cgp.empNm);
        astCgpNo.setText(String.valueOf(cgp.empNo));
        astCgpDeptNm.setText(cgp.empDeptNm);

        // 자산상세를 재활용하기 위해서 고정자산과 재물조사를 달리해줌 (layout setText)
        if (intent.getStringExtra("check").equals("AssetList")) {
            // 고정자산일 경우 현황탭이 달라짐
            reltmrdCgpNm.setVisibility(View.GONE);
            reltmrdVdDt.setVisibility(View.GONE);

            // 변동일자
            String vdDt = dataBase.assetInfoDao().getFlctDtByInfoId(axSvymTrgtItmq.axFaxmInfoId);
            if (vdDt != null) {
                infoVdDt.setText(vdDt);
            } else {
                infoVdDt.setText("-");
            }
        } else if (intent.getStringExtra("check").equals("Inspection")) {
            // 재물조사일 경우
            if (axSvymTrgtItmq.rmrkCntn != null) {
                rmrkCntn.setText(axSvymTrgtItmq.rmrkCntn);
            }
            relInfoVdDt.setVisibility(View.GONE);
            tmrdCgp = dataBase.empDao().getTrgtItmqCgp(axSvymTrgtItmq.axFaxmInfoId);
            if (tmrdCgp != null) {
                tmrdCgpNm.setText(tmrdCgp.empNm);
            } else {
                tmrdCgpNm.setText("-");
            }

            if (axSvymTrgtItmq.vdDt != null) {
                tmrdVdDt.setText(axSvymTrgtItmq.vdDt);
            } else {
                tmrdVdDt.setText("-");
            }
            addPhoto.setVisibility(View.VISIBLE);
        }
    }

    public void backArrow(View v) {
        finish();
    }

    public void updateDetail(View view) {
        CharSequence rmrkCntnStr = rmrkCntn.getText();
        // 자산상세를 재활용하기 위해서 고정자산과 재물조사를 달리해줌 (layout setText)
        if (intent.getStringExtra("check").equals("AssetList")) {
            // 고정자산일 경우
        } else if (intent.getStringExtra("check").equals("Inspection")) {
            // 재물조사일 경우
//            System.out.println(axSvymTrgtItmq+"++++++++++++++++++");
            axSvymTrgtItmq.setRmrkCntn(rmrkCntnStr.toString());
            new UpdateAxSyvmTrgtItmq(dataBase.axSvymTrgtItmqDao()).execute(axSvymTrgtItmq);
        }
        finish();
    }

    public static class UpdateAxSyvmTrgtItmq extends AsyncTask<AxSvymTrgtItmq, Void, Void> {
        private AxSvymTrgtItmqDao mAxSvymTrgtItmqDao;

        UpdateAxSyvmTrgtItmq(AxSvymTrgtItmqDao axSvymTrgtItmqDao) {
            mAxSvymTrgtItmqDao = axSvymTrgtItmqDao;
        }

        @Override
        protected Void doInBackground(AxSvymTrgtItmq... axSvymTrgtItmqs) {
            System.out.println(axSvymTrgtItmqs[0] + "++++++++");
            mAxSvymTrgtItmqDao.updateItmq(axSvymTrgtItmqs[0]);
            return null;
        }
    }

    public void capture(View v) {
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra("check", "AssetList");
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            resultPhoto.setVisibility(View.VISIBLE);

            BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
            bmpFactoryOptions.inJustDecodeBounds = true;

            bmpFactoryOptions.inSampleSize = 8;

            bmpFactoryOptions.inJustDecodeBounds = false;
            Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath(), bmpFactoryOptions);
            resultPhoto.setImageBitmap(bmp);

            addPhoto.setVisibility(View.GONE);
        }
    }
}