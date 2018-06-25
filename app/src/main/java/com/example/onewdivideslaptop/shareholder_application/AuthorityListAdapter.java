package com.example.onewdivideslaptop.shareholder_application;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AuthorityListAdapter extends RecyclerView.Adapter<AuthorityListAdapter.ViewHolder>{
    private String[] authorities_en;
    private String[] authorities_th;
    private String[] authorities_id;
    private String[] authorities_availability;
    private int itemCount;
    private String voteType;
    private int agendaId;
    private Dialog dialog;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button button;
        public ViewHolder(View v){
            super(v);
            button = v.findViewById(R.id.button);
        }
    }

    public AuthorityListAdapter(Context context, String[] authorities_id, String[] authorities_en,
                                String[] authorities_th, String[] authorities_availability,
                                String voteType, int agendaId){
        this.authorities_en = authorities_en;
        this.authorities_th = authorities_th;
        this.authorities_id = authorities_id;
        this.authorities_availability = authorities_availability;
        this.voteType = voteType;
        this.agendaId = agendaId;
        this.context = context;
        this.dialog = new Dialog(context);
        this.itemCount = authorities_id.length+1;
        for(int i=0;i<authorities_id.length;++i){
            if(authorities_availability[i].equals("false")){
                this.itemCount -= 1;
                break;
            }
        }
    }

    public AuthorityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_authority_button,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public int getItemCount(){
        return this.itemCount;
    }

    private String getThaiWord(String voteType){
        if(voteType.equals(AppUtility.VOTE_AGREE)) return "เห็นด้วย";
        if(voteType.equals(AppUtility.VOTE_DISAGREE)) return "ไม่เห็นด้วย";
        if(voteType.equals(AppUtility.VOTE_NOCOMMENT)) return "ไม่ออกความเห็น";
        return voteType;
    }

    public void onBindViewHolder(ViewHolder holder,final int position){
        if(position== authorities_en.length){
            holder.button.setText("ทุกคน\nEveryone");
            holder.button.setBackgroundResource(R.drawable.vote_agree_button_background);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // show the confirmation of the vote for "ALL" authorities_en
                    dialog.setContentView(R.layout.vote_confirm_popup);

                    TextView message = (TextView) dialog.findViewById(R.id.message);
                    message.setText("ต้องการออกเสียง \""+getThaiWord(voteType)+"\" ในสิทธิ์ของทุกคนใช่หรือไม่?");

                    Button yesButton = (Button) dialog.findViewById(R.id.yes_button);
                    yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // show inform popup
                            View layout = LayoutInflater.from(context).inflate(R.layout.inform_popup,null,false);
                            ((TextView) layout.findViewById(R.id.message)).setText("ออกคะแนนเสียง \""+getThaiWord(voteType)+"\" ในสิทธิ์ของทุกคนเรียบร้อยแล้ว!");
                            Toast toast = new Toast(context);
                            toast.setView(layout);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            dialog.dismiss();
                            toast.show();
                        }
                    });

                    Button noButton = (Button) dialog.findViewById(R.id.no_button);
                    noButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
        }else {
            holder.button.setText(authorities_th[position]+"\n"+authorities_en[position]);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // show the confirmation of the vote for the authority clicked
                    dialog.setContentView(R.layout.vote_confirm_popup);

                    TextView message = (TextView) dialog.findViewById(R.id.message);
                    message.setText("ต้องการออกเสียง \""+getThaiWord(voteType)+"\" ในสิทธิ์ของ "+ authorities_th[position]+" ใช่หรือไม่?");

                    Button yesButton = (Button) dialog.findViewById(R.id.yes_button);
                    yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // show inform popup
                            View layout = LayoutInflater.from(context).inflate(R.layout.inform_popup,null,false);
                            ((TextView) layout.findViewById(R.id.message)).setText("ออกคะแนนเสียง \""+getThaiWord(voteType)+"\" ในสิทธิ์ของ "+ authorities_th[position]+" เรียบร้อยแล้ว!");
                            Toast toast = new Toast(context);
                            toast.setView(layout);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            dialog.dismiss();
                            toast.show();
                        }
                    });

                    Button noButton = (Button) dialog.findViewById(R.id.no_button);
                    noButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
        }

        if(authorities_availability[position].equals("false")){
            holder.button.setEnabled(false);
            holder.button.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

}
