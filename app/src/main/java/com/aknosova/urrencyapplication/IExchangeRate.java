package com.aknosova.urrencyapplication;

import com.aknosova.urrencyapplication.rest.ExchangeRateModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IExchangeRate {
    @GET("daily_json.js")
    Call<ExchangeRateModel>loadData();
}
