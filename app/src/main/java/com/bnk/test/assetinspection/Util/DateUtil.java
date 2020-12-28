package com.bnk.test.assetinspection.Util;

public class DateUtil {
    public static String dateFormat(String old){
        if(old == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(old.substring(0,4)).append(". ").append(old.substring(4,6))
                .append(". ").append(old.substring(6,8));
        return sb.toString();
    }
}
