package com.example.youtubeclone.models.Youtube;


import java.util.ArrayList;

//        "publishedAt": "2022-10-19T15:25:28Z",
//                "channelId": "UCoRtOhYMUFmEXuNHLqFvxuA",
//                "title": "Stardew Valley em 3D - Jogo inspirado em Stardew Valley",
//                "description": "",
//                "thumbnails": {
//                "default": {
//                "url": "https://i.ytimg.com/vi/u-r93SJXzBk/default.jpg",
//                "width": 120,
//                "height": 90
//                },
//                "medium": {
//                "url": "https://i.ytimg.com/vi/u-r93SJXzBk/mqdefault.jpg",
//                "width": 320,
//                "height": 180
//                },
//                "high": {
//                "url": "https://i.ytimg.com/vi/u-r93SJXzBk/hqdefault.jpg",
//                "width": 480,
//                "height": 360
//                }
//                },
//                "channelTitle": "Sr Brigão",
//                "liveBroadcastContent": "none",
//                "publishTime": "2022-10-19T15:25:28Z"
public class YoutubeSnippet {
    public String publishedAt;
    public String channelId;
    public String title;
    public String description;
    public String channelTitle;
    public String publishTime;
    public YoutubeThumbnailTypes thumbnails;
}
