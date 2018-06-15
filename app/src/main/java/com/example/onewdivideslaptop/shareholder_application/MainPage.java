package com.example.onewdivideslaptop.shareholder_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Button viewAllBtn = (Button) findViewById(R.id.view_all_button);
        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainPage.this.startActivity(new Intent(MainPage.this,ViewAll.class));
            }
        });

    }
}
