package com.six.cat.sixcat.module.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public interface IBaseView<T> {
    /**
     * 显示加载动画
     */
    void onShowLoading();

    /**
     * 隐藏加载
     */
    void onHideLoading();

    /**
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置 presenter
     */
    void setPresenter(T presenter);

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

    /**
     * 加载完毕
     */
    void onShowNoMore();

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
}
