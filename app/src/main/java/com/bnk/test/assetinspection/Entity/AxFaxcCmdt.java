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
     * 사용여부(반납신청여부)
     * 반납신청여부 컬럼 부재, 추후 변경
     */
    @Ignore
    @ColumnInfo(name = "USE_YN")
    public String useYn;
}
