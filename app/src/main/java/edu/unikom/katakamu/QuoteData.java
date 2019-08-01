package edu.unikom.katakamu;

import com.google.gson.annotations.SerializedName;


public class QuoteData {

    @SerializedName("id_quote")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("quote")
    private String quote;

    @SerializedName("date")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
