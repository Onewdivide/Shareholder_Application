package com.example.onewdivideslaptop.shareholder_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ((Button)findViewById(R.id.qr_login_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start <QR camera> activity
            }
        });

        ((Button)findViewById(R.id.qr_login_icon_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start <QR camera> activity
            }
        });

        ((Button)findViewById(R.id.serial_login_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start <Username log-in> activity
            }
        });

        ((Button)findViewById(R.id.serial_login_icon_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start <Username log-in> activity
            }
        });
    }
}
