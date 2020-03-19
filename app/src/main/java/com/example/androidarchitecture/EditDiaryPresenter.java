package com.example.androidarchitecture;

import android.text.TextUtils;

public class EditDiaryPresenter implements EditDiaryContract.Presenter {
    private String mDiaryId;
    private EditDiaryFragment editDiaryFragment;
    private DataSource<DiaryModel> modelDataSource;

    public EditDiaryPresenter(String id, EditDiaryFragment editDiaryFragment) {
        this.mDiaryId = id;
        this.editDiaryFragment = editDiaryFragment;
        this.modelDataSource = DiaresRepository.getInstance();
    }

    @Override
    public void getDiaryById(String id) {
        if (isAdd() || TextUtils.isEmpty(id)) {
            return;
        }

        modelDataSource.get(id, new DataCallback<DiaryModel>() {

            @Override
            public void onSuccess(DiaryModel data) {

                editDiaryFragment.setContent(data.getContent());
                editDiaryFragment.setTittle(data.getTitle());
            }

            @Override
            public void onError() {
                editDiaryFragment.showError();
            }
        });
    }

    @Override
    public void saveDiary(String title, String content) {
        if (isAdd()) {
            addDiary(title, content);
        } else {
            updateDiary(mDiaryId, title, content);
        }
    }

    private void addDiary(String title, String content) {
        DiaryModel diaryModel = new DiaryModel(title, content);
        modelDataSource.update(diaryModel);
        editDiaryFragment.showDiariesList();
    }

    private void updateDiary(String id, String title, String content) {
        DiaryModel diaryModel = new DiaryModel(id, title, content);
        modelDataSource.update(diaryModel);
        editDiaryFragment.showDiariesList();
    }

    @Override
    public void start() {
        getDiaryById(mDiaryId);
    }


    @Override
    public void destory() {

    }

    private boolean isAdd() {
        return TextUtils.isEmpty(mDiaryId);
    }
}
