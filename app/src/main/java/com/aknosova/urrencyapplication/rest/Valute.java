package com.aknosova.urrencyapplication.rest;

import com.google.gson.annotations.SerializedName;

public class Valute {
    @SerializedName("Value") public float value;
    @SerializedName("Previous") public float previous;
}
