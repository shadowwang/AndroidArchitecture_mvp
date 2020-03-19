package com.example.androidarchitecture;

public class EditDiaryContract {

    public interface EditView extends BaseView<Presenter> {
        void setTittle(String title);
        void setContent(String content);
        void showError();
        void showDiariesList();
    }

    public interface Presenter extends BasePresenter {
        void getDiaryById(String id);
        void saveDiary(String title, String content);
    }
}
