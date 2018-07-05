package com.example.onewdivideslaptop.shareholder_application;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.onewdivideslaptop.shareholder_application.responseModel.checkAuthorityForVoteAgendaResponse;
import com.example.onewdivideslaptop.shareholder_application.responseModel.checkAuthorityForVoteAllResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authority {

    public static String[] authorities_id;
    private static String[] authorities_th;
    private static String[] authorities_en;
    private static String[] authorities_availability;
    private static boolean need_update;
    public static Dialog active_dialog;

    static{
        need_update = true;
    }

    public static void requireUpdate(){
        Authority.need_update = true;
    }

    private static String selectMessage(String voteType){
        if(voteType.equals(AppUtility.VOTE_NOCOMMENT)){
            return "ต้องการ \"ไม่ออกความเห็น\"\nในสิทธิ์ของ";
        } else{
            String thai_word = "";
            if(voteType.equals(AppUtility.VOTE_AGREE)) thai_word="เห็นด้วย";
            else thai_word="ไม่เห็นด้วย";
            return "ต้องการออกเสียง \""+thai_word+"\"\nในสิทธิ์ของ";
        }
    }

    public static String[] getAuthIDs(){
        return authorities_id;
    }

    public static void getAuthorities(final Runnable callback) {
        if(need_update) {
            if (AppUtility.active_agenda == AppUtility.AGENDA_ALL) {
                Call<List<checkAuthorityForVoteAllResponse>> call = AppUtility.client.checkAuthorityForVoteAll(AppUtility.token,AppUtility.getDelegateId());
                call.enqueue(new Callback<List<checkAuthorityForVoteAllResponse>>() {
                    @Override
                    public void onResponse(Call<List<checkAuthorityForVoteAllResponse>> call, Response<List<checkAuthorityForVoteAllResponse>> response) {
                        authorities_id = new String[response.body().size()];
                        authorities_th = new String[response.body().size()];
                        authorities_en = new String[response.body().size()];
                        authorities_availability = new String[response.body().size()];
                        for (int i = 0; i < response.body().size(); ++i) {
                            checkAuthorityForVoteAllResponse anAuth = response.body().get(i);
                            authorities_id[i] = anAuth.getId();
                            authorities_th[i] = anAuth.getHolder_titleth() + anAuth.getHolder_nameth() + " " + anAuth.getHolder_surnameth();
                            authorities_en[i] = anAuth.getHolder_titleeng() + anAuth.getHolder_nameeng() + " " + anAuth.getHolder_surnameeng();
                            authorities_availability[i] = Integer.parseInt(anAuth.getCount())>0?"yes":"no";
                        }
                        need_update = false;
                        callback.run();
                    }

                    @Override
                    public void onFailure(Call<List<checkAuthorityForVoteAllResponse>> call, Throwable t) {
                        AppUtility.makeFailureToast();
                    }
                });
            } else {
                Call<List<checkAuthorityForVoteAgendaResponse>> call = AppUtility.client.checkAuthorityForVote(AppUtility.token,AppUtility.active_agenda, AppUtility.getDelegateId());
                call.enqueue(new Callback<List<checkAuthorityForVoteAgendaResponse>>() {
                    @Override
                    public void onResponse(Call<List<checkAuthorityForVoteAgendaResponse>> call, Response<List<checkAuthorityForVoteAgendaResponse>> response) {
                        authorities_id = new String[response.body().size()];
                        authorities_th = new String[response.body().size()];
                        authorities_en = new String[response.body().size()];
                        authorities_availability = new String[response.body().size()];
                        for (int i = 0; i < response.body().size(); ++i) {
                            checkAuthorityForVoteAgendaResponse anAuth = response.body().get(i);
                            authorities_id[i] = anAuth.getId();
                            authorities_th[i] = anAuth.getHolder_titleth() + anAuth.getHolder_nameth() + " " + anAuth.getHolder_surnameth();
                            authorities_en[i] = anAuth.getHolder_titleeng() + anAuth.getHolder_nameeng() + " " + anAuth.getHolder_surnameeng();
                            authorities_availability[i] = anAuth.getCheckauthorityforthisagenda();
                        }
                        need_update = false;
                        Log.e("GetAuth", TextUtils.join(" ",authorities_availability));
                        callback.run();
                    }

                    @Override
                    public void onFailure(Call<List<checkAuthorityForVoteAgendaResponse>> call, Throwable t) {
                        AppUtility.makeFailureToast();
                    }
                });
            }
        }else{
            callback.run();
        }
    }

    public static void selectAuthority(final Dialog dialog) {
        active_dialog = dialog;
        getAuthorities(new Runnable() {
            @Override
            public void run() {
                dialog.setContentView(R.layout.select_authority_popup);
                TextView dialogMessage = dialog.findViewById(R.id.dialog_message);
                dialogMessage.setText(selectMessage(AppUtility.active_vote_type));
                RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(AppUtility.getCurrentContext()));
                recyclerView.setAdapter(
                        new AuthorityListAdapter(
                                AppUtility.getCurrentContext(),
                                authorities_id,
                                authorities_en,
                                authorities_th,
                                authorities_availability,
                                AppUtility.active_vote_type,
                                AppUtility.active_agenda
                        )
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

    public static boolean anyAuthAvailable(){
        for(int i=0;i<authorities_availability.length;++i){
            if(authorities_availability[i].equals("yes")) return true;
        }
        return false;
    }
}
