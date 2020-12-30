package com.bnk.test.assetinspection.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.bnk.test.assetinspection.Entity.AxSvymTrgtItmq;
import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;

import java.util.List;

/**
 * 회차별 대상항목 조회
 */
@Dao
public interface AxSvymTrgtItmqDao {
    /**
     * 액티비티 onCreate 시 전역 객체로 등록된 회차정보의 ID 값을 불러와
     * 총 대상 항목의 갯수를 카운트
     *
     * @param axSvymTmrdId
     * @return
     */
    @Query("SELECT COUNT(*) " +
            " FROM AX_SVYM_TRGT_ITMQ " +
            "WHERE AX_SVYM_TMRD_ID = :axSvymTmrdId")
    int countAllTrgtItmq(long axSvymTmrdId);

    /**
     * 전역 객체로 등록된 회차정보의 ID 값을 불러와 확인 완료된 품목의 갯수를 카운트
     * LiveData로 DB의 값이 변경되면 자동으로 값을 불러와 적용
     *
     * @param axSvymTmrdId
     * @return
     */
    @Query("SELECT COUNT(*) " +
            " FROM AX_SVYM_TRGT_ITMQ " +
            "WHERE AX_SVYM_TMRD_ID = :axSvymTmrdId " +
            "  AND VD_DT not null")
    LiveData<Integer> countCplTrgtItmq(long axSvymTmrdId);

    /**
     * 회차별 대상항목 전체 조회
     * 대상항목 join 고정자산_기본정보 on 기본정보 ID join 고정자산_담당기본 on 기본정보 ID
     */
    @Query("SELECT INFO.AST_NM," +
            "      INFO.AST_CD," +
            "      INFO.AST_DTL_CD," +
            "      ITMQ.VD_DT," +
            "      ITMQ.TRGT_STCD," +
            "      ITMQ.VD_PRSN," +
            "      EMP.EMP_NM," +
            "      EMP.EMP_DEPT_NM," +
            "      ITMQ.AX_FAXM_INFO_ID " +
            " FROM AX_FAXM_INFO AS INFO " +
            "INNER JOIN AX_SVYM_TRGT_ITMQ AS ITMQ " +
            "   ON INFO.AX_FAXM_INFO_ID = ITMQ.AX_FAXM_INFO_ID " +
            "INNER JOIN AX_FAXM_CGP  AS CGP " +
            "   ON INFO.AX_FAXM_INFO_ID = CGP.AX_FAXM_INFO_ID " +
            "INNER JOIN EMP " +
            "   ON CGP.CGP_ID = EMP.EMP_NO " +
            "WHERE ITMQ.AX_SVYM_TMRD_ID = :axSvymTmrdId " +
            "ORDER BY VD_DT ")
    LiveData<List<InfoAndItmqAndFaxmCgp>> getAllInfo(long axSvymTmrdId);

    /**
     * 회차별 대상항목 By 품목 이름
     */
    @Query("SELECT INFO.AST_NM," +
            "      INFO.AST_CD," +
            "      INFO.AST_DTL_CD," +
            "      ITMQ.VD_DT," +
            "      ITMQ.TRGT_STCD," +
            "      ITMQ.VD_PRSN," +
            "      EMP.EMP_NM," +
            "      ITMQ.AX_FAXM_INFO_ID " +
            " FROM AX_FAXM_INFO AS INFO " +
            "INNER JOIN AX_SVYM_TRGT_ITMQ AS ITMQ " +
            "   ON INFO.AX_FAXM_INFO_ID = ITMQ.AX_FAXM_INFO_ID " +
            "INNER JOIN AX_FAXM_CGP AS CGP " +
            "   ON INFO.AX_FAXM_INFO_ID = CGP.AX_FAXM_INFO_ID " +
            "INNER JOIN EMP " +
            "   ON CGP.CGP_ID = EMP.EMP_NO " +
            "WHERE ITMQ.AX_SVYM_TMRD_ID = :axSvymTmrdId " +
            "  AND INFO.AST_NM LIKE '%' || :infoNm || '%' ")
    LiveData<List<InfoAndItmqAndFaxmCgp>> getInfoByNm(long axSvymTmrdId, String infoNm);

    /**
     * 회차별 대상항목 By 사용자 사번
     */
    @Query("SELECT INFO.AST_NM," +
            "      INFO.AST_CD," +
            "      INFO.AST_DTL_CD," +
            "      ITMQ.VD_DT," +
            "      ITMQ.TRGT_STCD," +
            "      ITMQ.VD_PRSN," +
            "      EMP.EMP_NM," +
            "      ITMQ.AX_FAXM_INFO_ID " +
            " FROM AX_FAXM_INFO AS INFO " +
            "INNER JOIN AX_SVYM_TRGT_ITMQ AS ITMQ " +
            "   ON INFO.AX_FAXM_INFO_ID = ITMQ.AX_FAXM_INFO_ID " +
            "INNER JOIN AX_FAXM_CGP AS CGP " +
            "   ON INFO.AX_FAXM_INFO_ID = CGP.AX_FAXM_INFO_ID " +
            "INNER JOIN EMP " +
            "   ON CGP.CGP_ID = EMP.EMP_NO " +
            "WHERE ITMQ.AX_SVYM_TMRD_ID = :axSvymTmrdId " +
            "  AND CGP.CGP_ID = :empNo ")
    LiveData<List<InfoAndItmqAndFaxmCgp>> getInfoByEmpNo(long axSvymTmrdId, int empNo);

