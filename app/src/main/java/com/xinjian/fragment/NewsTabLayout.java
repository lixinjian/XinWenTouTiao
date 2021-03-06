package com.xinjian.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xinjian.R;
import com.xinjian.adapter.BasePagerAdapter;
import com.xinjian.fragment.news.KeJiFragment;
import com.xinjian.fragment.news.NewsFragment;
import com.xinjian.utils.RxBus;
import com.xinjian.utils.SettingUtil;
import com.xinjian.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by lxj on 2017/12/20 0020.
 */

public class NewsTabLayout extends Fragment {

    private static final String TAG = "NewsTabLayout";
    private ViewPager mViewPager;
    private BasePagerAdapter mAdapter;
    private LinearLayout mLinearLayout;
    private Observable<Boolean> observable;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private Map<String, Fragment> map = new HashMap<>();

    private static NewsTabLayout instance = null;

    public static NewsTabLayout getInstance() {
        if (instance == null) {
            instance = new NewsTabLayout();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_tab, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        TabLayout tab_layout = view.findViewById(R.id.tab_layout_news);
        mViewPager = view.findViewById(R.id.view_pager_news);

        tab_layout.setupWithViewPager(mViewPager);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ImageView add_channel_iv = view.findViewById(R.id.add_channel_iv);
        add_channel_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), /*NewsChannelActivity*/NewsTabLayout.class));
                ToastUtils.showToastShort("添加类别");
            }
        });
        mLinearLayout = view.findViewById(R.id.header_layout);
        mLinearLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
    }

    private void initData() {
        initTabs();
        mAdapter = new BasePagerAdapter(getChildFragmentManager(), fragmentList, titleList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(15);

        observable = RxBus.getInstance().register(NewsTabLayout.TAG);
        observable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isRefresh) throws Exception {
                if (isRefresh) {
                    initTabs();
                    mAdapter.recreateItems(fragmentList, titleList);
                }
            }
        });
    }

    private void initTabs() {

        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
//        titleList.add("标题1");
        titleList.add("标题2");
//        fragmentList.add(new NewsFragment());
        fragmentList.add(new KeJiFragment());
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
