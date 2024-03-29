package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bnk.test.assetinspection.Entity.Emp;

public class Login extends AppCompatActivity {

    private EditText empeNo;
    private EditText password;
    private CheckBox rememberNo;
    private CheckBox autoLogin;
    private AppDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        empeNo = (EditText) findViewById(R.id.empe_no);
        password = (EditText) findViewById(R.id.password);
        rememberNo = (CheckBox) findViewById(R.id.remember_no);
        autoLogin = (CheckBox) findViewById(R.id.auto_login);

        dataBase = AppDataBase.getInstance(this);

        /* 프레퍼런스에서 데이터를 읽어온다. */
        if (savedInstanceState == null) {
            SharedPreferences prefs = getSharedPreferences("login_info", 0);

            String groupnm = prefs.getString("groupNm", "");
            String empeno = prefs.getString("empeNo", "");
            String pwd = prefs.getString("password", "");
            boolean rememberno = prefs.getBoolean("rememberNo", false);
            boolean autologin = prefs.getBoolean("autoLogin", false);

            if (rememberno) {
                empeNo.setText(empeno);
                password.setText(pwd);
            }
            rememberNo.setChecked(rememberno);
            autoLogin.setChecked(autologin);

            if (autologin) {
                Button btn = findViewById(R.id.loginButton);
                btn.callOnClick();
            }
        }
    }

    public void login(View v) {
        String empeno = empeNo.getText().toString();
        String pwd = password.getText().toString();

//        if (!empeno.equals("123") || !pwd.equals("abc")) {
//            Toast.makeText(v.getContext(), "아이디, 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
//            return;
//        }
        MyApplication myApplication = (MyApplication) getApplication();
        Emp loginEmp = dataBase.empDao().getEmpByEmpNo(Integer.parseInt(empeno));
        myApplication.setLoginEmp(loginEmp);
        savePref();

        Toast.makeText(v.getContext(), "로그인", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), FirstPage.class);
        startActivity(intent);
    }

    private void savePref() {
        /* 프레퍼런스에 데이터를 저장한다. */
        SharedPreferences prefs = getSharedPreferences("login_info", 0);

        SharedPreferences.Editor editor = prefs.edit();

        String empeno = empeNo.getText().toString();
        String pwd = password.getText().toString();
        boolean rememberno = rememberNo.isChecked();
        boolean autologin = autoLogin.isChecked();

        if (rememberno) {
            editor.putString("empeNo", empeno);
            editor.putString("password", pwd);
        }
        editor.putBoolean("rememberNo", rememberno);
        editor.putBoolean("autoLogin", autologin);
        editor.apply(); // 안드로이드 2.2 이하에서는 editor.commit();
    }
}