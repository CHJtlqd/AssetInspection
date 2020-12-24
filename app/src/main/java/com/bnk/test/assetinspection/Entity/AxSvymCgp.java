package com.bnk.test.assetinspection.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 자산관리_재물조사_담당기본
 */
@Entity(tableName = "AX_SVYM_CGP",foreignKeys = {
        @ForeignKey(entity = AxSvymTmrd.class,
        parentColumns = "AX_SVYM_TMRD_ID",
        childColumns = "AX_SVYM_TMRD_ID"),
        @ForeignKey(entity = Emp.class,
        parentColumns = "EMP_NO",
        childColumns = "CGP_EMP_ID")
})
public class AxSvymCgp {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AX_SVYM_CGP_ID")
    public long axSvymCgpId;

    /**
     * 회차ID
     */
    @ColumnInfo(name = "AX_SVYM_TMRD_ID")
    public long axSvymTmrdId;

    @Ignore
    @ColumnInfo(name = "CGP_DEPT_NM")
    public String cgpDeptNm;

    /**
     * 회차 담당자 사번
     */
    @ColumnInfo(name = "CGP_EMP_ID")
    public int cgpEmpId;
}
