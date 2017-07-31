package com.example.eastcoastpawn.nytimessearch.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.eastcoastpawn.nytimessearch.Article;
import com.example.eastcoastpawn.nytimessearch.R;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

<<<<<<< HEAD
       Article article  = (Article) getIntent().getSerializableExtra("article");
=======
        Article article  = (Article) getIntent().getSerializableExtra("article");
>>>>>>> 2ca92a91c78c8925db43e91d21077d782e9fb733

        WebView webView = (WebView) findViewById(R.id.wvArticle);

        webView.setWebViewClient(new WebViewClient(){
                                     public boolean shouldOverrideUrlLoading(WebView view, String url){
                                         view.loadUrl(url);
                                         return true;
                                     }
                                 }
        );
        webView.loadUrl(article.getWebUrl());
    }
}

<<<<<<< HEAD

=======
>>>>>>> 2ca92a91c78c8925db43e91d21077d782e9fb733
