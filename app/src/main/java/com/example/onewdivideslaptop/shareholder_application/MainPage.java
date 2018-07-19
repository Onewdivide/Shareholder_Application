package com.example.onewdivideslaptop.shareholder_application;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.onewdivideslaptop.shareholder_application.AppUtility.AGENDA_ALL;
import static com.example.onewdivideslaptop.shareholder_application.AppUtility.VOTE_AGREE;
import static com.example.onewdivideslaptop.shareholder_application.AppUtility.VOTE_DISAGREE;
import static com.example.onewdivideslaptop.shareholder_application.AppUtility.VOTE_NOCOMMENT;

public class MainPage extends AppCompatActivity {

    public static String[] thai_month = {
            "มกราคม",
            "กุมภาพันธ์",
            "มีนาคม",
            "เมษายน",
            "พฤษภาคม",
            "มิถุนายน",
            "กรกฎาคม",
            "สิงหาคม",
            "กันยายน",
            "ตุลาคม",
            "พฤศจิกายน",
            "ธันวาคม",
    };

    Button viewAllBtn,voteAgreeButton,voteDisagreeButton,voteNoCommentButton;
    TextView welcomeMessage;
    Dialog dialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        AppUtility.active_agenda = AppUtility.AGENDA_ALL;
        Authority.getAuthorities(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main_page);

                AppUtility.mainPage = context;
                AppUtility.focus(context);

                ((Button)findViewById(R.id.refreshButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        refresh();
                    }
                });

                viewAllBtn = (Button) findViewById(R.id.view_all_button);
                viewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppUtility.fetchAgenda(new Runnable() {
                            @Override
                            public void run() {
                                MainPage.this.startActivity(new Intent(MainPage.this,ViewAll.class));
                            }
                        });
                    }
                });

                dialog = new Dialog(context);

                voteAgreeButton = (Button) findViewById(R.id.vote_agree_button);
                voteAgreeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppUtility.active_vote_type = AppUtility.VOTE_AGREE;
                        AppUtility.active_agenda = AGENDA_ALL;
                        Authority.selectAuthority(dialog);
                    }
                });

                voteDisagreeButton = (Button) findViewById(R.id.vote_disagree_button);
                voteDisagreeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppUtility.active_vote_type = AppUtility.VOTE_DISAGREE;
                        AppUtility.active_agenda = AGENDA_ALL;
                        Authority.selectAuthority(dialog);
                    }
                });

                voteNoCommentButton = (Button) findViewById(R.id.vote_nocomment_button);
                voteNoCommentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppUtility.active_vote_type = AppUtility.VOTE_NOCOMMENT;
                        AppUtility.active_agenda = AGENDA_ALL;
                        Authority.selectAuthority(dialog);
                    }
                });

                validateButton();

                int date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                int month = Calendar.getInstance().get(Calendar.MONTH);
                int year = Calendar.getInstance().get(Calendar.YEAR);

                welcomeMessage = (TextView) findViewById(R.id.welcome_msg);
                welcomeMessage.setText("ยินดีต้อนรับ คุณ"
                        + AppUtility.getDelegateThaiName()
                        + "\nสู่การประชุมลงมติวาระ ประจำวันที่\n"
                        +date+" "+thai_month[month]+" "+year);
            }
        });


    }

    @Override
    public void onBackPressed(){

    }

    public void validateButton(){
        if(!Authority.anyAuthAvailable()) {
            voteAgreeButton.setEnabled(false);
            voteDisagreeButton.setEnabled(false);
            voteNoCommentButton.setEnabled(false);
        }else{
            voteAgreeButton.setEnabled(true);
            voteDisagreeButton.setEnabled(true);
            voteNoCommentButton.setEnabled(true);
        }
    }

    public void refresh(){
        Authority.requireUpdate();
        Authority.getAuthorities(new Runnable() {
            @Override
            public void run() {
                validateButton();
            }
        });
    }

}
