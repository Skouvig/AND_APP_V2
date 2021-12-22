package com.example.andappv2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public int count =0;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(" " + count);
    }

    public LiveData<String> getText() {
        return mText;
    }
}