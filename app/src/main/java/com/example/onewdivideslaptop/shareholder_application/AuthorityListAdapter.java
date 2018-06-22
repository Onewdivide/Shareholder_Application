package com.example.onewdivideslaptop.shareholder_application;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class AuthorityListAdapter extends RecyclerView.Adapter<AuthorityListAdapter.ViewHolder>{
    private String[] authorities; // to be reconstructed to (title+names(th,eng) and id) >> the commented line below
    //private String[] shareHolderIds,shareHolderThaiNames,shareHolderEngNames;
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

    public AuthorityListAdapter(Context context, String[] authorities,String voteType,int agendaId){
        this.authorities = authorities;
        this.voteType = voteType;
        this.agendaId = agendaId;
        this.context = context;
        this.dialog = new Dialog(context);
    }

    public AuthorityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_authority_button,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public int getItemCount(){
        if(authorities.length==1)
            return 1;
        return authorities.length + 1;
    }

    public void onBindViewHolder(ViewHolder holder,final int position){
        if(position==authorities.length){
            holder.button.setText("ทุกคน");
            holder.button.setBackgroundResource(R.drawable.vote_agree_button_background);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // show the confirmation of the vote for "ALL" authorities
                    dialog.setContentView(R.layout.vote_confirm_popup);

                    TextView message = (TextView) dialog.findViewById(R.id.message);
                    message.setText("ต้องการออกเสียง "+voteType+" ในสิทธิ์ของทุกคนใช่หรือไม่?");

                    Button yesButton = (Button) dialog.findViewById(R.id.yes_button);
                    yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // show inform popup
                            View layout = LayoutInflater.from(context).inflate(R.layout.inform_popup,null,false);
                            ((TextView) layout.findViewById(R.id.message)).setText("ออกคะแนนเสียง "+voteType+" ในสิทธิ์ของทุกคนเรียบร้อยแล้ว!");
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
            holder.button.setText(authorities[position]);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // show the confirmation of the vote for the authority clicked
                    dialog.setContentView(R.layout.vote_confirm_popup);

                    TextView message = (TextView) dialog.findViewById(R.id.message);
                    message.setText("ต้องการออกเสียง "+voteType+" ในสิทธิ์ของ"+authorities[position]+"ใช่หรือไม่?");

                    Button yesButton = (Button) dialog.findViewById(R.id.yes_button);
                    yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // show inform popup
                            View layout = LayoutInflater.from(context).inflate(R.layout.inform_popup,null,false);
                            ((TextView) layout.findViewById(R.id.message)).setText("ออกคะแนนเสียง "+voteType+" ในสิทธิ์ของ"+authorities[position]+"เรียบร้อยแล้ว!");
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

        if(/* the authority was already used */  false){
            holder.button.setEnabled(false);
            holder.button.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

}
