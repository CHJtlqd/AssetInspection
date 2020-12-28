package com.bnk.test.assetinspection.DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.bnk.test.assetinspection.Entity.AssetInfoDetail;
import com.bnk.test.assetinspection.Entity.AxFaxmInfo;
import com.bnk.test.assetinspection.Entity.Emp;


/**
 * 자산 상세 페이지 Activity
 */
@Dao
public interface AssetInfoDao {
    /**
     * 자산 상세조회 자산정보
     * @param axFaxmInfoId
     * @return
     */
    @Query(" SELECT INFO.FLCT_LOC," +
            "       INFO.AST_CD," +
            "       INFO.AST_DTL_CD," +
            "       CLSF.XPNIT_ARTL_NM," +
            "       CLSF.XPNIT_DTEN_NM," +
            "       INFO.MDL_NM," +
            "       INFO.CMDT_SN," +
            "       INFO.AQS_DT" +
            "  FROM AX_FAXM_INFO AS INFO " +
            " INNER JOIN AX_FAXC_CLSF AS CLSF " +
            "    ON INFO.AX_FAXC_CLSF_ID = CLSF.AX_FAXC_CLSF_ID" +
            "   AND INFO.AX_FAXM_INFO_ID = :axFaxmInfoId")
    AssetInfoDetail getAssetInfoDetailById(long axFaxmInfoId);

    /**
     * 재물조사_대상품목 사용자 조회
     */
    @Query(" SELECT * " +
            "  FROM EMP " +
            " WHERE EMP_NO = (SELECT CGP_ID " +
            "                   FROM AX_FAXM_CGP " +
            "                  WHERE AX_FAXM_INFO_ID = :axFaxmInfoId)")
    Emp getCgp(long axFaxmInfoId);

    /**
     * 재물조사_대상품목 현황 -> AxSvymTrgtItmqDao 의 findOneItmq 사용
     * @param long axSvymTmrdId, long axFaxmInfoId
     */

    /**
     * 재물조사_대상품목 비고, 사진, 확인, 확인날짜,실제확인자, 대상상태 업데이트 -> AxSvymTrgtItmqDao 의 updateItmq
     * @param AxSvymTrgtItmqDao 객체
     */

    /**
     * 재물조사_대상품목_ 보수신청, 반납신청
     */
    @Update
    void updateCmdt(AxFaxmInfo axFaxmInfo);
}
