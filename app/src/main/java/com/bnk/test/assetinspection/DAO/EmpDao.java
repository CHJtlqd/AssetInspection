package com.bnk.test.assetinspection.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bnk.test.assetinspection.Entity.AxFaxcClsf;
import com.bnk.test.assetinspection.Entity.AxFaxmCgp;
import com.bnk.test.assetinspection.Entity.AxFaxmInfo;
import com.bnk.test.assetinspection.Entity.AxSvymCgp;
import com.bnk.test.assetinspection.Entity.AxSvymTmrd;
import com.bnk.test.assetinspection.Entity.AxSvymTrgtItmq;
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
     * @return
     */
    @Query(" SELECT * " +
            "  FROM EMP " +
            " WHERE EMP_NO = :empNo ")
    Emp login(int empNo);

    @Query(" SELECT * " +
            "  FROM EMP ")
    LiveData<List<Emp>> getAllEmp();

    @Query(" SELECT * " +
            "  FROM EMP " +
            " WHERE EMP_NO = :empNo ")
    Emp getEmpByEmpNo(int empNo);

    @Query(" SELECT * " +
            "  FROM EMP " +
            " WHERE EMP.EMP_NO = (SELECT AX_SVYM_TRGT_ITMQ.VD_PRSN " +
            "                       FROM AX_SVYM_TRGT_ITMQ" +
            "                      WHERE AX_FAXM_INFO_ID = :axFaxmInfoId " +
            "                        AND AX_SVYM_TMRD_ID = :axSvymTmrdId)")
    Emp getTrgtItmqCgp(long axFaxmInfoId, long axSvymTmrdId);


    @Insert
    void insertEmp(Emp... emp);

    @Insert
    void insertAxFaxcClsf(AxFaxcClsf... axFaxcClsfs);

    @Insert
    void insertAxFaxmInfo(AxFaxmInfo... axFaxmInfos);

    @Insert
    void insertAxFaxmCgp(AxFaxmCgp... axFaxmCgps);

    @Insert
    void insertAxSvymTmrd(AxSvymTmrd... axSvymTmrds);

    @Insert
    void insertAxSvymCgp(AxSvymCgp... axSvymCgps);

    @Insert
    void insertAxSvymTrgtItmq(AxSvymTrgtItmq... axSvymTrgtItmqs);
}
