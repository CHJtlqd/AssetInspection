package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * 자산관리_고정자산_변동사항
 */
@Entity(tableName = "AX_FAXH_FLCT", foreignKeys = {
        @ForeignKey(entity = AxFaxmInfo.class,
        parentColumns = "AX_FAXM_INFO_ID",
        childColumns = "AX_FAXM_INFO_ID")
})
public class AxFaxhFlct {
    @PrimaryKey
    @ColumnInfo(name = "AX_FAXH_FLCT_ID")
    public long axFaxhFlctId;
    /**
     * 고정자산_기본정보 ID
     */
    @ColumnInfo(name = "AX_FAXM_INFO_ID")
    public long axFaxmInfoId;

    /**
     * 변동 위치
     */
    @ColumnInfo(name = "FLCT_LOC")
    public String flctLoc;
}
