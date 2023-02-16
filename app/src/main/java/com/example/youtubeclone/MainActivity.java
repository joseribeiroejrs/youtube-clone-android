package com.example.youtubeclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.youtubeclone.adapters.ThumbnailAdapter;
import com.example.youtubeclone.api.YoutubeService;
import com.example.youtubeclone.helpers.RetrofitConfig;
import com.example.youtubeclone.helpers.YoutubeConfig;
import com.example.youtubeclone.listeners.RecyclerItemClickListener;
import com.example.youtubeclone.models.ThumbnailModel;
import com.example.youtubeclone.models.Youtube.YoutubeItem;
import com.example.youtubeclone.models.Youtube.YoutubeResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView thumbnailRecyclerView;
    SearchView searchView;
    Retrofit retrofit;
    ArrayList<ThumbnailModel> thumbnailList = new ArrayList<>();

    YoutubeResult results;
    ArrayList<YoutubeItem> videosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitToolbar();
        PopulateThumbnailList();
        // InitRecyclerView();
        InitRetrofit();
    }

    void InitToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("YouTube");
        toolbar.inflateMenu(R.menu.menu_main);
    }

    void InitSearchView() {
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("INFO", "Abrindo Search View");
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                getVideos("");
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getVideos(query);
                Log.i("QUERY YOUTUBE", "QUERY: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    void OpenVideoPlayer(int position) {
        YoutubeItem video = videosList.get(position);
        Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
        intent.putExtra(YoutubeConfig.VIDEO_ID_EXTRA_INTENT, video.id.videoId);
        startActivity(intent);
    }

    void InitRecyclerView() {
        thumbnailRecyclerView = findViewById(R.id.thumbnailRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        ThumbnailAdapter adapter = new ThumbnailAdapter(videosList);

        thumbnailRecyclerView.setLayoutManager(layoutManager);
        thumbnailRecyclerView.setAdapter(adapter);
        thumbnailRecyclerView.setHasFixedSize(true);

        thumbnailRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                thumbnailRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        OpenVideoPlayer(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));
    }

    void PopulateThumbnailList() {
        ThumbnailModel thumbnail = new ThumbnailModel("Primeiro video", "aslkdjklasd");
        thumbnailList.add(thumbnail);

        thumbnail = new ThumbnailModel("Outro video sinistro, com um titulo mais longo", "kkkk");
        thumbnailList.add(thumbnail);

        thumbnail = new ThumbnailModel("VIdeo sobre invasão do congresso, com um titulo mais longo", "kkkk");
        thumbnailList.add(thumbnail);

        thumbnail = new ThumbnailModel("Video estranho sobre um assunto qualquer", "kkkk");
        thumbnailList.add(thumbnail);
    }

    void InitRetrofit() {
        retrofit = RetrofitConfig.getRetrofit();
        getVideos("");
    }

    void getVideos(String pesquisa) {
        String q = pesquisa.replaceAll(" ", "+");
        YoutubeService youtubeService = retrofit.create(YoutubeService.class);
        youtubeService.getVideos(
                "snippet", "date", "20",
                YoutubeConfig.CHAVE_YOUTUBE_API, YoutubeConfig.CANAL_ID, q
        ).enqueue(new Callback<YoutubeResult>() {
            @Override
            public void onResponse(Call<YoutubeResult> call, Response<YoutubeResult> response) {
                results = response.body();
                videosList = results.items;
                InitRecyclerView();
            }

            @Override
            public void onFailure(Call<YoutubeResult> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.toolbar);
        searchView = (SearchView) searchItem.getActionView();
        InitSearchView();

        // MenuItem item = menu.findItem(R.id.search);

        return true; // super.onCreateOptionsMenu(menu);
    }
}