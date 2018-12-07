package com.example.delllatitude.lec10hwpokedesk;

import com.google.gson.annotations.SerializedName;

public class Stat {

    private String effort;
    private StatName stat;

    @SerializedName("base_stat")
    private String baseStat;

    public Stat() {
    }

    public String getEffort() {
        return effort;
    }

    public StatName getStat() {
        return stat;
    }

    public String getBaseStat() {
        return baseStat;
    }
}
