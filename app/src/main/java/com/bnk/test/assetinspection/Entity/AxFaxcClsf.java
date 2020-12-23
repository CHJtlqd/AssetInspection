package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 자산관리_고정자산_자산분류코드
 */
@Entity(tableName = "AX_FAXC_CLSF")
public class AxFaxcClsf {
    @PrimaryKey
    @ColumnInfo(name = "AX_FAXC_CLSF_ID")
    public long axFaxcClsfId;

    @ColumnInfo(name = "XPNIT_ARTL_NM")
    public String xpnitArtNm;

    @Ignore
    @ColumnInfo(name = "XPNIT_CLUS_NM")
    public String xpnitClusNm;

    @Ignore
    @ColumnInfo(name = "XPNIT_ITEM_NM")
    public String xpnitItemNm;

    @ColumnInfo(name = "XPNIT_DTEN_NM")
    public String xpnitDtenNm;

    public AxFaxcClsf(long axFaxcClsfId) {
        this.axFaxcClsfId = axFaxcClsfId;
    }

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
