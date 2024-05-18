package com.APP_ESP8266.ANDROID_STUDIO.ui.humidity;

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

public class HumidityFragment extends Fragment {

    private HumidityViewModel humidityViewModel;
    private CustomGauge mHumidityGauge;
    private TextView mHumidityTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        humidityViewModel =
                new ViewModelProvider(this).get(HumidityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_humidity, container, false);

        mHumidityGauge=root.findViewById(R.id.humidityGauge);
        mHumidityTextView=root.findViewById(R.id.humidityTextView);


        humidityViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {

            @Override
            public void onChanged(@Nullable Integer s) {
                mHumidityGauge.setValue(s);
                mHumidityTextView.setText(s+" %");
            }
        });

        return root;
    }
}