package com.alone.news.arumenu

//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//import androidx.viewpager.widget.PagerAdapter
//import androidx.viewpager.widget.ViewPager
import android.icu.util.Calendar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.alone.news.arumenu.entity.NewsEntity
import com.alone.news.arumenu.fragment.NewsTohFragment
import com.alone.news.arumenu.model.NewsModel
import com.alone.news.arumenu.presenter.NewsPresenter
import com.alone.news.arumenu.utils.CalendarUtils
import com.alone.news.arumenu.view.NewsView
import com.arc.news.utils.util.LogUtils
import com.arc.news.utils.util.NetUtils
import com.google.gson.Gson
import me.jessyan.autosize.internal.CustomAdapt

/**
 * package : com.alone.news.arumenu
 * anthor : 张贺岗
 * Date : 2019/2/18
 * Use : <类的用途>
 */
class MainInterface : AppCompatActivity(), NewsView,CustomAdapt{
    override fun isBaseOnWidth(): Boolean {
        return true
    }

    override fun getSizeInDp(): Float {
        return 540F
    }

    lateinit var adapter: PagerAdapter
    lateinit var resultData: MutableList<NewsEntity.ResultBean>
    val TAG = "MainInterface"
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun sucess(data: NewsEntity?) {
        LogUtils.d(TAG, "=====suc:" + Gson().toJson(data))
        resultData = data!!.result
        adapter = NewsTohAdapter(supportFragmentManager, resultData)

        vp.adapter = adapter
        vp.currentItem = data.result.size * 10
    }

    override fun fail(error: Throwable?) {
        LogUtils.e(TAG, "=====fail_reason:" + error!!.message)
    }


    @BindView(R.id.vp_card)
    lateinit var vp: ViewPager

//    @OnClick(R.id.btn_dialog)
//    fun dialog(){
//        val bundle = Bundle()
//        bundle.putString("des", "今日新闻")
//        bundle.putString("pic", "http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201003/13/E5124946812.jpg")
//        val fragment = ImageDialogFrament()
//        fragment.arguments = bundle
//        fragment.show(supportFragmentManager, "dialog")
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interface)
        ButterKnife.bind(this)
        vpData()
    }

    override fun onDestroy() {
        ButterKnife.bind(this).unbind()
        newsPresenter!!.DetachView()
        super.onDestroy()
    }

    var newsPresenter: NewsPresenter? = null
    fun isNetInfo() {
        val connect = NetUtils.isNetworkAvailable()
        LogUtils.e(TAG, "====net=====$connect")

    }

    /**
     * 加载数据
     */
    private fun vpData() {
        isNetInfo()
        vp.pageMargin = 20
        vp.offscreenPageLimit = 3
        vp.setPageTransformer(false, LoopTransformer())
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

    class NewsTohAdapter(fm: FragmentManager, val data: MutableList<NewsEntity.ResultBean>) : FragmentPagerAdapter(fm) {
        val itemSize = 1000
        override fun getItem(current: Int): Fragment {

            val resultBean = data[current % data.size]
            val fragment = NewsTohFragment()
            val bundle = Bundle()
            val flag = (current % data.size + 1).toString() + "/" + data.size
            bundle.putString("title", flag + ":" + resultBean.title)
            bundle.putString("des", flag + ":" + resultBean.des)
            bundle.putString("pic", resultBean.pic)
            fragment.arguments = bundle
            return fragment

        }

        fun getCurrent(pos: Int): NewsEntity.ResultBean {

            return data[pos]
        }

        override fun getCount(): Int {
            return data.size * itemSize
        }

    }

    class LoopTransformer : ViewPager.PageTransformer {
        val MIN_SCALE = 0.8f
        override fun transformPage(view: View, position: Float) {
            var anInt: Int = 0
            if (position < -1) {
                anInt = -1
            } else if (position > 1) {
                anInt = 1
            }
            val tempScale = if (anInt < 0) 1 + anInt else 1 - anInt // [0,1]
            val scaleValue = MIN_SCALE + tempScale * 0.1f// [0,1]
            view.scaleX = scaleValue
            view.scaleY = scaleValue
        }

    }

}


