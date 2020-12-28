package com.bnk.test.assetinspection;

import java.io.Serializable;

public class MyData implements Serializable {
    public String ast_cd;
    public String ast_nm;
    public String empe_nm;
    public String check_date;

    public MyData(String ast_cd, String ast_nm, String empe_nm, String check_date) {
        this.ast_cd = ast_cd;
        this.ast_nm = ast_nm;
        this.empe_nm = empe_nm;
        this.check_date = check_date;
    }
}
