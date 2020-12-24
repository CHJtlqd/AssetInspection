package com.bnk.test.assetinspection.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사원 테이블
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "EMP{" +
                "empNo=" + empNo +
                ", empNm='" + empNm + '\'' +
                ", empDeptNm='" + empDeptNm + '\'' +
                '}';
    }
}
