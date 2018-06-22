package com.example.onewdivideslaptop.shareholder_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

public class ViewAll extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        AppUtility.focus(this);

        ((ImageButton) findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        recyclerView.setAdapter(new AgendaItemListAdapter(agenda_ids,agenda_titles,agenda_full_titles,agenda_descriptions));
    }

}
