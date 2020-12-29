package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;

import lombok.ToString;

@ToString
public class InfoAndItmqAndFaxmCgp {
    @ColumnInfo(name = "AST_NM")
    public String astNm;

    @ColumnInfo(name = "AST_CD")
    public String astCd;

    @ColumnInfo(name = "AST_DTL_CD")
    public String astDtlCd;

    @ColumnInfo(name = "VD_DT")
    public String vdDt;

    @ColumnInfo(name = "TRGT_STCD")
    public String trgtStcd;

    @ColumnInfo(name = "VD_PRSN")
    public String vdPrsn;

    @ColumnInfo(name = "EMP_NM")
    public String empNm;

    @ColumnInfo(name = "AX_FAXM_INFO_ID")
    public long axFaxmInfo;

    @ColumnInfo(name = "EMP_DEPT_NM")
    public String empDeptNm;
}
