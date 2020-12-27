package com.bnk.test.assetinspection;

import android.app.Application;

import com.bnk.test.assetinspection.Entity.AxSvymTmrd;

import lombok.Data;

@Data
public class MyApplication extends Application {
    private AxSvymTmrd axSvymTmrd;
}
