package com.example.eastcoastpawn.nytimessearch.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.eastcoastpawn.nytimessearch.Article;
import com.example.eastcoastpawn.nytimessearch.ArticleArrayAdapter;
import com.example.eastcoastpawn.nytimessearch.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {


    EditText etQuery;
    Button btnSearch;
    GridView gvResults;
    ArrayList<Article> articles;
    ArticleArrayAdapter adapter;
   // SearchSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViews();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_icon);
        getSupportActionBar().setDiplayUseLogoEnabled(true);
    }

    private void setupViews() {
        etQuery = (EditText) findViewById(R.id.etQuery);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        gvResults = (GridView) findViewById(R.id.gvResults);
        articles = new ArrayList<>();
        adapter = new ArticleArrayAdapter(this, articles);
        gvResults.setAdapter(adapter);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),ArticleActivity.class);
                Article article = articles.get(position);
               // Toast.makeText(SearchActivity.this, ""+article.getThumbNail(), Toast.LENGTH_SHORT).show();
                i.putExtra("article", article);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onArticleSearch(View view) {
        String query = etQuery.getText().toString();
        //Toast.makeText(this, "Searching for " + Query, Toast.LENGTH_LONG).show();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
        RequestParams params = new RequestParams();
        params.put("api-key", "f31815d224f14b34aa20d04579e9c9d9");
        params.put("q", query);
/*
        // if settings begin date has been set apply begin_date

        if (settings.getBeginDate() != null && settings.getBeginDate().getCalendar() != null) {
            params.put("begin_date", settings.formatBeginDate());
        }

       // if settings has sort oder apply sort
        if (settings.getSortOrder() != SearchSettings.Sort.oldest) {
            params.put("sort", settings.getSortOrder().name());
        }

        // if settings has sort oder apply sort
        if (settings.getSortOrder() != SearchSettings.Sort.newest) {
            params.put("sort", settings.getSortOrder().name());
        }

        // if settings filters contains at least one filter, apply filter
        if (settings.getFilters().size() > 0) {
            params.put("fq", settings.generateNewsDeskFiltersOR());
            */
            client.get(url, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.d("DEBUG", response.toString());
                    JSONArray articleJsonResults = null;
                    try {
                        articleJsonResults = response.getJSONObject("response").getJSONArray("docs");
                        adapter.addAll(Article.fromJSONArray(articleJsonResults));
                        //adapter.clear();
                        adapter.notifyDataSetChanged();
                        Log.d("DEBUG", articles.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }





