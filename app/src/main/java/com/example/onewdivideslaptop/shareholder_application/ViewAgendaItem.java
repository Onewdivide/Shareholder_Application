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
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.onewdivideslaptop.shareholder_application.AppUtility.VOTE_AGREE;
import static com.example.onewdivideslaptop.shareholder_application.AppUtility.VOTE_DISAGREE;
import static com.example.onewdivideslaptop.shareholder_application.AppUtility.VOTE_NOCOMMENT;
import static com.example.onewdivideslaptop.shareholder_application.Authority.selectAuthority;

public class ViewAgendaItem extends AppCompatActivity {
    TextView agenda_item_title;
    TextView agenda_item_description;
    ImageButton backButton;
    Button vote_bottom;
    Dialog vote_dialog,auth_dialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        AppUtility.currentAgengaPage = this;

        AppUtility.focus(this);
        Authority.getAuthorities(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_view_agenda_item);

                Intent intent = getIntent();
                String title = intent.getStringExtra("title");
                String description = intent.getStringExtra("description");

                agenda_item_title = (TextView) findViewById(R.id.agenda_item_title);
                agenda_item_title.setText(title);

                agenda_item_description = (TextView) findViewById(R.id.agenda_item_description);
                agenda_item_description.setText(description);

                backButton = (ImageButton) findViewById(R.id.back_button);
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ViewAll.currentViewAllPage.reload(new Runnable() {
                            @Override
                            public void run() {
                                ViewAgendaItem.this.finish();
                            }
                        });
                    }
                });

                vote_dialog = new Dialog(context);
                auth_dialog = new Dialog(context);

                vote_bottom = (Button) findViewById(R.id.vote_button);
                vote_bottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vote();
                    }
                });

                validateButton();
            }
        });

    }

    public void vote(){
        vote_dialog.setContentView(R.layout.vote_popup);

        Button agreeButton = (Button) vote_dialog.findViewById(R.id.vote_agree_button);
        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtility.active_vote_type = VOTE_AGREE;
                vote_dialog.dismiss();
                selectAuthority(auth_dialog);
            }
        });

        Button disagreeButton = (Button) vote_dialog.findViewById(R.id.vote_disagree_button);
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtility.active_vote_type = VOTE_DISAGREE;
                vote_dialog.dismiss();
                selectAuthority(auth_dialog);
            }
        });

        Button noCommentButton = (Button) vote_dialog.findViewById(R.id.vote_nocomment_button);
        noCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtility.active_vote_type = VOTE_NOCOMMENT;
                vote_dialog.dismiss();
                selectAuthority(auth_dialog);
            }
        });

        vote_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        vote_dialog.show();
    }

    public void validateButton(){
        if(!Authority.anyAuthAvailable()){
            vote_bottom.setEnabled(false);
            vote_bottom.setTextColor(Color.parseColor("#FFFFFF"));
            vote_bottom.setText("ใช้สิทธิ์หมดแล้ว");
        }
    }

}
