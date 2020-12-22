package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * 자산관리_재물조사_담당기본
 */
@Entity(tableName = "AX_SVYM_CGP",foreignKeys = {
        @ForeignKey(entity = AX_SVYM_TMRD.class,
        parentColumns = "AX_SVYM_TMRD_ID",
        childColumns = "AX_SVYM_TMRD_ID"),
        @ForeignKey(entity = EMP.class,
        parentColumns = "EMP_NO",
        childColumns = "CGP_EMP_ID")
})
public class AX_SVYM_CGP {
    @PrimaryKey
    @ColumnInfo(name = "AX_SVYM_CGP_ID")
    public long axSvymCgpId;

    @ColumnInfo(name = "AX_SVYM_TMRD_ID")
    public long axSvymTmrdId;

    @ColumnInfo(name = "CGP_DEPT_NM")
    public String cgpDeptNm;

    @ColumnInfo(name = "CGP_EMP_ID")
    public long cgpEmpId;
}
