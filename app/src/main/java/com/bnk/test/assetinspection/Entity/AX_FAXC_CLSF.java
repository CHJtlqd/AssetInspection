package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 자산관리_고정자산_자산분류코드
 */
@Entity(tableName = "AX_FAXC_CLSF")
public class AX_FAXC_CLSF {
    @PrimaryKey
    @ColumnInfo(name = "AX_FAXC_CLSF_ID")
    public long axFaxcClsfId;

    @ColumnInfo(name = "XPNIT_ARTL_NM")
    public String xpnitArtNm;

    @ColumnInfo(name = "XPNIT_CLUS_NM")
    public String xpnitClusNm;

    @ColumnInfo(name = "XPNIT_ITEM_NM")
    public String xpnitItemNm;

    @ColumnInfo(name = "XPNIT_DTEN_NM")
    public String xpnitDtenNm;
}
