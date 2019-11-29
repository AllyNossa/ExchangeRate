package com.aknosova.urrencyapplication;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ExchangeRateAdapter {
    private static ExchangeRateAdapter singleton = null;
    private IExchangeRate iExchangeRate;

    private ExchangeRateAdapter() {
        iExchangeRate = createAdapter();
    }

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
                .baseUrl("https://www.cbr-xml-daily.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return adapter.create(IExchangeRate.class);
    }
}
