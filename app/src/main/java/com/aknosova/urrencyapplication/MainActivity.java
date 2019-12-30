package com.aknosova.urrencyapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aknosova.urrencyapplication.rest.ExchangeRateModel;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("NullableProblems")
public class MainActivity extends AppCompatActivity {
    private TextView rateDollarDataTextTomorrow;
    private TextView rateDollarDataTextToday;
    private TextView rateUeroDataTextTomorrow;
    private TextView rateEuroDataTextToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        updateData();
    }

    private void updateData() {
        ExchangeRateAdapter.getSingletone().getiExchangeRate().loadData().enqueue(new Callback<ExchangeRateModel>() {
            @Override
            public void onResponse(Call<ExchangeRateModel> call, Response<ExchangeRateModel> response) {
                if (response.body() == null) {
                    rateDollarDataTextTomorrow.setText(R.string.error_server);
                }

                if (response.body() != null) {
                    renderRate(response.body());
                }
            }

            @Override
            public void onFailure(Call<ExchangeRateModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.internet_error,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void renderRate(ExchangeRateModel exchangeRateRequest) {
        setDollarRateTomorrow(exchangeRateRequest.valute.usd.value);
        setDollarRateToday(exchangeRateRequest.valute.usd.previous);
        setEuroRateToday(exchangeRateRequest.valute.euro.previous);
        setEuroRateTomorrow(exchangeRateRequest.valute.euro.value);
    }

    private void setDollarRateTomorrow(float value) {
        String currentValue = String.format(Locale.getDefault(), "%.2f", value);
        rateDollarDataTextTomorrow.setText(currentValue);
    }

    private void setDollarRateToday(float value) {
        String currentValue = String.format(Locale.getDefault(), "%.2f", value);
        rateDollarDataTextToday.setText(currentValue);
    }

    private void setEuroRateToday(float value) {
        String currentValue = String.format(Locale.getDefault(), "%.2f", value);
        rateEuroDataTextToday.setText(currentValue);
    }

    private void setEuroRateTomorrow(float value) {
        String currentValue = String.format(Locale.getDefault(), "%.2f", value);
        rateUeroDataTextTomorrow.setText(currentValue);
    }

    private void initViews() {
        rateDollarDataTextTomorrow = findViewById(R.id.data_text_tommorow_dollar);
        rateUeroDataTextTomorrow = findViewById(R.id.data_text_tommorow_euro);
        rateDollarDataTextToday = findViewById(R.id.data_text_today_dollar);
        rateEuroDataTextToday = findViewById(R.id.data_text_today_euro);
    }
}
