package com.example.androidarchitecture;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DiariesPresenter implements DiariesContract.IPresenter {

    private DariesFragment mView;
    private DiaresRepository mDiaresRepository;
    private DairesAdapter mAdapter;
    private DiaryModel mDiaryModel;

    public DiariesPresenter(DariesFragment view) {
        this.mView = view;
        this.mDiaresRepository = DiaresRepository.getInstance();
    }

    @Override
    public void start() {
        initAdapter();
        loadDiaries();
    }

    @Override
    public void destory() {}

    public void loadDiaries() {
        mDiaresRepository.getAll(new DataCallback<List<DiaryModel>>() {
            @Override
            public void onSuccess(List<DiaryModel> data) {
                if (data == null || data.isEmpty() || !mView.isAdded()) {
                    return;
                }

               mAdapter.updateDairy(data);
            }

            @Override
            public void onError() {
                if (!mView.isAdded()) {
                    return;
                }
                mView.showMessage(mView.getContext().getString(R.string.dialog_error));
            }
        });
    }

    @Override
    public void addDiaries() {
        if (!mView.isAdded()) {
            return;
        }

        mView.goToEditPage();
    }

    private void initAdapter() {
        mAdapter = new DairesAdapter(new ArrayList<DiaryModel>());
        mAdapter.onLongClickListener = new DairesAdapter.OnLongClickListener<DiaryModel>() {
            @Override
            public boolean onLongClick(View view, DiaryModel data) {
                updateDiary(data);
                return false;
            }
        };
        mView.setListAdapter(mAdapter);
    }

    public void updateDiary(DiaryModel data) {
        this.mDiaryModel = data;
        mView.gotoEditPage(data.getId());
    }

    @Override
    public void setResult(int requestCode, int resultCode) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        mView.showMessage("成功");
    }

    public void onInoutDialogClick(String content) {
        mDiaryModel.setContent(content);
        mDiaresRepository.update(mDiaryModel);
        loadDiaries();
    }
}
