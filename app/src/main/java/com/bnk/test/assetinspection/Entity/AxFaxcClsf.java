package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 자산관리_고정자산_자산분류코드
 */
@Data
@Entity(tableName = "AX_FAXC_CLSF")
public class AxFaxcClsf {
    @Builder
    public AxFaxcClsf(String xpnitArtNm, String xpnitDtenNm) {
        this.xpnitArtNm = xpnitArtNm;
        this.xpnitDtenNm = xpnitDtenNm;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AX_FAXC_CLSF_ID")
    public long axFaxcClsfId;
    /**
     * 분류명
     */
    @ColumnInfo(name = "XPNIT_ARTL_NM")
    public String xpnitArtNm;

    /**
     * 세목명
     */
    @ColumnInfo(name = "XPNIT_DTEN_NM")
    public String xpnitDtenNm;

    /**
     * 회사 ID
     */
    @Ignore
    @ColumnInfo(name = "HR_HRPM_COMP_ID")
    public int hrHrpmCompId;

    /**
     * 상태코드
     */
    @Ignore
    @ColumnInfo(name ="STCD")
    public String stcd;

    /**
     * 자산분류코드
     */
    @Ignore
    @ColumnInfo(name = "AST_CLSF_CD")
    public String astClsfCd;

    /**
     * 단계구분코드
     */
    @Ignore
    @ColumnInfo(name = "LVL_DVCD")
    public String lvlDvcd;

    /**
     * 분류코드
     */
    @Ignore
    @ColumnInfo(name = "XPNIT_ARTL_CD")
    public String xpnitArtlCd;

    /**
     * 항코드
     */
    @Ignore
    @ColumnInfo(name = "XPNIT_CLUS_CD")
    public String xpnitClusCd;

    /**
     * 항명
     */
    @Ignore
    @ColumnInfo(name = "XPNIT_CLUS_NM")
    public String xpnitClusNm;

    /**
     * 목코드
     */
    @Ignore
    @ColumnInfo(name = "XPNIT_ITEM_CD")
    public String xpnitItemCd;

    /**
     * 목명
     */
    @Ignore
    @ColumnInfo(name = "XPNIT_ITEM_NM")
    public String xpnitItemNm;

    /**
     * 세목코드
     */
    @Ignore
    @ColumnInfo(name = "XPNIT_DTEM_CD")
    public String xpnitDtemCd;

    /**
     * 상각방법코드
     */
    @Ignore
    @ColumnInfo(name = "RDMP_MTD_CD")
    public String rdmpMtdCd;

    /**
     * 내용연수
     */
    @Ignore
    @ColumnInfo(name = "SVC_LF")
    public int svcLf;

    /**
     * 상세정보구분
     */
    @Ignore
    @ColumnInfo(name = "INFO_DVCD")
    public String infoDvcd;

    /**
     * 사용여부
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

    @Override
    public String toString() {
        return "AX_FAXC_CLSF{" +
                "axFaxcClsfId=" + axFaxcClsfId +
                ", xpnitArtNm='" + xpnitArtNm + '\'' +
                ", xpnitClusNm='" + xpnitClusNm + '\'' +
                ", xpnitItemNm='" + xpnitItemNm + '\'' +
                ", xpnitDtenNm='" + xpnitDtenNm + '\'' +
                '}';
    }
}
