package com.xinjian.fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxFragment;
import com.xinjian.presenter.news.INewsArticle;
import com.xinjian.presenter.news.NewsArticlePresenter;

import java.util.List;

/**
 * Created by lxj on 2018/1/4 0004.
 */

public class CommonNewsFragment extends RxFragment implements INewsArticle.View{
    INewsArticle.Presenter mPresenter;

    @Override
    public void onSetAdapter(List<?> list) {

    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(INewsArticle.Presenter presenter) {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }

    @Override
    public void onLoadData() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NewsArticlePresenter(this);
        setPresenter(mPresenter);
    }
}
