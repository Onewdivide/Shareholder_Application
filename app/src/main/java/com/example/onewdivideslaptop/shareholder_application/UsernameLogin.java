package com.example.onewdivideslaptop.shareholder_application;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UsernameLogin extends AppCompatActivity {

    ImageButton backBtn;
    Button loginBtn;
    Dialog dialog;
    EditText usernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_login);
        dialog = new Dialog(this);

        usernameInput = (EditText) findViewById(R.id.serial_num_field);

        Intent intent = getIntent();
        if (intent.getStringExtra("Username") != null){
            Log.e("username from QR :",intent.getStringExtra("Username"));
            usernameInput.setText(intent.getStringExtra("Username"));
        }

        backBtn = (ImageButton) findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsernameLogin.this,LoginPage.class);
                startActivity(intent);
            }
        });

        }

        public void showConfirmPopup(View v){
            dialog.setContentView(R.layout.login_popup);
            TextView txtClose = (TextView) dialog.findViewById(R.id.txtClose);
            Button yesBtn = (Button) dialog.findViewById(R.id.yesBtn);

//            yesBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(UsernameLogin.this,LoginPage.class);
//                    startActivity(intent);
//                }
//            });

            Log.e("popUp","Show");
            txtClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("popUp","Close");
                    dialog.dismiss();
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }

}
