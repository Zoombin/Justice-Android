package com.ufo.judicature.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.R;

/**
 * 在线考试
 */
public class ExamFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exam, container, false);
        createView(v);
        initData();
        return v;
    }

    private void createView(View v) {

    }

    private void initData() {

    }
}