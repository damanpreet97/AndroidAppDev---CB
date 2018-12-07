package com.example.delllatitude.lec10hwpokedesk;

import com.google.gson.annotations.SerializedName;

public class Ability {

    private AbilityName ability;
    private String slot;

    @SerializedName("is_hidden")
    private String isHidden;


    public Ability() {
    }

    public AbilityName getAbility() {
        return ability;
    }

    public String getSlot() {
        return slot;
    }

    public String getIsHidden() {
        return isHidden;
    }
}
