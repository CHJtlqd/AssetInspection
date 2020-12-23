package com.bnk.test.assetinspection.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;

import java.util.List;

/**
 * 회차별 대상항목 조회
 */
public interface AxSvymTrgtItmqDao {
    /**
     * 액티비티 onCreate 시 전역 객체로 등록된 회차정보의 ID 값을 불러와
     * 총 대상 항목의 갯수를 카운트
     *
     * @param axSvymTmrdId
     * @return
     */
    @Query("SELECT COUNT(*) FROM AX_SVYM_TRGT_ITMQ WHERE AX_SVYM_TMRD_ID = :axSvymTmrdId")
    int countAllTrgtItmq(long axSvymTmrdId);

    /**
     * 전역 객체로 등록된 회차정보의 ID 값을 불러와 확인 완료된 품목의 갯수를 카운트
     * LiveData로 DB의 값이 변경되면 자동으로 값을 불러와 적용
     * @param axSvymTmrdId
     * @return
     */
    @Query("SELECT COUNT(*) FROM AX_SVYM_TRGT_ITMQ WHERE AX_SVYM_TMRD_ID = :axSvymTmrdId AND VD_DT != null")
    LiveData<Integer> countCplTrgtItmq(long axSvymTmrdId);

    /**
     * 회차별 대상항목 전체 조회
     * 대상항목 join 고정자산_기본정보 on 기본정보 ID join 고정자산_담당기본 on 기본정보 ID
     */
    @Query("SELECT * " +
            " FROM AX_FAXM_INFO AS INFO " +
            "INNER JOIN AX_SVYM_TRGT_ITMQ AS ITMQ " +
            "   ON INFO.AX_FAXM_INFO_ID = ITMQ.AX_FAXM_INFO_ID " +
            "INNER JOIN AX_FAXM_CGP AS CGP " +
            "   ON INFO.AX_FAXM_INFO_ID = CGP.AX_FAXM_INFO_ID " +
            "WHERE ITMQ.AX_SVYM_TMRD_ID = :axSvymTmrdId ")
    LiveData<List<InfoAndItmqAndFaxmCgp>> getAllInfo(long axSvymTmrdId);

    /**
     * 회차별 대상항목 By 품목 이름
     */
    @Query("SELECT * " +
            " FROM AX_FAXM_INFO AS INFO " +
            "INNER JOIN AX_SVYM_TRGT_ITMQ AS ITMQ " +
            "   ON INFO.AX_FAXM_INFO_ID = ITMQ.AX_FAXM_INFO_ID " +
            "INNER JOIN AX_FAXM_CGP AS CGP " +
            "   ON INFO.AX_FAXM_INFO_ID = CGP.AX_FAXM_INFO_ID " +
            "WHERE ITMQ.AX_SVYM_TMRD_ID = :axSvymTmrdId")
    LiveData<List<InfoAndItmqAndFaxmCgp>> getInfoByNm(long axSvymTmrdId);

    /**
     * 회차별 대상항목 By 사용자 사번
     */

    /**
     * 회차별 대상항목 By 부서
     */

    /**
     * 회차별 대상항목 By 확인여부
     */
}
