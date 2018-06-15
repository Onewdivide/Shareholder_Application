package com.example.onewdivideslaptop.shareholder_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class UsernameLogin extends AppCompatActivity {

    ImageButton backBtn;
    Button loginBtn;
<<<<<<< HEAD
=======
    Dialog dialog;
    EditText usernameInput;

>>>>>>> d0a988a45a80544038c38d40370c170ca581998e

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_login);

        backBtn = (ImageButton) findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsernameLogin.this,LoginPage.class);
                startActivity(intent);
            }
        });

<<<<<<< HEAD
        loginBtn = (Button) findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsernameLogin.this,MainPage.class);
                startActivity(intent);
            }
        });
    }
=======
        }

        public void showConfirmPopup(View v){
            dialog.setContentView(R.layout.login_popup);
            TextView txtClose = (TextView) dialog.findViewById(R.id.txtClose);
            Button yesBtn = (Button) dialog.findViewById(R.id.yesBtn);

            yesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(UsernameLogin.this,MainPage.class);
                    startActivity(intent);
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

>>>>>>> d0a988a45a80544038c38d40370c170ca581998e
}


