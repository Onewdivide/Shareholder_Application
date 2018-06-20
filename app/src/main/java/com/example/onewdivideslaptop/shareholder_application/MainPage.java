package com.example.onewdivideslaptop.shareholder_application;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.onewdivideslaptop.shareholder_application.Authority.ALL_AGENDA;
import static com.example.onewdivideslaptop.shareholder_application.Authority.VOTE_AGREE;
import static com.example.onewdivideslaptop.shareholder_application.Authority.VOTE_DISAGREE;
import static com.example.onewdivideslaptop.shareholder_application.Authority.VOTE_NOCOMMENT;
import static com.example.onewdivideslaptop.shareholder_application.Authority.selectAuthority;

public class MainPage extends AppCompatActivity {

    Button viewAllBtn,voteAgreeButton,voteDisagreeButton,voteNoCommentButton;
    Dialog dialog;
    String delegateId = "DummyID";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main_page);

        viewAllBtn = (Button) findViewById(R.id.view_all_button);
        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainPage.this.startActivity(new Intent(MainPage.this,ViewAll.class));
            }
        });

        dialog = new Dialog(this);

        voteAgreeButton = (Button) findViewById(R.id.vote_agree_button);
        voteAgreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAuthority(context,dialog,delegateId,ALL_AGENDA,VOTE_AGREE);
            }
        });

        voteDisagreeButton = (Button) findViewById(R.id.vote_disagree_button);
        voteDisagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAuthority(context,dialog,delegateId,ALL_AGENDA,VOTE_DISAGREE);
            }
        });

        voteNoCommentButton = (Button) findViewById(R.id.vote_nocomment_button);
        voteNoCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAuthority(context,dialog,delegateId,ALL_AGENDA,VOTE_NOCOMMENT);
            }
        });

    }

    @Override
    public void onBackPressed(){

    }

}
