package com.bnk.test.assetinspection.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 사원 테이블
 */
@Entity(tableName = "EMP")
public class Emp {
    /**
     * 사번
     */
    @PrimaryKey
    @ColumnInfo(name = "EMP_NO")
    public int empNo;

    /**
     * 사원이름
     */
    @NonNull
    @ColumnInfo(name = "EMP_NM")
    public String empNm;

    /**
     * 부서이름
     */
    @NonNull
    @ColumnInfo(name = "EMP_DEPT_NM")
    public String empDeptNm;
    /**
     * 패스워드
     */
    @NonNull
    @ColumnInfo(name = "EMP_PW")
    public String empPw;

    public Emp(int empNo, @NonNull String empNm, @NonNull String empDeptNm, @NonNull String empPw) {
        this.empNo = empNo;
        this.empNm = empNm;
        this.empDeptNm = empDeptNm;
        this.empPw = empPw;
    }

    @Override
    public String toString() {
        return "EMP{" +
                "empNo=" + empNo +
                ", empNm='" + empNm + '\'' +
                ", empDeptNm='" + empDeptNm + '\'' +
                ", empPw='" + empPw + '\'' +
                '}';
    }
}
