package com.bnk.test.assetinspection.Entity;

/**
 * 자산관리_고정자산_담당기본
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "AX_FAXM_CGP", foreignKeys = {
        @ForeignKey(entity = AX_FAXM_INFO.class,
                parentColumns = "AX_FAXM_INFO_ID",
                childColumns = "AX_FAXM_INFO_ID"),
        @ForeignKey(entity = EMP.class,
                parentColumns = "EMP_NO",
                childColumns = "CGP_ID")
})
public class AX_FAXM_CGP {
    @PrimaryKey
    public long AX_FAXM_CGP_ID;

    @ColumnInfo(name = "AX_FAXM_INFO_ID")
    public long axFaxmInfoId;

    @ColumnInfo(name = "CGP_DVCD")
    public String cgpDvcd;

    @ColumnInfo(name = "CGP_DEPT_NM")
    public String cgpDeptNm;

    @ColumnInfo(name = "CGP_ID")
    public String cgpId;

    @ColumnInfo(name = "FLCT_DT")
    public String flctDt;
}
