package com.example.borcheltmerrychristmas.ui.countdown;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountDownViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CountDownViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}