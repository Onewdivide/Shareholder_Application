package com.example.onewdivideslaptop.shareholder_application;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AgendaItemListAdapter extends RecyclerView.Adapter<AgendaItemListAdapter.ViewHolder> {
    private String[] mAgendaTitle;
    private String[] mAgendaFullTitle;
    private String[] mAgendaDescription;
    private int[] mAgendaID;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView title;
        public ViewHolder(View v){
            super(v);
            view = v;
            title = v.findViewById(R.id.title);
        }
    }

    public AgendaItemListAdapter(int[] ids,String[] titles,String[] full_titles,String[] descriptions){
        this.context = AppUtility.getCurrentContext();
        this.mAgendaID = ids;
        this.mAgendaTitle = titles;
        this.mAgendaDescription = descriptions;
        this.mAgendaFullTitle = full_titles;
    }

    public AgendaItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.title.setText(mAgendaTitle[position]);
//        if(position%2==0){
//            holder.view.setBackgroundColor(Color.parseColor("#80F0D1"));
//        }else{
//            holder.view.setBackgroundColor(Color.parseColor("#BAF080"));
//        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ViewAgendaItem.class);
                intent.putExtra("title",mAgendaFullTitle[position]);
                intent.putExtra("description",mAgendaDescription[position]);
                AppUtility.active_agenda = mAgendaID[position];
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return mAgendaTitle.length;
    }
}
