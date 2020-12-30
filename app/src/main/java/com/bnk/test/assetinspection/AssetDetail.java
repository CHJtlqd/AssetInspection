package com.bnk.test.assetinspection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bnk.test.assetinspection.DAO.AxFaxmCgpDao;
import com.bnk.test.assetinspection.DAO.AxFaxmInfoDao;
import com.bnk.test.assetinspection.DAO.AxSvymTrgtItmqDao;
import com.bnk.test.assetinspection.Entity.AssetInfoDetail;
import com.bnk.test.assetinspection.Entity.AxFaxmCgp;
import com.bnk.test.assetinspection.Entity.AxFaxmInfo;
import com.bnk.test.assetinspection.Entity.AxSvymTmrd;
import com.bnk.test.assetinspection.Entity.AxSvymTrgtItmq;
import com.bnk.test.assetinspection.Entity.Emp;
import com.bnk.test.assetinspection.Util.DateUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AssetDetail extends AppCompatActivity {
    private TextView astFlctLoc, astDtlCd, xpnitArtlNm, xpnitDtenNm, mdlNm, cmdtSn, aqsDtYear,
            astCgpNm, astCgpNo, astCgpDeptNm, tmrdVdDt, tmrdCgpNm, infoVdDt, rmrkCntn;
    private Button trgtStcdNormal, trgtStcdBroken;
    private ImageView resultPhoto;
    private ImageButton addPhoto;
    private AxSvymTrgtItmq axSvymTrgtItmq;
    private AppDataBase dataBase;
    private AxSvymTmrd axSvymTmrd;
    private AssetInfoDetail assetInfoDetail;
    private File file;
    private Emp tmrdCgp, cgp, loginUser;
    private AxFaxmCgp axFaxmCgp;
    private RelativeLayout relTmrdVdDt, relTmrdCgpNm, relInfoVdDt;
    private LinearLayout trgtStcdLayout, assetYnBtnLayout;
    private Intent intent;
    private AxFaxmInfo axFaxmInfo;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private String today = simpleDateFormat.format(new Date());
    private String trgtStcd;

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
        // 보수 신청 반납신청
        assetYnBtnLayout = findViewById(R.id.asset_yn_btn_layout);

        // 대상상태
        trgtStcdLayout = findViewById(R.id.trgt_stcd_layout);
        trgtStcdNormal = findViewById(R.id.trgt_stcd_normal);
        trgtStcdBroken = findViewById(R.id.trgt_stcd_borken);
        trgtStcd = "01";

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
        relTmrdCgpNm = findViewById(R.id.rel_tmrd_cgp_nm);
        relTmrdVdDt = findViewById(R.id.rel_tmrd_vd_dt);

        infoVdDt = findViewById(R.id.info_vd_dt);
        rmrkCntn = findViewById(R.id.rmrk_cntn);

        resultPhoto = findViewById(R.id.result_photo);
        addPhoto = findViewById(R.id.add_photo);
        File sdcard = Environment.getExternalStorageDirectory();
        file = new File(sdcard.getAbsolutePath() + "/picture1.jpg");
        addPhoto.setVisibility(View.GONE);
        resultPhoto.setVisibility(View.GONE);


        // DB 객체 SELECT
        // 대상품목
        axSvymTrgtItmq = dataBase.axSvymTrgtItmqDao().findOneItmq(axSvymTmrd.axSvymTmrdId, intent.getLongExtra("axFaxmInfo", -1));
        // 대상품목 detail
        assetInfoDetail = dataBase.assetInfoDao().getAssetInfoDetailById(intent.getLongExtra("axFaxmInfo", -1));
        // 대상품목 기본
        axFaxmInfo = dataBase.axFaxmInfoDao().getOneInfo(intent.getLongExtra("axFaxmInfo", -1));
        // 대상품목 담당자
        axFaxmCgp = dataBase.axFaxmCgpDao().getOneFaxmCgp(intent.getLongExtra("axFaxmInfo", -1));
        //실제 확인자
        tmrdCgp = dataBase.empDao().getTrgtItmqCgp(intent.getLongExtra("axFaxmInfo", -1), axSvymTmrd.axSvymTmrdId);
        //loginUser
        loginUser = myApplication.getLoginEmp();

        setText();
    }

    private void setText() {
        astFlctLoc.setText(assetInfoDetail.flctLoc);
        astDtlCd.setText(assetInfoDetail.astCd + "-" + assetInfoDetail.astDtlCd);
        xpnitArtlNm.setText(assetInfoDetail.xpnitAptlNm);
        xpnitDtenNm.setText(assetInfoDetail.xpnitDtemNm);
        mdlNm.setText(assetInfoDetail.mdlNm);
        cmdtSn.setText(assetInfoDetail.cmdtSn);
        aqsDtYear.setText(assetInfoDetail.aqsDt.substring(0, 4));

        // 사용자
        cgp = dataBase.assetInfoDao().getCgp(intent.getLongExtra("axFaxmInfo", -1));
        astCgpNm.setText(cgp.empNm);
        astCgpNo.setText(String.valueOf(cgp.empNo));
        astCgpDeptNm.setText(cgp.empDeptNm);

        // 자산상세를 재활용하기 위해서 고정자산과 재물조사를 달리해줌 (layout setText)
        if (intent.getStringExtra("check").equals("AssetList")) {
            // 고정자산일 경우 현황탭이 달라짐
            trgtStcdLayout.setVisibility(View.GONE);
            relTmrdCgpNm.setVisibility(View.GONE);
            relTmrdVdDt.setVisibility(View.GONE);

            if (axFaxmInfo.getRmrkCntn() != null) {
                rmrkCntn.setText(axFaxmInfo.getRmrkCntn());
            }

            // 변동일자
            String vdDt = axFaxmCgp.flctDt;
            if (vdDt != null) {
                infoVdDt.setText(DateUtil.dateFormat(vdDt));
            } else {
                infoVdDt.setText("-");
            }
        } else if (intent.getStringExtra("check").equals("Inspection")) {
            // 재물조사일 경우
            if (axSvymTrgtItmq.rmrkCntn != null) {
                rmrkCntn.setText(axSvymTrgtItmq.rmrkCntn);
            }
            assetYnBtnLayout.setVisibility(View.GONE);
            relInfoVdDt.setVisibility(View.GONE);
            if (tmrdCgp != null) {
                tmrdCgpNm.setText(tmrdCgp.empNm);
            } else {
                tmrdCgpNm.setText("-");
            }

            if (axSvymTrgtItmq.vdDt != null) {
                tmrdVdDt.setText(DateUtil.dateFormat(axSvymTrgtItmq.vdDt));
            } else {
                tmrdVdDt.setText("-");
            }

            if (axSvymTrgtItmq.getPicture() == null) {
                addPhoto.setVisibility(View.VISIBLE);
            } else {
                resultPhoto.setVisibility(View.VISIBLE);
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                bmpFactoryOptions.inJustDecodeBounds = true;

                bmpFactoryOptions.inSampleSize = 8;

                bmpFactoryOptions.inJustDecodeBounds = false;
                Bitmap bmp = BitmapFactory.decodeByteArray(axSvymTrgtItmq.picture, 0, axSvymTrgtItmq.picture.length, bmpFactoryOptions);
                resultPhoto.setImageBitmap(bmp);
            }

            if (axSvymTrgtItmq.getTrgtStcd() != null) {
                if (axSvymTrgtItmq.getTrgtStcd().equals("01")) {   //이상없음
                    trgtStcdOnClick(trgtStcdNormal);
                } else {
                    trgtStcdOnClick(trgtStcdBroken);
                }
            } else {
                trgtStcdOnClick(resultPhoto);
            }
        }


    }

    public void backArrow(View v) {
        finish();
    }

    public void trgtStcdOnClick(View view) {
        switch (view.getId()) {
            case R.id.trgt_stcd_normal:
                trgtStcd = "01";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    trgtStcdBroken.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
                } else {
                    trgtStcdBroken.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border));
                }
                trgtStcdBroken.setTextColor(Color.parseColor("#333333"));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    trgtStcdNormal.setBackground(ContextCompat.getDrawable(this, R.drawable.border_select));
                } else {
                    trgtStcdNormal.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_select));
                }
                trgtStcdNormal.setTextColor(Color.parseColor("#CB2B11"));
                break;
            case R.id.trgt_stcd_borken:
                trgtStcd = "02";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    trgtStcdNormal.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
                } else {
                    trgtStcdNormal.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border));
                }
                trgtStcdNormal.setTextColor(Color.parseColor("#333333"));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    trgtStcdBroken.setBackground(ContextCompat.getDrawable(this, R.drawable.border_select));
                } else {
                    trgtStcdBroken.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_select));
                }
                trgtStcdBroken.setTextColor(Color.parseColor("#CB2B11"));
                break;
            default:
                trgtStcd = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    trgtStcdNormal.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
                } else {
                    trgtStcdNormal.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border));
                }
                trgtStcdNormal.setTextColor(Color.parseColor("#333333"));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    trgtStcdBroken.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
                } else {
                    trgtStcdBroken.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border));
                }
                trgtStcdBroken.setTextColor(Color.parseColor("#333333"));
                break;
        }
    }

    public void updateDetail(View view) {

        CharSequence rmrkCntnStr = rmrkCntn.getText();
        // 자산상세를 재활용하기 위해서 고정자산과 재물조사를 달리해줌 (layout setText)
        if (intent.getStringExtra("check").equals("AssetList")) {
            // 고정자산일 경우
            axFaxmInfo.setRmrkCntn(rmrkCntnStr.toString());
            new UpdateAxFaxmInfo(dataBase.axFaxmInfoDao()).execute(axFaxmInfo);

        } else if (intent.getStringExtra("check").equals("Inspection")) {
            // 재물조사일 경우

            // 업데이트가 필요한지 없는지 확인
            axSvymTrgtItmq.setRmrkCntn(rmrkCntnStr.toString());
            int size = (int) file.length();
            byte[] bytes = new byte[size];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                buf.read(bytes, 0, bytes.length);
                buf.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            axSvymTrgtItmq.setPicture(bytes);
            axFaxmCgp.flctDt = today;
            axSvymTrgtItmq.setVdPrsn(loginUser.empNo);
            axSvymTrgtItmq.setVdDt(today);
            axSvymTrgtItmq.setTrgtStcd(trgtStcd);
            new UpdateAxFaxmCgp(dataBase.axFaxmCgpDao()).execute(axFaxmCgp);
            new UpdateAxSyvmTrgtItmq(dataBase.axSvymTrgtItmqDao()).execute(axSvymTrgtItmq);
        }
        finish();
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

    public static class UpdateAxSyvmTrgtItmq extends AsyncTask<AxSvymTrgtItmq, Void, Void> {
        private AxSvymTrgtItmqDao mAxSvymTrgtItmqDao;


        UpdateAxSyvmTrgtItmq(AxSvymTrgtItmqDao axSvymTrgtItmqDao) {
            mAxSvymTrgtItmqDao = axSvymTrgtItmqDao;
        }

        @Override
        protected Void doInBackground(AxSvymTrgtItmq... axSvymTrgtItmqs) {
            mAxSvymTrgtItmqDao.updateItmq(axSvymTrgtItmqs[0]);
            return null;
        }
    }


    public void capture(View v) {
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

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
            intent.putExtra("check", "Inspection");
        }
    }

    public static class UpdateAxFaxmInfo extends AsyncTask<AxFaxmInfo, Void, Void> {
        private AxFaxmInfoDao mAxFaxmInfoDao;

        UpdateAxFaxmInfo(AxFaxmInfoDao axFaxmInfoDao) {
            mAxFaxmInfoDao = axFaxmInfoDao;
        }

        @Override
        protected Void doInBackground(AxFaxmInfo... axFaxmInfos) {
            mAxFaxmInfoDao.updateAssetInfo(axFaxmInfos[0]);
            return null;
        }
    }

    public static class UpdateAxFaxmCgp extends AsyncTask<AxFaxmCgp, Void, Void> {
        private AxFaxmCgpDao mAxFaxmCgpDao;

        UpdateAxFaxmCgp(AxFaxmCgpDao axFaxmCgpDao) {
            mAxFaxmCgpDao = axFaxmCgpDao;
        }

        @Override
        protected Void doInBackground(AxFaxmCgp... axFaxmCgps) {
            mAxFaxmCgpDao.updateFaxmCgp(axFaxmCgps[0]);
            return null;
        }
    }
}