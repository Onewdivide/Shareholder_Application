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
    private String[] mAgendaDescription;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView title,description;
        public ViewHolder(View v){
            super(v);
            view = v;
            title = v.findViewById(R.id.title);
            description = v.findViewById(R.id.description);
        }
    }

    public AgendaItemListAdapter(Context context,String[] titles,String[] descriptions){
        this.context = context;
        this.mAgendaTitle = titles;
        this.mAgendaDescription = descriptions;
    }

    public AgendaItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.title.setText(mAgendaTitle[position]+" :"+position);
        holder.description.setText(mAgendaDescription[position]);
        if(position%2==0){
            holder.view.setBackgroundColor(Color.parseColor("#992468AC"));
        }else{
            holder.view.setBackgroundColor(Color.parseColor("#99CA8642"));
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ViewAgendaItem.class);
                intent.putExtra("title",mAgendaTitle[position]);
                intent.putExtra("description",mAgendaDescription[position]);
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return mAgendaTitle.length;
    }
}
