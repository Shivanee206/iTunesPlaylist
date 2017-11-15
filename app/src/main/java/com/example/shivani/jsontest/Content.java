package com.example.shivani.jsontest;

/**
 * Created by Shivani on 11/14/2017.
 */

public class Content {
    String icon;
    String trackName;
    String artistName;
    String collectionName;
    String collectionPrice;
    String genre;
    String currency;
    String trackPrice;
    String time;
    String releaseDate;
    String streamable;
    String previewUrl;
    
    public Content(String icon,String trackName,String artistName,String collectionName,String genre,String collectionPrice,
                   String currency,String trackPrice,String time,String releaseDate,String streamable,String previewUrl ) {
        this.icon=icon;
        this.trackName=trackName;
        this.artistName=artistName;
        this.collectionName=collectionName;
        this.genre=genre;
        this.collectionPrice=collectionPrice;
        this.currency=currency;
        this.trackPrice=trackPrice;
        this.time=time;
        this.releaseDate=releaseDate;
        this.streamable=streamable;
        this.previewUrl=previewUrl;
        
    }

    public Content() {

    }

    public String getIcon() {
        return icon;
    }
    public String getTrackName() {
        return trackName;
    }
    public String getArtistName() {
        return artistName;
    }
    public String getCollectionName() {
        return collectionName;
    }
    public String getGenre() {return genre;}
    public String getCollectionPrice(){return collectionPrice;}
    public String getCurrency() {return currency;}
    public String getTrackPrice() {
        return trackPrice;
    }
    public String getTime() {
        return time;
    }
    public String getReleaseDate() {return releaseDate;}
    public String getStreamable() {
        return streamable;
    }
    public String getPreviewUrl() {return previewUrl;}

  

}


