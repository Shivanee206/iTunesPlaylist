package com.example.shivani.jsontest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Content> items;


    public CustomListAdapter(Activity activity, ArrayList<Content> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item, null);

        ImageView thumbNail = convertView.findViewById(R.id.image);
        TextView track = convertView.findViewById(R.id.trackName);
        TextView artist = convertView.findViewById(R.id.artistName);
        TextView collection = convertView.findViewById(R.id.collectionName);
        TextView genre = convertView.findViewById(R.id.genre);
        TextView currency = convertView.findViewById(R.id.currency);
        TextView price = convertView.findViewById(R.id.price);

        Content m = items.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Color.DKGRAY);
        requestOptions.error(Color.GRAY);

        Glide.with(activity).load(m.getIcon())
                .apply(requestOptions)
                .into(thumbNail);
        track.setText(m.getTrackName());
        artist.setText(m.getArtistName());
        collection.setText(m.getCollectionName());
        genre.setText(m.getGenre());
        currency.setText(m.getCurrency());
        price.setText(m.getTrackPrice());
        return convertView;
    }

}
