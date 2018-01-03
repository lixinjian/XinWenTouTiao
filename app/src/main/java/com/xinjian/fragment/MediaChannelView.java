package com.xinjian.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinjian.R;

/**
 * Created by lxj on 2017/12/20 0020.
 */

public class MediaChannelView extends Fragment {


    private static MediaChannelView instance = null;

    public static MediaChannelView getInstance() {
        if (instance == null) {
            instance = new MediaChannelView();
        }
        return instance;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_tab, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
