package com.bnk.test.assetinspection.Entity;

/**
 * 자산관리_재물조사_회차
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AX_SVYM_TMRD")
public class AX_SVYM_TMRD {
    @PrimaryKey
    @ColumnInfo(name = "AX_SVYM_TMRD_ID")
    public long axSvymTmrdId;

    @ColumnInfo(name = "ASVY_YY")
    public String asvyYy;

    @ColumnInfo(name = "ASVY_TMRD")
    public int asvyTmrd;

    @ColumnInfo(name = "ASVY_TMRD_NM")
    public String asvyTmrdNm;

    @ColumnInfo(name = "STRDT")
    public String strdt;

    @ColumnInfo(name = "EDT")
    public String edt;

    @ColumnInfo(name = "TMRD_STCD")
    public String tmrdStcd;

    @ColumnInfo(name = "VD_DT")
    public String vdDt;
}
