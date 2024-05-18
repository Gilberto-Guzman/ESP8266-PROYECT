package com.APP_ESP8266.ANDROID_STUDIO.ui.temperature;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TemperatureViewModel extends ViewModel {

    private MutableLiveData<Float> mText;

    public TemperatureViewModel() {
        mText = new MutableLiveData<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("temperature");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Float value = dataSnapshot.getValue(Float.class);
                mText.setValue(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }

    public LiveData<Float> getText() {
        return mText;
    }
}