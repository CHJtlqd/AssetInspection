package com.bnk.test.assetinspection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText empeNo;
    private CheckBox rememberNo;
    private CheckBox autoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        empeNo = (EditText) findViewById(R.id.empeNo);
        rememberNo = (CheckBox) findViewById(R.id.rememberNo);
        autoLogin = (CheckBox) findViewById(R.id.autoLogin);

        /* 프레퍼런스에서 데이터를 읽어온다. */
        if (savedInstanceState == null) {
            SharedPreferences prefs = getSharedPreferences("login_info", 0);

            String empeno = prefs.getString("empeNo", "");
            boolean rememberno = prefs.getBoolean("rememberNo", false);
            boolean autologin = prefs.getBoolean("autoLogin", false);

            if (rememberno) {
                empeNo.setText(empeno);
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
        boolean rememberno = rememberNo.isChecked();
        boolean autologin = autoLogin.isChecked();

        if (rememberno) {
            editor.putString("empeNo", empeno);
        }
        editor.putBoolean("rememberNo", rememberno);
        editor.putBoolean("autoLogin", autologin);
        editor.apply(); // 안드로이드 2.2 이하에서는 editor.commit();
    }
}