package com.example.eastcoastpawn.nytimessearch;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by East Coast Pawn on 7/25/2017.
 */

public class ArticleArrayAdapter extends ArrayAdapter<Article> {
    public ArticleArrayAdapter(Context context, List<Article> articles) {
        super(context, android.R.layout.simple_list_item_1, articles);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Article article = this.getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_article_result, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivImage);
        //clear out recycled
        imageView.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        tvTitle.setText(article.getHeadline());

        String thumbnail = article.getThumbNail().toString();
        if (TextUtils.isEmpty(thumbnail)){
            //Do nothing
        }else{
            Picasso.with(getContext()).load(thumbnail).placeholder(R.drawable.place1).into(imageView);
        }

        // Return the completed view to render on screen
        return convertView;

    }
}
