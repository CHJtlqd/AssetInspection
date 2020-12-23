package com.bnk.test.assetinspection;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bnk.test.assetinspection.DAO.AxFaxcClsfDao;
import com.bnk.test.assetinspection.DAO.AssetInfoDao;
import com.bnk.test.assetinspection.DAO.EmpDao;
import com.bnk.test.assetinspection.Entity.AxFaxcCmdt;
import com.bnk.test.assetinspection.Entity.AxFaxhFlct;
import com.bnk.test.assetinspection.Entity.AxFaxmCgp;
import com.bnk.test.assetinspection.Entity.AxFaxmInfo;
import com.bnk.test.assetinspection.Entity.AxSvymCgp;
import com.bnk.test.assetinspection.Entity.AxSvymTmrd;
import com.bnk.test.assetinspection.Entity.AxSvymTrgtItmq;
import com.bnk.test.assetinspection.Entity.AxFaxcClsf;
import com.bnk.test.assetinspection.Entity.Emp;

@Database(entities = {Emp.class, AxFaxcClsf.class, AxFaxmCgp.class, AxFaxmInfo.class, AxSvymCgp.class, AxSvymTmrd.class, AxSvymTrgtItmq.class, AxFaxhFlct.class, AxFaxcCmdt.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract EmpDao empDao();
    public abstract AxFaxcClsfDao axFaxcClsfDao();
    public abstract AssetInfoDao axFaxmInfoDao();

    private static AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context) {
        if (null == appDataBase) {
            appDataBase = buildDatabaseInstance(context);
        }
        return appDataBase;
    }

    private static AppDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDataBase.class,
                "Asset-Inspection").allowMainThreadQueries().build();
    }

    public void cleanUp() {
        appDataBase = null;
    }
}