package com.example.onewdivideslaptop.shareholder_application;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AgendaItemListAdapter extends RecyclerView.Adapter<AgendaItemListAdapter.ViewHolder> {
    private String[] mAgendaTitle;
    private String[] mAgendaFullTitle;
    private String[] mAgendaDescription;
    private boolean[] mAgendaCompleted;
    private int[] mAgendaID;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView title;
        public LinearLayout layout;
        public ImageView mark;
        public ViewHolder(View v){
            super(v);
            view = v;
            title = v.findViewById(R.id.title);
            layout = v.findViewById(R.id.layout);
            mark = v.findViewById(R.id.mark);
        }
    }

    public AgendaItemListAdapter(int[] ids,String[] titles,String[] full_titles,String[] descriptions,boolean[] completed){
        this.context = AppUtility.getCurrentContext();
        this.mAgendaID = ids;
        this.mAgendaTitle = titles;
        this.mAgendaDescription = descriptions;
        this.mAgendaFullTitle = full_titles;
        this.mAgendaCompleted = completed;
    }

    public AgendaItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.title.setText(mAgendaTitle[position]);
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
        if(mAgendaCompleted[position]){
            holder.layout.setBackgroundResource(R.drawable.agenda_item_frame_completed);
            holder.mark.setVisibility(View.VISIBLE);
        }
    }

    public int getItemCount(){
        return mAgendaTitle.length;
    }
}
