package com.example.eastcoastpawn.nytimessearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
<<<<<<< HEAD
import java.text.SimpleDateFormat;
=======
>>>>>>> 2ca92a91c78c8925db43e91d21077d782e9fb733
import java.util.ArrayList;




/**
 * Created by East Coast Pawn on 7/25/2017.
 */

public class Article implements Serializable {

    String webUrl;

    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    String headline;
    String thumbNail;
<<<<<<< HEAD
    String source;

    public SimpleDateFormat getPubDate() {
        return pubDate;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    SimpleDateFormat pubDate;
    String newsDesk;

=======
>>>>>>> 2ca92a91c78c8925db43e91d21077d782e9fb733

    public Article(JSONObject jsonObject) {
        try {
            this.webUrl = jsonObject.getString("web_url");
            this.headline = jsonObject.getJSONObject("headline").getString("main");
<<<<<<< HEAD
            this.newsDesk = jsonObject.getString("news_desk");
=======
>>>>>>> 2ca92a91c78c8925db43e91d21077d782e9fb733

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");
            if (multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                this.thumbNail = "https://www.nytimes.com/"+multimediaJson.getString("url");
            } else {
                this.thumbNail = "";
            }
        } catch (JSONException e) {

        }
    }
        public static ArrayList<Article> fromJSONArray(JSONArray array) {
        ArrayList<Article> results = new ArrayList<>();
     for (int x = 0; x < array.length(); x++)
        {
            try {
                results.add(new Article(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return results;
    }

    }
