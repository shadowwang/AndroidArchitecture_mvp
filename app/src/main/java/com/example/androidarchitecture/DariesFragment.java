package com.example.androidarchitecture;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DariesFragment extends Fragment implements DiariesContract.IView {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_daries, container, false);
//        if (this.mDariesController != null) {
//            this.mDariesController.setDiariesList((RecyclerView) view.findViewById(R.id.rv_daries_list));
//        }
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_daries_list);
        setDiariesList(mRecyclerView);
        setHasOptionsMenu(true);
        return view;
    }

    public void setDiariesList(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (this.mDariesController != null) {
//            this.mDariesController.loadDiaries();
//        }

        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destory();
        }
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
                mPresenter.addDiaries();
                return true;
        }
        return false;
    }

    private DiariesContract.IPresenter mPresenter;

    @Override
    public void setListAdapter(DairesAdapter adapter) {
        this.mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void gotoEditPage(String id) {
        EditDiaryActivity.goToEdit(getActivity(), id);
    }

    @Override
    public void showMessage(String msg) {
        UIUtils.showMessage(getContext(), msg);
    }

    @Override
    public void goToEditPage() {
        EditDiaryActivity.goToEdit(getActivity(), "");
    }

    @Override
    public void setPresenter(DiariesContract.IPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mPresenter.setResult(requestCode, resultCode);
    }
}
