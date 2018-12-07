package com.example.delllatitude.lec10hwpokedesk;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

public class ApiResponse {
    private String name, weight, height;
    private Sprites sprites;
    private ArrayList<Form> forms;
    private ArrayList<Ability> abilities;
    private ArrayList<Stat> stats;

    public ArrayList<Form> getForms() {
        return forms;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public ArrayList<Stat> getStats() {
        return stats;
    }

    @SerializedName("id")
    private String rank;

    @SerializedName("base_experience")
    private String baseExperience;


    public Sprites getSprites() {
        return sprites;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getRank() {
        return rank;
    }

    public String getBaseExperience() {
        return baseExperience;
    }
}
