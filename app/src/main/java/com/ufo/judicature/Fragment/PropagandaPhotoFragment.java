package com.ufo.judicature.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.RefreshListView;

/**
 * 宣传普法（图片）
 */
public class PropagandaPhotoFragment extends BaseFragment {

    private RefreshListView lv_photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_propaganda_photo, container, false);
        createView(v);
        initData();
        return v;
    }

    private void createView(View v) {
        lv_photo = (RefreshListView) v.findViewById(R.id.lv_photo);

    }

    private void initData() {

    }
}
