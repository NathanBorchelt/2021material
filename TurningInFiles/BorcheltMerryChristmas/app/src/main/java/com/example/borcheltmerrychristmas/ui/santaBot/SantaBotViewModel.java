package com.example.borcheltmerrychristmas.ui.santaBot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SantaBotViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SantaBotViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}