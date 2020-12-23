package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 자산관리_고정자산_기본정보
 */
@Entity(tableName = "AX_FAXM_INFO", foreignKeys = {
        @ForeignKey(entity = AxFaxcClsf.class,
                parentColumns = "AX_FAXC_CLSF_ID",
                childColumns = "AX_FAXC_CLSF_ID"),
        @ForeignKey(entity = AxFaxcCmdt.class,
        parentColumns = "AX_FAXC_CMDT_ID",
        childColumns = "AX_FAXC_CMDT_ID")
})
public class AxFaxmInfo {
    @PrimaryKey
    @ColumnInfo(name = "AX_FAXM_INFO_ID")
    public long axFaxmInfoId;

    /**
     * 자산분류코드ID
     */
    @ColumnInfo(name = "AX_FAXC_CLSF_ID")
    public long axfaxcClsfId;

    /**
     * 품목코드ID
     */
    @ColumnInfo(name = "AX_FAXC_CMDT_ID")
    public long axFaxcCmdtId;

    /**
     * 자산코드
     */
    @ColumnInfo(name = "AST_CD")
    public String astCd;

    /**
     * 자산명
     */
    @ColumnInfo(name = "AST_NM")
    public String astNm;

    /**
     * 자산상세코드
     */
    @ColumnInfo(name = "AST_DTL_CD")
    public String astDtlCd;

    /**
     * 자산상세명
     */
    @Ignore
    @ColumnInfo(name = "AST_DTL_NM")
    public String astDtlNm;

    /**
     * 취득일자
     */
    @ColumnInfo(name = "AQS_DT")
    public String aqsDt;

    @ColumnInfo(name = "RMRK_CNTN")
    public String rmrkCntn;

    public AxFaxmInfo(long axFaxmInfoId, long axfaxcClsfId) {
        this.axFaxmInfoId = axFaxmInfoId;
        this.axfaxcClsfId = axfaxcClsfId;
    }

    @Override
    public String toString() {
        return "AX_FAXM_INFO{" +
                "axFaxmInfoId=" + axFaxmInfoId +
                ", axfaxcClsfId=" + axfaxcClsfId +
                ", astCd='" + astCd + '\'' +
                ", astNm='" + astNm + '\'' +
                ", astDtlCd='" + astDtlCd + '\'' +
                ", astDtlNm='" + astDtlNm + '\'' +
                ", aqsDt='" + aqsDt + '\'' +
                ", rmrkCntn='" + rmrkCntn + '\'' +
                '}';
    }
}
