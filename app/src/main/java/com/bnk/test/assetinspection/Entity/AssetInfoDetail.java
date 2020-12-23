package com.bnk.test.assetinspection.Entity;

import androidx.room.Embedded;

public class AssetInfoDetail {
    /**
     * 자산관리_고정자산_자산분류코드
     */
    @Embedded(prefix = "CLSF_")
    public AxFaxcClsf axFaxcClsf;

    /**
     * 자산관리_고정자산_기본정보
     */
    @Embedded(prefix = "INFO_")
    public AxFaxmInfo axFaxmInfo;

    /**
     * 자산관리_고정자산_품목코드
     */
    @Embedded(prefix = "CMDT_")
    public AxFaxcCmdt axFaxcCmdt;

    /**
     * 자산관리_고정자산_담당기본
     */
    @Embedded(prefix = "CGP_")
    public AxFaxmCgp axFaxmCgp;

    /**
     * 자산관리_고정자산_변동사항
     */
    @Embedded(prefix = "FLCT_")
    public AxFaxhFlct axFaxhFlct;

    @Override
    public String toString() {
        return "INFO_AND_CLSF{" +
                "axFaxcClsf=" + axFaxcClsf +
                ", axFaxmInfo=" + axFaxmInfo +
                '}';
    }
}
