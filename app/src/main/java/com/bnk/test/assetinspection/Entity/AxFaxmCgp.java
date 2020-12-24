package com.bnk.test.assetinspection.Entity;

/**
 * 자산관리_고정자산_담당기본
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Builder;
import lombok.Data;

@Entity(tableName = "AX_FAXM_CGP", foreignKeys = {
        @ForeignKey(entity = AxFaxmInfo.class,
                parentColumns = "AX_FAXM_INFO_ID",
                childColumns = "AX_FAXM_INFO_ID"),
        @ForeignKey(entity = Emp.class,
                parentColumns = "EMP_NO",
                childColumns = "CGP_ID")
})
@Data
public class AxFaxmCgp {
    @Builder
    public AxFaxmCgp(long axFaxmInfoId, int cgpId) {
        this.axFaxmInfoId = axFaxmInfoId;
        this.cgpId = cgpId;
    }

    @PrimaryKey(autoGenerate = true)
    public long AX_FAXM_CGP_ID;
    /**
     * 고정자산_기본정보 ID
     */
    @ColumnInfo(name = "AX_FAXM_INFO_ID")
    public long axFaxmInfoId;

    @Ignore
    @ColumnInfo(name = "CGP_DVCD")
    public String cgpDvcd;

    /**
     * 담당자 부서
     */
    @ColumnInfo(name = "CGP_DEPT_NM")
    public String cgpDeptNm;

    /**
     * 담당자 사번
     */
    @ColumnInfo(name = "CGP_ID")
    public int cgpId;
    /**
     * 변동일자
     */
    @ColumnInfo(name = "FLCT_DT")
    public String flctDt;
}
