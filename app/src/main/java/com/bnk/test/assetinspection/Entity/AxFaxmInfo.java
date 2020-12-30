package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 자산관리_고정자산_기본정보
 */

@Entity(tableName = "AX_FAXM_INFO", foreignKeys = {
        @ForeignKey(entity = AxFaxcClsf.class,
                parentColumns = "AX_FAXC_CLSF_ID",
                childColumns = "AX_FAXC_CLSF_ID"),
})
@Data
@ToString
public class AxFaxmInfo {
    @Builder
    public AxFaxmInfo(long axfaxcClsfId, String astCd, String astNm, String astDtlCd, String mdlNm, String cmdtSn, String useYn, String rwdAplYn, String aqsDt, String flctLoc, String rmrkCntn) {
        this.axfaxcClsfId = axfaxcClsfId;
        this.astCd = astCd;
        this.astNm = astNm;
        this.astDtlCd = astDtlCd;
        this.mdlNm = mdlNm;
        this.cmdtSn = cmdtSn;
        this.useYn = useYn;
        this.rwdAplYn = rwdAplYn;
        this.aqsDt = aqsDt;
        this.flctLoc = flctLoc;
        this.rmrkCntn = rmrkCntn;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AX_FAXM_INFO_ID")
    public long axFaxmInfoId;

    /**
     * 자산분류코드ID
     */
    @ColumnInfo(name = "AX_FAXC_CLSF_ID")
    public long axfaxcClsfId;

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
     * 모델명
     */
    @ColumnInfo(name = "MDL_NM")
    public String mdlNm;

    /**
     * 시리얼넘버
     */
    @ColumnInfo(name = "CMDT_SN")
    public String cmdtSn;

    /**
     * 반납신청여부
     */
    @ColumnInfo(name = "USE_YN")
    public String useYn;

    /**
     * 보수신청여부
     */
    @ColumnInfo(name = "RWD_APL_YN")
    public String rwdAplYn;

    /**
     * 취득일자
     */
    @ColumnInfo(name = "AQS_DT")
    public String aqsDt;

    /**
     * 변동 위치
     */
    @ColumnInfo(name = "FLCT_LOC")
    public String flctLoc;

    @ColumnInfo(name = "RMRK_CNTN")
    public String rmrkCntn;
}
