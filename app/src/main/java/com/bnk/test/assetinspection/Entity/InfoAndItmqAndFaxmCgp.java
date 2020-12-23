package com.bnk.test.assetinspection.Entity;

import androidx.room.Embedded;

public class InfoAndItmqAndFaxmCgp {
    /**
     * 자산관리_고정자산_기본정보
     */
    @Embedded(prefix = "INFO_")
    public AxFaxmInfo axFaxmInfo;

    /**
     * 자산관리_고정자산_담당기본
     */
    @Embedded(prefix = "CGP_")
    public AxFaxmCgp axFaxmCgp;

    /**
     * 자산관리_재물조사_대상물품
     */
    @Embedded(prefix = "ITEMQ_")
    public AxSvymTrgtItmq axSvymTrgtItmq;
}
