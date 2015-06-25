package com.ufo.judicature.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.RefreshListView;

/**
 * 宣传普法(视频)
 */
public class PropagandaVideoFragment extends BaseFragment {

    private RefreshListView lv_video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_propaganda_video, container, false);
        createView(v);
        initData();
        return v;
    }

    private void createView(View v) {
        lv_video = (RefreshListView) v.findViewById(R.id.lv_video);

    }

    private void initData() {

    }
}
