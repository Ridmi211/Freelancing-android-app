package com.example.sparkle.ui.jobApplyCleaner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ApplyJobViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ApplyJobViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}