package com.bnk.test.assetinspection.Entity;

/**
 * 자산관리_재물조사_회차
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Builder;
import lombok.Data;

@Data
@Entity(tableName = "AX_SVYM_TMRD")
public class AxSvymTmrd {
    @Builder
    public AxSvymTmrd(String asvyYy, int asvyTmrd, String asvyTmrdNm, String strdt, String edt, String tmrdStcd, String vdDt) {
        this.asvyYy = asvyYy;
        this.asvyTmrd = asvyTmrd;
        this.asvyTmrdNm = asvyTmrdNm;
        this.strdt = strdt;
        this.edt = edt;
        this.tmrdStcd = tmrdStcd;
        this.vdDt = vdDt;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AX_SVYM_TMRD_ID")
    public long axSvymTmrdId;
    /**
     * 회차 실행 연도
     */
    @ColumnInfo(name = "ASVY_YY")
    public String asvyYy;

    /**
     * 회차
     */
    @ColumnInfo(name = "ASVY_TMRD")
    public int asvyTmrd;

    /**
     * 회차명
     */
    @ColumnInfo(name = "ASVY_TMRD_NM")
    public String asvyTmrdNm;

    /**
     * 시작일자
     */
    @ColumnInfo(name = "STRDT")
    public String strdt;

    /**
     * 종료일자
     */
    @ColumnInfo(name = "EDT")
    public String edt;

    /**
     * 회차상태
     */
    @ColumnInfo(name = "TMRD_STCD")
    public String tmrdStcd;

    /**
     * 확인일자
     */
    @ColumnInfo(name = "VD_DT")
    public String vdDt;
}
