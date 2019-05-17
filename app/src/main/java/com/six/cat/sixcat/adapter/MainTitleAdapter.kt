package com.six.cat.sixcat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

import com.six.cat.sixcat.R

/**
 * @author liguoying
 * @date 2017/12/25.
 */

class MainTitleAdapter(private val context: Context) : PagerAdapter(), View.OnClickListener {
    private val images = intArrayOf(R.mipmap.speech_title, R.mipmap.branch_title, R.mipmap.activity_title, R.mipmap.record_title)
    private var mOnItemClickLitener: OnItemClickLitener? = null

    interface OnItemClickLitener {
        fun onLeftClick(view: View, i: Int)

        fun onRightClick(view: View, i: Int)
    }

    fun setOnItemClickLitener(mOnItemClickLitener: OnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener
    }

    override fun getCount(): Int {
        return this.images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(this.context).inflate(R.layout.main_title_item, null)
        (view.findViewById<View>(R.id.main_title_image) as ImageView).setImageResource(this.images[position])
        val left = view.findViewById<View>(R.id.main_title_left)
        val right = view.findViewById<View>(R.id.main_title_right)
        left.setOnClickListener(this)
        left.tag = Integer.valueOf(position)
        right.setOnClickListener(this)
        right.tag = Integer.valueOf(position)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun onClick(v: View) {
        if (this.mOnItemClickLitener != null) {
            when (v.id) {
                R.id.main_title_left -> {
                    this.mOnItemClickLitener!!.onLeftClick(v, (v.tag as Int).toInt())
                    return
                }
                R.id.main_title_right -> {
                    this.mOnItemClickLitener!!.onRightClick(v, (v.tag as Int).toInt())
                    return
                }
            }
        }
    }
}

