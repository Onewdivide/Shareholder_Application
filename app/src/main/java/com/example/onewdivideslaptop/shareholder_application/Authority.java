package com.example.onewdivideslaptop.shareholder_application;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class Authority {

    public static final String ALL_AGENDA = "ALL";
    public static final String VOTE_AGREE = "Agree";
    public static final String VOTE_DISAGREE = "Disgree";
    public static final String VOTE_NOCOMMENT = "NoComment";

    private static String[] authorities = {"Mr.Antimage", "Mr.Bunny", "Mr.Chicken", "Mr.D Luffy"};

    private static String selectMessage(String voteType){
        return "Please confirm voting " + voteType + " for this agenda item.\nYou're using the authority of:";
    }

    private static String[] getAuthorities(int delegateId){
        return authorities;
    }

    public static void selectAuthority(Context context, Dialog dialog, String agendaId, String voteType) {
        String[] authorities = getAuthorities(AppUtility.getDelegateId());
        dialog.setContentView(R.layout.select_authority_popup);
        TextView dialogMessage = dialog.findViewById(R.id.dialog_message);
        dialogMessage.setText(selectMessage(voteType));
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new AuthorityListAdapter(context,authorities,voteType,agendaId));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
