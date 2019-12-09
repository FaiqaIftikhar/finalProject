package com.example.bazaarapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofit extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://webhook.site/632982a3-db67-4fe0-ab18-c213c255f3ab/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                String abc=" ";
                for (Post post : posts) {
                    String content = "";
                    content += "Heading : " + post.getHeading() + "\n";
                    content += "Content : " + post.getContent() + "\n\n";

                    Toast.makeText(getBaseContext(), content , Toast.LENGTH_LONG ).show();
                    //textViewResult.append(content);

                    abc=abc+content;
                }
                textViewResult.setText(abc);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
