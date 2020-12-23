package com.bnk.test.assetinspection.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bnk.test.assetinspection.Entity.AxFaxmInfo;
import com.bnk.test.assetinspection.Entity.AssetInfoDetail;

import java.util.List;

/**
 * 자산 상세 페이지 Activity
 */
@Dao
public interface AssetInfoDao {
    @Query(" SELECT INFO.*, CLSF.*, CMDT.*, FLCT.* " +
            "  FROM AX_FAXM_INFO AS INFO " +
            " INNER JOIN AX_FAXC_CLSF AS CLSF " +
            "    ON INFO.AX_FAXM_INFO_ID = CLSF.AX_FAXC_CLSF_ID" +
            "   AND INFO.AX_FAXM_INFO_ID = :axFaxmInfoId" +
            " INNER JOIN AX_FAXC_CMDT AS CMDT " +
            "    ON INFO.AX_FAXC_CMDT_ID = CMDT.AX_FAXC_CMDT_ID " +
            " INNER JOIN AX_FAXH_FLCT AS FLCT " +
            "    ON INFO.AX_FAXM_INFO_ID = FLCT.AX_FAXM_INFO_ID ")
    LiveData<AssetInfoDetail> getAssetInfoDetailById(long axFaxmInfoId);
}
