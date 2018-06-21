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

import java.util.regex.Pattern;

public class UsernameLogin extends AppCompatActivity {

    ImageButton backBtn;
    Button loginBtn;
    Dialog dialog;
    EditText usernameInput;
    Pattern delegateId_pattern = Pattern.compile("[0-9]+");


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

        private boolean isValidId(String delegateId){
            return delegateId_pattern.matcher(delegateId).matches();
        }

        public void showConfirmPopup(View v){
            String delegateId = usernameInput.getText().toString();
            if(!isValidId(delegateId)) return;

            dialog.setContentView(R.layout.login_popup);
            TextView txtClose = (TextView) dialog.findViewById(R.id.txtClose);
            Button yesBtn = (Button) dialog.findViewById(R.id.yesBtn);
            Button noBtn = (Button) dialog.findViewById(R.id.noBtn);

            TextView usernameTextView = dialog.findViewById(R.id.Username);
            String username = "xxxx xxxxx";
            usernameTextView.setText(username);

            yesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(UsernameLogin.this,MainPage.class);
                    startActivity(intent);
                }
            });

            noBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

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


