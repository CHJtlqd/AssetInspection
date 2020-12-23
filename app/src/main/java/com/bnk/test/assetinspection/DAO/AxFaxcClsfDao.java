package com.bnk.test.assetinspection.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bnk.test.assetinspection.Entity.AxFaxcClsf;

/**
 * AX_FAXC_CLSF DAO
 */
@Dao
public interface AxFaxcClsfDao {
    @Query(" SELECT * " +
            "  FROM AX_FAXC_CLSF " +
            " WHERE AX_FAXC_CLSF_ID = :axFaxcClsfId ")
    AxFaxcClsf getAxFaxcClsf(long axFaxcClsfId);

    @Insert
    void insertAxFaxcClsf(AxFaxcClsf axFaxcClsf);
}
