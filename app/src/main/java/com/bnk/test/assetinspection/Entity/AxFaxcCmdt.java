package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 자산관리_고정자산_품목코드
 */
@Entity(tableName = "AX_FAXC_CMDT")
public class AxFaxcCmdt {
    @PrimaryKey
    @ColumnInfo(name = "AX_FAXC_CMDT_ID")
    public long axFaxcCmdtId;

    /**
     * 품목 시리얼 번호
     */
    @ColumnInfo(name = "CMDT_SEQ")
    public String cmdtSeq;

    /**
     * 품목명
     */
    @ColumnInfo(name = "CMDT_NM")
    public String cmdtNm;

    /**
     * 모델명
     */
    @ColumnInfo(name = "MDL_NM")
    public String mdlNm;

    /**
     * 보수신청여부
     */
    @ColumnInfo(name = "RWD_APL")
    public String rwdApl;

    /**
     * 회사ID
     */
    @Ignore
    @ColumnInfo(name = "HR_HRPM_COMP_ID")
    public int hrHrpmCompId;

    /**
     * 상태코드
     */
    @Ignore
    @ColumnInfo(name = "STCD")
    public String stcd;

    /**
     * 제조사
     */
    @Ignore
    @ColumnInfo(name = "MNF_COMP_NM")
    public String mnfCompNm;

    /**
     * 규격
     */
    @Ignore
    @ColumnInfo(name = "STND_NM")
    public String stndNm;

    /**
     * 품목단가
     */
    @Ignore
    @ColumnInfo(name = "CMDT_UNPR")
    public long cmdtUnpr;

    /**
     * 품목사진ID
     */
    @Ignore
    @ColumnInfo(name = "CMDT_PHO_ID")
    public long cmdtPhoId;

    /**
     * 보수금액구분코드
     */
    @Ignore
    @ColumnInfo(name = "RWD_AMT_DVCD")
    public String rwdAmtDvcd;



    /**
     * 사용여부(반납신청여부)
     * 반납신청여부 컬럼 부재, 추후 변경
     */
    @Ignore
    @ColumnInfo(name = "USE_YN")
    public String useYn;

    /**
     * 출력순서
     */
    @Ignore
    @ColumnInfo(name = "INQ_OUTPT_PRCNO")
    public String inqOutptPrcno;

    /**
     * 비고
     */
    @Ignore
    @ColumnInfo(name = "RMRK_CNTN")
    public String rmrkCntn;

    /**
     * 최초등록자
     */
    @Ignore
    @ColumnInfo(name = "FST_RGPR")
    public int fstRgpr;

    /**
     * 최초 등록 일시
     */
    @Ignore
    @ColumnInfo(name = "FST_RG_DTTI")
    public String fstRgDtti;

    /**
     * 최종 변경자
     */
    @Ignore
    @ColumnInfo(name = "LST_CHPR")
    public int lstChpr;

    /**
     * 최종 변경 일시
     */
    @Ignore
    @ColumnInfo(name = "LST_CH_DTTI")
    public String lstChDtti;

    /**
     * 그룹회사ID
     */
    @Ignore
    @ColumnInfo(name = "COM_CMM_CMGPR_ID")
    public String comCmmCmgprId;

    /**
     * 시스템구분ID
     */
    @Ignore
    @ColumnInfo(name = "COM_CMM_SYSTEM_ID")
    public String comCmmSystemId;
}
