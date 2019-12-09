package com.example.bazaarapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activityTwo extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        addListenerOnButton();
    }

    public void addListenerOnButton()
    {

        final Context context = this;

        button = (Button) findViewById(R.id.buttonSignup1);

        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, home.class);
                startActivity(intent);

            }

        });

    }
}
