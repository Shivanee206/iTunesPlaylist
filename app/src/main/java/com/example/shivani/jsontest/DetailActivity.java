package com.example.shivani.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        
        ImageView thumbNail = (ImageView)findViewById(R.id.imageView);
        TextView track = (TextView)findViewById(R.id.track);
        TextView artist = (TextView)findViewById(R.id.artist);
        TextView release = (TextView)findViewById(R.id.release);
        TextView time = (TextView)findViewById(R.id.time);
        TextView price=(TextView)findViewById(R.id.collPrice);
        TextView stream = (TextView)findViewById(R.id.stream);
        TextView preview=(TextView)findViewById(R.id.preview);
        final WebView webView=(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        
        ArrayList<HashMap<String, String>> arl = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("arraylist");
        
       HashMap<String,String> map = arl.get(0);
        for (final Map.Entry<String,String> entry : map.entrySet()) {
           
            if(entry.getKey().equals("img")){
                Glide.with(this).load(entry.getValue())
                        .into(thumbNail);}
            else if(entry.getKey().equals("trackN")){
                track.setText(entry.getValue()); 
            }
            else if(entry.getKey().equals("artist")){
                artist.setText(entry.getValue());

            }
            else if(entry.getKey().equals("time")){
                time.setText("Track time: "+ entry.getValue() +" ms");

            }
            else if(entry.getKey().equals("release")){
                
                release.setText("Release Date: "+entry.getValue());

            }else if(entry.getKey().equals("collectionP")){
                price.setText("Collection Price: "+entry.getValue());

            }
            else if(entry.getKey().equals("stream")){
                stream.setText("Can be Streamed: "+entry.getValue());
            }
            else if(entry.getKey().equals("url")){
                preview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       
                        webView.loadUrl(entry.getValue());
                        
                    }
                });
            }
        }
          
        }
        
    }

