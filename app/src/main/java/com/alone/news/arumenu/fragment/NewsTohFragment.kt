package com.alone.news.arumenu.fragment

//import androidx.fragment.app.Fragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.alone.news.arumenu.R
import com.arc.news.utils.imageloader.ImageLoader

/**
 * package : com.alone.news.arumenu.fragment
 * anthor : 张贺岗
 * Date : 2019/2/18
 * Use : <类的用途>
 */
class NewsTohFragment : Fragment() {
    val TAG = "NewsTohFragment"
    @BindView(R.id.iv_card)
    lateinit var iv_card: ImageView
    @BindView(R.id.tv_card_title)
    lateinit var tv_card_title: TextView

    lateinit var pic: String
    lateinit var title: String
    lateinit var des: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = View.inflate(context, R.layout.view_card_toh, null)
        return view
    }

    var bind: Unbinder? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind = ButterKnife.bind(this, view)
        pic = arguments!!.get("pic") as String
        title = arguments!!.get("title") as String
        des = arguments!!.get("des") as String
        ImageLoader.getInstace().displayImage(pic, iv_card)
        tv_card_title.setText(title)

    }


    override fun onDestroy() {
        if (bind != null) bind!!.unbind()
        super.onDestroy()
    }
    val fragment:ImageDialogFrament = ImageDialogFrament()
    @OnClick(R.id.iv_card)
    fun onClick() {
        val bundle = Bundle()
        bundle.putString("des", des)
        bundle.putString("pic", pic)

//        if (fragment == null) {
//            fragment = ImageDialogFrament()
//        }
        if (!fragment .isAdded) {
            fragment.arguments = bundle
            fragment.show(fragmentManager, "dialog")
        }
    }
}