package com.gauri.btcontrol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class MoreAppActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private LinearLayout mShoppingMasterAppBtn, mYEBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_app);

        mToolbar = findViewById(R.id.more_app_tb);
        mToolbar.setTitle("More Apps");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mShoppingMasterAppBtn = findViewById(R.id.shopping_master_btn);
        mYEBtn = findViewById(R.id.ye_app_btn);

        mShoppingMasterAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.mytechguruji.shoppingmaster"));
                startActivity(rateIntent);
            }
        });
        mYEBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.yourselectronics.gauridev.yourselectronics"));
                startActivity(rateIntent);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
