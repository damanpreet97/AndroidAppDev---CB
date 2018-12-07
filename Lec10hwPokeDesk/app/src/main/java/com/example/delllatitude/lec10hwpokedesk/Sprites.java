package com.example.delllatitude.lec10hwpokedesk;

import com.google.gson.annotations.SerializedName;

public class Sprites {
    @SerializedName("back_female")
    private String backFemale;

    @SerializedName("back_shiny_female")
    private String backShinyFemale;

    @SerializedName("back_default")
    private String backDefault;

    @SerializedName("front_female")
    private String frontFemale;

    @SerializedName("front_shiny_female")
    private String frontShinyFemale;

    @SerializedName("back_shiny")
    private String backShiny;

    @SerializedName("front_default")
    private String frontDefault;

    @SerializedName("front_shiny")
    private String frontShiny;

    public String getBackFemale() {
        return backFemale;
    }

    public String getBackShinyFemale() {
        return backShinyFemale;
    }

    public String getBackDefault() {
        return backDefault;
    }

    public String getFrontFemale() {
        return frontFemale;
    }

    public String getFrontShinyFemale() {
        return frontShinyFemale;
    }

    public String getBackShiny() {
        return backShiny;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public String getFrontShiny() {
        return frontShiny;
    }
}
