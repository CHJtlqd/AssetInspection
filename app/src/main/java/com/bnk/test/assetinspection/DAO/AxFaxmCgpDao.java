package com.bnk.test.assetinspection.DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.bnk.test.assetinspection.Entity.AxFaxmCgp;

@Dao
public interface AxFaxmCgpDao {
    // 담당자 한명 조회
    @Query(" SELECT * FROM AX_FAXM_CGP WHERE AX_FAXM_INFO_ID = :axFaxmInfoId")
    AxFaxmCgp getOneFaxmCgp(long axFaxmInfoId);

    /**
     * 대상항목을 확인하면 고정자산_담당기본 업데이트
     */
    @Update
    void updateFaxmCgp(AxFaxmCgp axFaxmCgp);
}
