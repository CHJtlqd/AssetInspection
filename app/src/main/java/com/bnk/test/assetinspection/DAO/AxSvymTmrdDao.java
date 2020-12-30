package com.bnk.test.assetinspection.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.bnk.test.assetinspection.Entity.AxSvymTmrd;

/**
 * 회차정보 Dao
 */
@Dao
public interface AxSvymTmrdDao {
    /**
     * 로그인 성공 후
     * 오늘 날짜를 기준으로 회차정보 조회
     * <p>
     * 조회 후 애플리케이션 전역 객체로 등록
     *
     * @param today
     * @return AxSvymTmrd
     */
    @Query(" SELECT * " +
            "  FROM AX_SVYM_TMRD " +
            " WHERE STRDT <= :today " +
            "   AND EDT >= :today ")
    AxSvymTmrd getAxSvymTmrd(String today);
}
