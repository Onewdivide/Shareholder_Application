package com.example.onewdivideslaptop.shareholder_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ((Button)findViewById(R.id.qr_login_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start <QR camera> activity
//                Intent intent = new Intent(LoginPage.this,QR_Camera_Activity.class);
//                startActivity(intent);

                IntentIntegrator integrator = new IntentIntegrator(LoginPage.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });

        ((Button)findViewById(R.id.qr_login_icon_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start <QR camera> activity
//                Intent intent = new Intent(LoginPage.this,QR_Camera_Activity.class);
//                startActivity(intent);

                IntentIntegrator integrator = new IntentIntegrator(LoginPage.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        ((Button)findViewById(R.id.serial_login_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start <Username log-in> activity
                Intent intent = new Intent(LoginPage.this,UsernameLogin.class);
                startActivity(intent);
            }
        });

        ((Button)findViewById(R.id.serial_login_icon_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start <Username log-in> activity
                Intent intent = new Intent(LoginPage.this,UsernameLogin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");

            } else {
                Log.e("Scan", "Scanned");

//                tv_qr_readTxt.setText(result.getContents());
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
