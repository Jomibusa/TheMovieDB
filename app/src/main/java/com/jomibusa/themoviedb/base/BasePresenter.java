package com.jomibusa.themoviedb.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BasePresenter {

    protected void BasePresenter(){
    }

    @CallSuper
    void onCreate(@Nullable final Bundle savedInstanceState){
    }

    @CallSuper
    void onResume(){
    }

    @CallSuper
    void onPause(){
    }

    @CallSuper
    void onSaveInstanceState(@NonNull final Bundle outState){
    }

    @CallSuper
    void onDestroy(){
    }

    @CallSuper
    void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data){
    }

    @CallSuper
    void onRequestPersmissionResult(final int resultCode, @NonNull final String[] permissions, @NonNull final int[] grandResults){
    }

}
