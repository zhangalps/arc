package com.alone.news.arumenu.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.alone.news.arumenu.R
import com.alone.news.arumenu.autoview.SlidingTextView
import com.arc.news.utils.imageloader.ImageLoader
import com.arc.news.utils.util.LogUtils

/**
 * package : com.alone.news.arumenu.fragment
 * anthor : 张贺岗
 * Date : 2019/3/4
 * Use : <类的用途>
 */
class ImageDialogFrament : DialogFragment() {
    val TAG = "ImageDialogFrament"
    lateinit var inflate: View
    lateinit var mContext: Context

    @BindView(R.id.iv_detail_card)
    lateinit var iv_detail: ImageView
    @BindView(R.id.tv_detail_title)
    lateinit var tv_detail: SlidingTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.e(TAG, "onCreate=========")
//        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context!!
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        LogUtils.e(TAG, "onCreateDialog=========")
        val builder: AlertDialog.Builder = AlertDialog.Builder(mContext, R.style.DialogTHeme)
        inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_imagedetail, null)
        ButterKnife.bind(this, inflate)
        initView()
        builder.setView(inflate)
        builder.setCancelable(true)
        return builder.create()
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        super.show(manager, tag)
        LogUtils.e(TAG, "show=========")

    }
    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        val pic = arguments!!.get("pic") as String
        val des = arguments!!.get("des") as String
        ImageLoader.getInstace().displayImage(pic, iv_detail, R.drawable.normal)
        tv_detail.setText(des+"--->广告时间，欢迎使用今日头条，现在插播一条广告，茶花芬芳，你值得拥有，走过路过不要错过，拒绝我们就是拒绝物美价廉")
    }





    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        LogUtils.e(TAG, "onDismiss=========")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.e(TAG, "onDetach=========")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e(TAG, "onDestroy=========")
    }

    override fun onDestroyView() {
        this.dismiss()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        LogUtils.e(TAG, "onResume=========")

    }

}