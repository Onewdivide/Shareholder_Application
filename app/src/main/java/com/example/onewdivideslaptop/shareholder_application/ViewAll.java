package com.example.onewdivideslaptop.shareholder_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

public class ViewAll extends AppCompatActivity {

    public static ViewAll currentViewAllPage;
    private static boolean refresh_flag;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        AppUtility.focus(this);
        currentViewAllPage = this;
        refresh_flag = false;

        ((ImageButton) findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtility.focus(AppUtility.mainPage);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int[] agenda_ids = AppUtility.agenda_ids;
        String[] agenda_titles = AppUtility.agenda_titles;
        String[] agenda_full_titles = AppUtility.agenda_full_titles;
        String[] agenda_descriptions = AppUtility.agenda_descriptions;
        boolean[] agenda_completed = AppUtility.agenda_completed;
        recyclerView.setAdapter(new AgendaItemListAdapter(agenda_ids,agenda_titles,agenda_full_titles,agenda_descriptions,agenda_completed));
    }

    public static void requireUpdate(){
        refresh_flag = true;
    }

    public void reload(){
        if(refresh_flag){
            startActivity(new Intent(AppUtility.mainPage,ViewAll.class));
            finish();
        }
    }

}
