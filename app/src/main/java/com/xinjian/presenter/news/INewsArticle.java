package com.xinjian.presenter.news;

import com.xinjian.base.IBaseListView;
import com.xinjian.base.IBasePresenter;

/**
 * Created by lxj on 2018/1/4 0004.
 */

public interface INewsArticle {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
//        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }


}
