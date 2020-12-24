package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;

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
}
