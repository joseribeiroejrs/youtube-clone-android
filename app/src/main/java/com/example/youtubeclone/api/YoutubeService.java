package com.example.youtubeclone.api;

import com.example.youtubeclone.models.Youtube.YoutubeResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {
    @GET("search")
    Call<YoutubeResult> getVideos(
            @Query("part") String part,
            @Query("order") String order,
            @Query("maxResults") String maxResults,
            @Query("key") String key,
            @Query("channelId") String channelId,
            @Query("q") String q
    );
}
