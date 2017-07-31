package com.example.eastcoastpawn.nytimessearch.activities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by East Coast Pawn on 7/29/2017.
 */

public class SearchSettings implements Serializable {
    SimpleDateFormat beginDate;

    public enum Sort
    {
        newest, oldest
    }
    Sort sortOrder;

    ArrayList<String> filters;

    public SearchSettings() {

        this.sortOrder = Sort.oldest;
        filters = new ArrayList<String>();

        this.sortOrder = Sort.newest;
        filters = new ArrayList<String>();
    }


    public Sort getSortOrder() {
        return sortOrder;
    }

    public SimpleDateFormat getBeginDate() {
        return beginDate;
    }

    public ArrayList<String> getFilters() {
        return filters;
    }

    public void setSortOrder(Sort sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setBeginDate(SimpleDateFormat beginDate) {
        this.beginDate = beginDate;
    }
    public void addFilter(String filter) {
        filters.add(filter);
    }


    public String generateNewsDeskFiltersOR() {

        String luceneSyntax = "news_desk:(";

        for(String filter : filters) {
            luceneSyntax = luceneSyntax.concat("\""+filter+"\" ");
        }

        luceneSyntax = luceneSyntax.concat(")");

        return luceneSyntax;
    }


    public String formatBeginDate() {
        return beginDate.format(beginDate.getCalendar().getTime());
    }
}



