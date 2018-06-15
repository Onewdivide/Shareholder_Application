package com.example.onewdivideslaptop.shareholder_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewAgendaItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_agenda_item);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getString
    }
}
