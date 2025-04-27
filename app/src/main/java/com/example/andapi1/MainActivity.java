package com.example.andapi1;

import static com.example.andapi1.R.id.pagination_list;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private  ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        listView=(ListView)findViewById(pagination_list);
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        fastApi_client client= retrofit.create(fastApi_client.class);
        Call<List<FastAPIRepo>> call=client.repoforUser("fs_opensource");

        call.enqueue(new Callback<List<FastAPIRepo>>() {
            @Override
            public void onResponse(Call<List<FastAPIRepo>> call, Response<List<FastAPIRepo>> response) {
                Toast.makeText(MainActivity.this, "Hello world", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<FastAPIRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Link failure", Toast.LENGTH_LONG).show();
            }
        });

    }
}