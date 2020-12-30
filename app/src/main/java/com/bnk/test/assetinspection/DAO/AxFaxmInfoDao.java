package com.bnk.test.assetinspection.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.bnk.test.assetinspection.Entity.AxFaxmInfo;
import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;

import java.util.List;

@Dao
public interface AxFaxmInfoDao {
    @Query(" SELECT INFO.AX_FAXM_INFO_ID," +
            "       INFO.AST_CD," +
            "       INFO.AST_DTL_CD, " +
            "       INFO.AST_NM, " +
            "       EMP.EMP_NM," +
            "       EMP.EMP_DEPT_NM" +
            "  FROM AX_FAXM_INFO AS INFO " +
            " INNER JOIN AX_FAXM_CGP AS CGP " +
            "    ON INFO.AX_FAXM_INFO_ID = CGP.AX_FAXM_INFO_ID " +
            " INNER JOIN EMP " +
            "    ON CGP.CGP_ID = EMP.EMP_NO  ")
    LiveData<List<InfoAndItmqAndFaxmCgp>> getAll();

    @Query(" SELECT * " +
            "  FROM AX_FAXM_INFO " +
            " WHERE AX_FAXM_INFO_ID = :axFaxmInfoId")
    AxFaxmInfo getOneInfo(long axFaxmInfoId);

    @Update
    void updateAssetInfo(AxFaxmInfo axFaxmInfo);
}
