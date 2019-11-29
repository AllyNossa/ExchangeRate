package com.aknosova.urrencyapplication;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchangeRateAdapter {
    private static ExchangeRateAdapter singleton = null;
    private IExchangeRate iExchangeRate;

    static ExchangeRateAdapter getSingletone() {
        if (singleton == null) {
            singleton = new ExchangeRateAdapter();
        }
        return singleton;
    }

    IExchangeRate getiExchangeRate() {
        return iExchangeRate;
    }

    private IExchangeRate createAdapter() {

        OkHttpClient clientOkHttp = new OkHttpClient.Builder().build();
        Retrofit adapter = new Retrofit.Builder()
                .client(clientOkHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return adapter.create(IExchangeRate.class);
    }
}
