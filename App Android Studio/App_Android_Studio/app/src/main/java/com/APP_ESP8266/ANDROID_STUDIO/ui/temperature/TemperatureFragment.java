package com.APP_ESP8266.ANDROID_STUDIO.ui.temperature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.APP_ESP8266.ANDROID_STUDIO.R;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class TemperatureFragment extends Fragment {

    private TemperatureViewModel temperatureViewModel;
    private CustomGauge mTemperatureGauge;
    private TextView mTemperatureTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        temperatureViewModel =
                new ViewModelProvider (this).get(TemperatureViewModel.class);
        View root = inflater.inflate(R.layout.fragment_temperature, container, false);
        TextView textView = root.findViewById(R.id.titleTextView);

        mTemperatureGauge=root.findViewById(R.id.temperatureGauge);
        mTemperatureTextView=root.findViewById(R.id.temperatureTextView);


        temperatureViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Float>() {

            @Override
            public void onChanged(@Nullable Float s) {


                mTemperatureGauge.setValue(Math.round(s));
                mTemperatureTextView.setText(s+" C°");

            }
        });

        return root;
    }
}