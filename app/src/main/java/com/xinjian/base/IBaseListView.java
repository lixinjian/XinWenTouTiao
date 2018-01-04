package com.xinjian.base;

import java.util.List;

/**
 * Created by Meiji on 2017/7/5.
 */

public interface IBaseListView<T> extends IBaseView<T> {

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

    /**
     * 加载完毕
     */
    void onShowNoMore();
}
