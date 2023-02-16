package com.example.youtubeclone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeclone.R;
import com.example.youtubeclone.models.ThumbnailModel;
import com.example.youtubeclone.models.Youtube.YoutubeItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ThumbnailViewHolder> {

    ArrayList<YoutubeItem> thumbnailList = new ArrayList<>();

    public ThumbnailAdapter(ArrayList<YoutubeItem> thumbnailList) {
        this.thumbnailList = thumbnailList;
    }

    @NonNull
    @Override
    public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thumbnail_layout, parent, false);
        return new ThumbnailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailViewHolder holder, int position) {
        YoutubeItem thumbnail = thumbnailList.get(position);
        holder.setThumbnailTitleTextView(thumbnail.snippet.title);
        holder.setThumbnailImageView(thumbnail.snippet.thumbnails.high.url);
    }

    @Override
    public int getItemCount() {
        return thumbnailList.size();
    }

    public class ThumbnailViewHolder extends RecyclerView.ViewHolder {
        private TextView thumbnailTitleTextView;
        private ImageView thumbnailImageView;

        public ThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailTitleTextView = itemView.findViewById(R.id.thumbnailTextView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
        }

        public TextView getThumbnailTitleTextView() {
            return thumbnailTitleTextView;
        }

        public void setThumbnailTitleTextView(String newTitle) {
            this.thumbnailTitleTextView.setText(newTitle);
        }

        public ImageView getThumbnailImageView() {
            return thumbnailImageView;
        }

        public void setThumbnailImageView(String URL) {
            Picasso.get().load(URL).into(this.getThumbnailImageView());
        }
    }
}
