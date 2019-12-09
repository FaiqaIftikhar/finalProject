package com.example.bazaarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class subscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
    }
    public void backAcivity(View view){
        view.getContext().startActivity(new Intent(view.getContext(), home.class));
    }

    public void gottoinfo(View view){
        view.getContext().startActivity(new Intent(view.getContext(), info.class));
    }
}
