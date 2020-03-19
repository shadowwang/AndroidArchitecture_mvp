package com.example.androidarchitecture;

import android.content.Context;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
