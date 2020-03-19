package com.example.androidarchitecture;

public class DiariesContract {

    public interface IPresenter extends BasePresenter {

        void loadDiaries();
        void addDiaries();
        void updateDiary(DiaryModel data);
        void setResult(int requestCode, int resultCode);
    }

    public interface IView extends BaseView<IPresenter> {
        void setListAdapter(DairesAdapter adapter);
//        void showInputDialog(String title, String content);
        void gotoEditPage(String id);
        void showMessage(String  msg);
        void goToEditPage();
    }
}
