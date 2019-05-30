package com.alone.news.arumenu.ui.fragment

import android.icu.util.Calendar
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import butterknife.BindView
import com.alone.news.arumenu.MainInterface
import com.alone.news.arumenu.R
import com.alone.news.arumenu.base.BaseFragment
import com.alone.news.arumenu.entity.NewsEntity
import com.alone.news.arumenu.model.NewsModel
import com.alone.news.arumenu.presenter.NewsPresenter
import com.alone.news.arumenu.utils.CalendarUtils
import com.alone.news.arumenu.view.NewsView
import com.arc.news.utils.util.LogUtils
import com.arc.news.utils.util.NetUtils
import com.google.gson.Gson

/**
 * package : com.alone.news.arumenu.ui.fragment
 * anthor : 张贺岗
 * Date : 2019/4/24
 * Use : 首页
 */
class LeftFragment : BaseFragment(), NewsView {


    lateinit var adapter: PagerAdapter
    lateinit var resultData: MutableList<NewsEntity.ResultBean>
    val TAG = "MainInterface"
    var newsPresenter: NewsPresenter? = null


    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun sucess(data: NewsEntity?) {

        LogUtils.d(TAG, "=====suc:" + Gson().toJson(data))
        resultData = data!!.result
        adapter = MainInterface.NewsTohAdapter(fragmentManager!!, resultData)

        vp.adapter = adapter
        vp.currentItem = data.result.size * 10
    }

    override fun fail(error: Throwable?) {
        LogUtils.e(TAG, "=====fail_reason:" + error!!.message)
    }

    @BindView(R.id.vp_card)
    lateinit var vp: ViewPager

    override fun viewBindId(): Int {
        return R.layout.fragment_left
    }

    override fun viewInit(view: View) {
        isNetInfo()
        vp.pageMargin = 20
        vp.offscreenPageLimit = 3
        vp.setPageTransformer(false, MainInterface.LoopTransformer())
        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
                LogUtils.e(TAG, "onPageScrollStateChanged====net=====$p0")
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                LogUtils.e(TAG, "onPageScrolled====net=====$p0")
            }

            override fun onPageSelected(p0: Int) {
                LogUtils.e(TAG, "onPageSelected====net=====$p0")
            }

        })



        newsPresenter = NewsPresenter(NewsModel())
        newsPresenter!!.attachView(this)
        newsPresenter!!.getToh(CalendarUtils.getDate(Calendar.MONTH), CalendarUtils.getDate(Calendar.DATE))
    }

    fun isNetInfo(): Boolean {
        val connect = NetUtils.isNetworkAvailable()
        LogUtils.e(TAG, "====net=====$connect")
        return connect
    }

}