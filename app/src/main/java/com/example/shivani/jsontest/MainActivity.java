package com.example.shivani.jsontest;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TAG ="JSON";
    String data="";
    TextView output;
    ArrayList<Content> musicList = new ArrayList<>();
    ListView listView;
    CustomListAdapter adapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);
         new Playlist().execute();

    }
    private class Playlist extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Fetching music list.....", Toast.LENGTH_LONG).show();}

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String url = "https://itunes.apple.com/search?term=Michael+jackson";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray contacts = jsonObj.getJSONArray("results");
                    
                    for (int i = 0; i < contacts.length(); i++) {
                        
                        JSONObject jsonObject = contacts.getJSONObject(i);
                        
                        String artistName = jsonObject.optString("artistName","");
                        String collectionName = jsonObject.optString("collectionName","");
                        String genreName = jsonObject.optString("primaryGenreName","");
                        String trackCensoredName = jsonObject.optString("trackCensoredName","");
                        String trackPrice = (jsonObject.optString("trackPrice"));
                        String currency = jsonObject.optString("currency","");
                        String image = jsonObject.optString("artworkUrl100","");
                        String time=jsonObject.optString("trackTimeMillis","");
                        String releaseDate=jsonObject.optString("releaseDate","");
                        String isStreamable=jsonObject.optString("isStreamable","");
                        String previewUrl=jsonObject.optString("previewUrl","");
                        String collectionPrice=jsonObject.optString("collectionPrice","");
                        
                        musicList.add(new Content(image, trackCensoredName, artistName, collectionName, genreName,collectionPrice,
                                currency,trackPrice,time,releaseDate,isStreamable,previewUrl));

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "error: " + e.getMessage());
                                   }

            } else {
                Log.e(TAG, "Can't populate list right now...!");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Can't populate list right now...!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            adapter = new CustomListAdapter(MainActivity.this, musicList);
            listView.setAdapter(adapter);
            
            listView.setOnItemClickListener(new ListView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    Content content=musicList.get(position);
                    String url=  content.getPreviewUrl();
                    String img=content.getIcon();
                    String release=content.getReleaseDate();
                    String artist=content.getArtistName();
                    String collectionP=content.getCollectionPrice();
                    String trackN=content.getTrackName();
                    String stream=content.getStreamable();
                    String time=content.getTime();
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("img",img);
                    hm.put("trackN",trackN);
                    hm.put("artist",artist);
                    hm.put("time",time);
                    hm.put("release",release);
                    hm.put("collectionP",collectionP);
                    hm.put("stream",stream);
                    hm.put("url",url);
                    ArrayList<HashMap<String, String>> arl = new ArrayList<HashMap<String, String>>();
                    arl.add(hm);
                    intent.putExtra("arraylist", arl);
                    startActivityForResult(intent, 500);
                }
            });
        }
    }
        
}
   