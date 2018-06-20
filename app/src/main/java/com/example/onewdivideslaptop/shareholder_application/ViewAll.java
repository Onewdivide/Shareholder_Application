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

        ((ImageButton) findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] titles = {"An agenda item","An agenda item","An agenda item","An agenda item","An agenda item","An agenda item"};
        String[] descriptions = {
                "Description for an agenda item. We drift through the heaven hatenai omoi Filled with the love from up above...",
                "Description for an agenda item. We drift through the heaven hatenai omoi Filled with the love from up above...",
                "Description for an agenda item. We drift through the heaven hatenai omoi Filled with the love from up above...",
                "Description for an agenda item. We drift through the heaven hatenai omoi Filled with the love from up above...",
                "Description for an agenda item. We drift through the heaven hatenai omoi Filled with the love from up above...",
                "Description for an agenda item. We drift through the heaven hatenai omoi Filled with the love from up above...",
        };
        recyclerView.setAdapter(new AgendaItemListAdapter(ViewAll.this,titles,descriptions));
    }
}
