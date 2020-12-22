package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * 자산관리_재물조사_대상항목
 */
@Entity(tableName = "AX_SVYM_TRGT_ITMQ", foreignKeys = {
        @ForeignKey(entity = EMP.class,
        parentColumns = "EMP_NO",
        childColumns = "VD_APLN"),
        @ForeignKey(entity = EMP.class,
        parentColumns = "EMP_NO",
        childColumns = "VD_PRSN"),
        @ForeignKey(entity = AX_SVYM_TMRD.class,
        parentColumns = "AX_SVYM_TMRD_ID",
        childColumns = "AX_SVYM_TMRD_ID"),
        @ForeignKey(entity = AX_FAXM_INFO.class,
        parentColumns = "AX_FAXM_INFO_ID",
        childColumns = "AX_FAXM_INFO_ID")
})
public class AX_SVYM_TRGT_ITMQ {
    @PrimaryKey
    @ColumnInfo(name = "AX_SVYM_TRGT_ITMQ_ID")
    public long axSvymTrgtItmqId;

    @ColumnInfo(name = "AX_SVYM_TMRD_ID")
    public long axSvymTmrdId;

    @ColumnInfo(name = "AX_FAXM_INFO_ID")
    public long axFaxmInfoId;

    @ColumnInfo(name = "TRGT_STCD")
    public String trgtStcd;

    @ColumnInfo(name = "VD_APLN")
    public long vdApln;

    @ColumnInfo(name = "VD_PRSN")
    public long vdPrsn;

    @ColumnInfo(name = "VD_DT")
    public long vdDt;

    @ColumnInfo(name = "RMRK_CNTN")
    public String rmrkCntn;
}