    /**
     * 회차별 대상항목 By 부서
     */
    @Query("SELECT INFO.AST_NM," +
            "      INFO.AST_CD," +
            "      INFO.AST_DTL_CD," +
            "      ITMQ.VD_DT," +
            "      ITMQ.TRGT_STCD," +
            "      ITMQ.VD_PRSN," +
            "      EMP.EMP_NM," +
            "      ITMQ.AX_FAXM_INFO_ID " +
            " FROM AX_FAXM_INFO AS INFO " +
            "INNER JOIN AX_SVYM_TRGT_ITMQ AS ITMQ " +
            "   ON INFO.AX_FAXM_INFO_ID = ITMQ.AX_FAXM_INFO_ID " +
            "INNER JOIN AX_FAXM_CGP AS CGP " +
            "   ON INFO.AX_FAXM_INFO_ID = CGP.AX_FAXM_INFO_ID " +
            "INNER JOIN EMP " +
            "   ON CGP.CGP_ID = EMP.EMP_NO " +
            "WHERE ITMQ.AX_SVYM_TMRD_ID = :axSvymTmrdId " +
            "  AND CGP.CGP_DEPT_NM LIKE '%' || :deptNm || '%' ")
    LiveData<List<InfoAndItmqAndFaxmCgp>> getInfoByDeptNm(long axSvymTmrdId, String deptNm);

    /**
     * 회차별 대상항목 By 확인여부(확인)
     */
    @Query("SELECT INFO.AST_NM," +
            "      INFO.AST_CD," +
            "      INFO.AST_DTL_CD," +
            "      ITMQ.VD_DT," +
            "      ITMQ.TRGT_STCD," +
            "      ITMQ.VD_PRSN," +
            "      EMP.EMP_NM," +
            "      ITMQ.AX_FAXM_INFO_ID " +
            " FROM AX_FAXM_INFO AS INFO " +
            "INNER JOIN AX_SVYM_TRGT_ITMQ AS ITMQ " +
            "   ON INFO.AX_FAXM_INFO_ID = ITMQ.AX_FAXM_INFO_ID " +
            "INNER JOIN AX_FAXM_CGP AS CGP " +
            "   ON INFO.AX_FAXM_INFO_ID = CGP.AX_FAXM_INFO_ID " +
            "INNER JOIN EMP " +
            "   ON CGP.CGP_ID = EMP.EMP_NO " +
            "WHERE ITMQ.AX_SVYM_TMRD_ID = :axSvymTmrdId " +
            "  AND ITMQ.VD_DT IS NOT NULL")
    LiveData<List<InfoAndItmqAndFaxmCgp>> getInfoByIsCheck(long axSvymTmrdId);

    /**
     * 회차별 대상항목 By 확인여부(미확인)
     */
    @Query("SELECT INFO.AST_NM," +
            "      INFO.AST_CD," +
            "      INFO.AST_DTL_CD," +
            "      ITMQ.VD_DT," +
            "      ITMQ.TRGT_STCD," +
            "      ITMQ.VD_PRSN," +
            "      EMP.EMP_NM," +
            "      ITMQ.AX_FAXM_INFO_ID " +
            " FROM AX_FAXM_INFO AS INFO " +
            "INNER JOIN AX_SVYM_TRGT_ITMQ AS ITMQ " +
            "   ON INFO.AX_FAXM_INFO_ID = ITMQ.AX_FAXM_INFO_ID " +
            "INNER JOIN AX_FAXM_CGP AS CGP " +
            "   ON INFO.AX_FAXM_INFO_ID = CGP.AX_FAXM_INFO_ID " +
            "INNER JOIN EMP " +
            "   ON CGP.CGP_ID = EMP.EMP_NO " +
            "WHERE ITMQ.AX_SVYM_TMRD_ID = :axSvymTmrdId " +
            "  AND VD_DT IS NULL")
    LiveData<List<InfoAndItmqAndFaxmCgp>> getInfoByIsNotCheck(long axSvymTmrdId);

    /**
     * 회차별 대상항목 비고 update
     */
    @Update
    void updateItmq(AxSvymTrgtItmq axSvymTrgtItmq);

    /**
     * 회차별 대상항목 findOne
     * 자산상세 액티비티 시작 시 Intent로 넘어온 axFaxmInfoId 값과
     * 회차 전역객체를 이용하여 자산상세의 내용을 수집
     */
    @Query("SELECT * " +
            " FROM AX_SVYM_TRGT_ITMQ " +
            "WHERE AX_SVYM_TMRD_ID = :axSvymTmrdId " +
            "  AND AX_FAXM_INFO_ID = :axFaxmInfoId")
    AxSvymTrgtItmq findOneItmq(long axSvymTmrdId, long axFaxmInfoId);
}
