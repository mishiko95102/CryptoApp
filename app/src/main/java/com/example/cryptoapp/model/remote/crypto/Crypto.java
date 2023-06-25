package com.example.cryptoapp.model.remote.crypto;

import com.google.gson.annotations.SerializedName;



public class Crypto {

    public int rank;


    @SerializedName("id")
    public String idn;

    public String name;
    public String symbol;
    public String description;
    public String started_at;
    public String logo;
    public Links links;
    public String development_status;

    // კლასია შექმილი Api-დან ინფორმაციის წამოსაღებად

    public Crypto(int rank, String idn, String name, String symbol, String description, String logo, String started_at, Links links, String development_status) {
        this.rank = rank;
        this.idn = idn;
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.started_at = started_at;
        this.logo = logo;
        this.links = links;
        this.development_status = development_status;
    }


}
