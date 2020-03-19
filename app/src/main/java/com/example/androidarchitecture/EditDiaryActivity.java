package com.example.androidarchitecture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

public class EditDiaryActivity extends BaseActivity {

    public static final String KEY_DIARY_ID = "keu_diary_id";

    private String mDiaryId;

    public static void goToEdit(Activity context, String diaryId) {
        Intent intent = new Intent(context, EditDiaryActivity.class);
        intent.putExtra(KEY_DIARY_ID, diaryId);
        context.startActivityForResult(intent, 0x01);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDiaryId = getIntent().getStringExtra(KEY_DIARY_ID);
        setToolBarTitle(TextUtils.isEmpty(mDiaryId));
        initFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit;
    }

    private void setToolBarTitle(boolean isAdd) {
        getSupportActionBar().setTitle(isAdd ? R.string.title_add_edit : R.string.title_edit_edit);
    }

    private void initFragment() {
        EditDiaryFragment editDiaryFragment = getEditDiaryFragment();
        if (editDiaryFragment == null) {
            editDiaryFragment = initEditDiaryFragment(mDiaryId);
        }
        editDiaryFragment.setPresenter(new EditDiaryPresenter(mDiaryId, editDiaryFragment));
    }

    private EditDiaryFragment initEditDiaryFragment(String diaryId) {
        EditDiaryFragment editDiaryFragment = new EditDiaryFragment();
        if (!TextUtils.isEmpty(diaryId)) {
            Bundle data = new Bundle();
            data.putString(KEY_DIARY_ID, diaryId);
            editDiaryFragment.setArguments(data);
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), editDiaryFragment, R.id.content);
        return editDiaryFragment;
    }

    private EditDiaryFragment getEditDiaryFragment() {
        return (EditDiaryFragment) getSupportFragmentManager().findFragmentById(R.id.content);
    }
}
