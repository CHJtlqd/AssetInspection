package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * 자산관리_고정자산_기본정보
 */
@Entity(tableName = "AX_FAXM_INFO", foreignKeys = {
        @ForeignKey(entity = AX_FAXC_CLSF.class,
                parentColumns = "AX_FAXC_CLSF_ID",
                childColumns = "AX_FAXC_CLSF_ID")
})
public class AX_FAXM_INFO {
    @PrimaryKey
    @ColumnInfo(name = "AX_FAXM_INFO_ID")
    public long axFaxmInfoId;

    @ColumnInfo(name = "AX_FAXC_CLSF_ID")
    public long axfaxcClsfId;

    @ColumnInfo(name = "AST_CD")
    public String astCd;

    @ColumnInfo(name = "AST_NM")
    public String astNm;

    @ColumnInfo(name = "AST_DTL_CD")
    public String astDtlCd;

    @ColumnInfo(name = "AST_DTL_NM")
    public String astDtlNm;

    @ColumnInfo(name = "AQS_DT")
    public String aqsDt;

    @ColumnInfo(name = "RMRK_CNTN")
    public String rmrkCntn;
}
