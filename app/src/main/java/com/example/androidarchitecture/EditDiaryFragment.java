package com.example.androidarchitecture;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditDiaryFragment extends Fragment implements EditDiaryContract.EditView{

    private EditText mEditTitle, mEditContent;
    private EditDiaryContract.Presenter mEditDiaryPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.layout_edit_daries, container, false);
        mEditTitle = (EditText) view.findViewById(R.id.edit_title);
        mEditContent = (EditText) view.findViewById(R.id.edit_content);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void setTittle(String title) {
        mEditTitle.setText(title);
    }

    @Override
    public void setContent(String content) {
        mEditContent.setText(content);
    }

    @Override
    public void showError() {
        UIUtils.showMessage(getContext(), getContext().getString(R.string.dialog_error));
    }

    @Override
    public void showDiariesList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        mEditDiaryPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEditDiaryPresenter.destory();
    }

    @Override
    public void setPresenter(EditDiaryContract.Presenter presenter) {
        mEditDiaryPresenter = presenter;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_write, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                mEditDiaryPresenter.saveDiary(mEditTitle.getText().toString(), mEditContent.getText().toString());
                return true;
        }
        return false;
    }
}
