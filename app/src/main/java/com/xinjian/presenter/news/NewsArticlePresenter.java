package com.xinjian.presenter.news;

import com.xinjian.bean.news.MultiNewsArticleBean;
import com.xinjian.utils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxj on 2018/1/4 0004.
 */

public class NewsArticlePresenter implements INewsArticle.Presenter {

    private INewsArticle.View mView;
    private String category;

    public NewsArticlePresenter(INewsArticle.View view) {
        mView = view;
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {

    }

    @Override
    public void doLoadData(String... category) {
        try {
            if (this.category == null) {
                this.category = category[0];
            }
        } catch (Exception e) {
//            ErrorAction.print(e);
        }
//        Observable<MultiNewsArticleBean> ob1 = RetrofitFactory.getRetrofit().create(IMobileNewsApi.class)
//                .getNewsArticle(this.category);

//        ob1.subscribeOn(Schedulers.io())
//                .switchMap(new Function<MultiNewsArticleBean, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(MultiNewsArticleBean multiNewsArticleBean) throws Exception {
//                        return null;
//                    }
//                })
//                .filter()
//                .toList()
//                .map()
//                .compose()
//                .observeOn()
//                .subscribe();

    }

    @Override
    public void doLoadMoreData() {

    }

    @Override
    public void doShowNoMore() {

    }
}
