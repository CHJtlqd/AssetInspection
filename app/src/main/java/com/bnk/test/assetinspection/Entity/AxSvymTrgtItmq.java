package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * 자산관리_재물조사_대상항목
 */
@Entity(tableName = "AX_SVYM_TRGT_ITMQ", foreignKeys = {
        @ForeignKey(entity = Emp.class,
        parentColumns = "EMP_NO",
        childColumns = "VD_APLN"),
        @ForeignKey(entity = Emp.class,
        parentColumns = "EMP_NO",
        childColumns = "VD_PRSN"),
        @ForeignKey(entity = AxSvymTmrd.class,
        parentColumns = "AX_SVYM_TMRD_ID",
        childColumns = "AX_SVYM_TMRD_ID"),
        @ForeignKey(entity = AxFaxmInfo.class,
        parentColumns = "AX_FAXM_INFO_ID",
        childColumns = "AX_FAXM_INFO_ID")
})
public class AxSvymTrgtItmq {
    @PrimaryKey
    @ColumnInfo(name = "AX_SVYM_TRGT_ITMQ_ID")
    public long axSvymTrgtItmqId;

    /**
     * 회차ID
     */
    @ColumnInfo(name = "AX_SVYM_TMRD_ID")
    public long axSvymTmrdId;

    /**
     * 담당 품목 ID
     */
    @ColumnInfo(name = "AX_FAXM_INFO_ID")
    public long axFaxmInfoId;

    /**
     * 대상 상태
     */
    @ColumnInfo(name = "TRGT_STCD")
    public String trgtStcd;

    /**
     * 확인 예정자
     */
    @ColumnInfo(name = "VD_APLN")
    public long vdApln;

    /**
     * 실제 확인자
     */
    @ColumnInfo(name = "VD_PRSN")
    public long vdPrsn;

    /**
     * 확인 일자
     */
    @ColumnInfo(name = "VD_DT")
    public long vdDt;

    /**
     * 비고
     */
    @ColumnInfo(name = "RMRK_CNTN")
    public String rmrkCntn;
}
