package com.example.onewdivideslaptop.shareholder_application;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.onewdivideslaptop.shareholder_application.AppUtility.AGENDA_ALL;
import static com.example.onewdivideslaptop.shareholder_application.AppUtility.VOTE_AGREE;
import static com.example.onewdivideslaptop.shareholder_application.AppUtility.VOTE_DISAGREE;
import static com.example.onewdivideslaptop.shareholder_application.AppUtility.VOTE_NOCOMMENT;

public class MainPage extends AppCompatActivity {

    Button viewAllBtn,voteAgreeButton,voteDisagreeButton,voteNoCommentButton;
    TextView welcomeMessage;
    Dialog dialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Authority.getAuthorities(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main_page);

                AppUtility.mainPage = context;
                AppUtility.focus(context);

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

                if(!Authority.anyAuthAvailable()) {
                    voteAgreeButton.setEnabled(false);
                    voteDisagreeButton.setEnabled(false);
                    voteNoCommentButton.setEnabled(false);
                }

                welcomeMessage = (TextView) findViewById(R.id.welcome_msg);
                welcomeMessage.setText("ยินดีต้อนรับ คุณ"
                        + AppUtility.getDelegateThaiName()
                        + "\nสู่การประชุมลงมติวาระ ครั้งที่ 1\nประจำวันที่ 22 มิถุนายน 2561");
            }
        });


    }

    @Override
    public void onBackPressed(){

    }

}
