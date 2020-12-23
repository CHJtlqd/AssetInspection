package com.bnk.test.assetinspection.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bnk.test.assetinspection.Entity.Emp;

import java.util.List;

/**
 * EMPDAO
 */
@Dao
public interface EmpDao {
    /**
     * 로그인 성공 후 전역 객체로 사원 정보를 등록
     *
     * @param empNo
     * @param empPw
     * @return
     */
    @Query(" SELECT * " +
            "  FROM EMP " +
            " WHERE EMP_NO = :empNo " +
            "   AND EMP_PW = :empPw ")
    Emp login(int empNo, String empPw);

    @Query(" SELECT * " +
            "  FROM EMP ")
    LiveData<List<Emp>> getAllEmp();

    @Query(" SELECT * " +
            "  FROM EMP " +
            " WHERE EMP_NO = :empNo ")
    Emp getEmpByEmpNo(int empNo);

    @Insert
    void insertEmp(Emp... emp);
}
