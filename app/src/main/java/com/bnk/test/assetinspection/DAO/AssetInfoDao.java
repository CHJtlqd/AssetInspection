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
    @Query(" SELECT AxFaxmInfo.*, AxFaxcClsf.* " +
            "  FROM AX_FAXM_INFO as AxFaxmInfo " +
            " INNER JOIN AX_FAXC_CLSF as AxFaxcClsf " +
            "    ON AxFaxmInfo.AX_FAXM_INFO_ID = AxFaxcClsf.AX_FAXC_CLSF_ID ")
    LiveData<List<AssetInfoDetail>> getAllProduct();

    @Update
    void updateProduct(AxFaxmInfo axFaxmInfo);    // 비고 변경

    @Insert
    void insertProduct(AxFaxmInfo axFaxmInfo);
}
