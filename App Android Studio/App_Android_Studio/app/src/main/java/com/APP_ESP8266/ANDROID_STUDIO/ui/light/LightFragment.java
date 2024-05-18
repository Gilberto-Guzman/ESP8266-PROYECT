package com.APP_ESP8266.ANDROID_STUDIO.ui.light;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.APP_ESP8266.ANDROID_STUDIO.R;

import java.text.MessageFormat;

public class LightFragment extends Fragment {

    private LightViewModel lightViewModel;
    private TextView mPercentTextView;
    private SeekBar percentSeekBar;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Switch mlightSwitch;
    private int value;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        lightViewModel = new ViewModelProvider (this).get(LightViewModel.class);
        View root = inflater.inflate(R.layout.fragment_light, container, false);

        mPercentTextView= root.findViewById(R.id.percentTextView);
        percentSeekBar=(SeekBar)root.findViewById(R.id.percentSeekBar);
        mlightSwitch=(Switch)root.findViewById(R.id.lightSwitch);

        database    = FirebaseDatabase.getInstance();
        myRef       = database.getReference("light_bulb");

        lightViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {

            @Override
            public void onChanged(@Nullable Integer s) {

                percentSeekBar.setProgress(s);

                value=s;

                int percent= (int)(s / 10.24);
                mPercentTextView.setText(MessageFormat.format("{0}%", percent));

                if(s==0){
                    mlightSwitch.setChecked(false);
                    value=1024;
                }else{
                    mlightSwitch.setChecked(true);
                }
            }
        });

        mlightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myRef.setValue(value);
                }else{
                    myRef.setValue(0);
                }
            }
        });

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    myRef.setValue(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return root;
    }
}