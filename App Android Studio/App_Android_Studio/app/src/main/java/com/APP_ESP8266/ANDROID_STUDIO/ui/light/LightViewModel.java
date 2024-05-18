package com.APP_ESP8266.ANDROID_STUDIO.ui.light;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LightViewModel extends ViewModel {

    private MutableLiveData<Integer> mText;
    DatabaseReference myRef;

    public LightViewModel() {
        mText = new MutableLiveData<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("light_bulb");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int value = dataSnapshot.getValue(int.class);
                mText.setValue(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}