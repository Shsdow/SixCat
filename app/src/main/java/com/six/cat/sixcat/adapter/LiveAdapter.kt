package com.six.cat.sixcat.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.six.cat.sixcat.R
import com.six.cat.sixcat.bean.LiveBean

/**
 * @author liguoying
 * @date 2018/1/15.
 */
class LiveAdapter(data: List<LiveBean.SubjectsBean>?) : BaseQuickAdapter<LiveBean.SubjectsBean, BaseViewHolder>(R.layout.item_live, data) {
    override fun convert(helper: BaseViewHolder?, item: LiveBean.SubjectsBean?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}