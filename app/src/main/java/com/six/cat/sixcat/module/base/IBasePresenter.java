package com.six.cat.sixcat.module.base;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();
}
