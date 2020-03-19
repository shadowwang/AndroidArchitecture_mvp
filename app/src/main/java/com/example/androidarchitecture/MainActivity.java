package com.example.androidarchitecture;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

/**
 * android架构demo——MVC
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化view
        initFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    private void initFragment() {
        DariesFragment dariesFragment = getDariesFragment();
        if (dariesFragment == null) {
            dariesFragment = new DariesFragment();
            dariesFragment.setPresenter(new DiariesPresenter(dariesFragment));
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), dariesFragment, R.id.content);
    }

    private DariesFragment getDariesFragment() {
        return (DariesFragment) getSupportFragmentManager().findFragmentById(R.id.content);
    }

}
