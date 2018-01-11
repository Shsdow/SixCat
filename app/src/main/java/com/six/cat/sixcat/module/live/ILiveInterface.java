package com.six.cat.sixcat.module.live;

import com.six.cat.sixcat.module.base.IBasePresenter;
import com.six.cat.sixcat.module.base.IBaseView;

/**
 * @author liguoying
 * @date 2018/1/9.
 */

public interface ILiveInterface {
    interface ILiveView extends IBaseView<ILivePresenter> {
        void loadData();
    }

    interface ILivePresenter extends IBasePresenter {
        /**
         * 请求数据
         */
        void doLoadData();

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter();

        void doShowNoMore();
    }
}
