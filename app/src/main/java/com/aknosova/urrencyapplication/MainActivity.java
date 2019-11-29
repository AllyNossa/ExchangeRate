package com.aknosova.urrencyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.aknosova.urrencyapplication.rest.ExchangeRateModel;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView rateDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rateDataText = findViewById(R.id.data_text);
        updateData();




    }

    private void updateData() {
        ExchangeRateAdapter.getSingletone().getiExchangeRate().loadData().enqueue(new Callback<ExchangeRateModel>() {
            @Override
            public void onResponse(Call<ExchangeRateModel> call, Response<ExchangeRateModel> response) {
                if (response.body() == null) {
                    rateDataText.setText(R.string.error_server);
                }

                renderRate(response.body());
            }

            @Override
            public void onFailure(Call<ExchangeRateModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.internet_error,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void renderRate(ExchangeRateModel exchangeRateRequest) {
        setDollarRate(exchangeRateRequest.valute.value);
    }

    private void setDollarRate(float value) {
        String currentValue = String.format(Locale.getDefault(), "%.0f", value);
        rateDataText.setText(currentValue);

    }
}
