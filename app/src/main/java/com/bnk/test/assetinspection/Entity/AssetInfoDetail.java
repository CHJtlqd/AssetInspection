package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import lombok.ToString;

@ToString
public class AssetInfoDetail {
    /**
     * 자산관리_고정자산_자산분류코드
     */
    @ColumnInfo(name = "XPNIT_ARTL_NM")
    public String xpnitAptlNm;

    @ColumnInfo(name = "XPNIT_DTEN_NM")
    public String xpnitDtemNm;

    /**
     * 자산관리_고정자산_기본정보
     */
    @ColumnInfo(name = "AST_CD")
    public String astCd;

    @ColumnInfo(name = "AST_DTL_CD")
    public String astDtlCd;

    @ColumnInfo(name = "AQS_DT")
    public String aqsDt;

    /**
     * 자산관리_고정자산_품목코드
     */
    @ColumnInfo(name = "MDL_NM")
    public String mdlNm;

    @ColumnInfo(name = "CMDT_SN")
    public String cmdtSn;

    /**
     * 자산관리_고정자산_변동사항
     */
    @ColumnInfo(name = "FLCT_LOC")
    public String flctLoc;

    @ColumnInfo(name = "FLCT_DT")
    public String flctDt;
}