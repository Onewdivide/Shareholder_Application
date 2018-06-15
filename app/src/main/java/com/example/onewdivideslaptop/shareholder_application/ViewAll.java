package com.example.onewdivideslaptop.shareholder_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ViewAll extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] titles = {"An agenda item","An agenda item","An agenda item","An agenda item","An agenda item","An agenda item"};
        String[] descriptions = {
                "Description fir the agenda item mentioned above. This provide some detial...",
                "Description fir the agenda item mentioned above. This provide some detial...",
                "Description fir the agenda item mentioned above. This provide some detial...",
                "Description fir the agenda item mentioned above. This provide some detial...",
                "Description fir the agenda item mentioned above. This provide some detial...",
                "Description fir the agenda item mentioned above. This provide some detial...",
        };
        recyclerView.setAdapter(new AgendaItemListAdapter(ViewAll.this,titles,descriptions));
    }
}
