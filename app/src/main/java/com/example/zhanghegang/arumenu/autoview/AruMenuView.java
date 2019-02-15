package com.example.zhanghegang.arumenu.autoview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.example.zhanghegang.arumenu.R;

/**
 * package : com.example.zhanghegang.arumenu.view
 * anthor : 张贺岗
 * Date : 2018/12/18
 * Use : <类的用途>
 */
public class AruMenuView extends ViewGroup implements View.OnClickListener {

    public static final String TAG = "ARUMENUVIEW";

    private OnMenuClickListener clickListener;
    private int mRandius;
    private final int POSTION_LEFT_TOP = 0;
    private final int POSTION_LEFT_BOTTOM = 1;
    private final int POSTION_RIGHT_TOP = 2;
    private final int POSTION_RIGHT_BOTTOM = 4;
    private int mPostion;
    private View cButton;
    private Status mCurrentStatus = Status.CLOSE;

    private enum Status {
        OPEN, CLOSE
    }

    private enum Postion {
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM
    }

    public AruMenuView(Context context) {
        this(context, null);
    }

    public AruMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AruMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //默认半径
        mRandius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());

        //获取配置
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AruMenuView, defStyleAttr, 0);

        mPostion = ta.getInt(R.styleable.AruMenuView_postion, POSTION_LEFT_TOP);
        mRandius = (int) ta.getDimension(R.styleable.AruMenuView_radius, mRandius);

        ta.recycle();
        Log.d(TAG, "postion=" + mPostion + " mRandius=" + mRandius);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            //测量Child
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            layoutCbutton();
            for (int i = 0; i < getChildCount() - 1; i++) {
                View child = getChildAt(i + 1);
                child.setVisibility(GONE);
                int cl = (int) (mRandius * Math.sin(Math.PI / 2 / (getChildCount() - 2) * i));
                int ct = (int) (mRandius * Math.cos(Math.PI / 2 / (getChildCount() - 2) * i));

                int cWidth = child.getMeasuredWidth();
                int cHeight = child.getMeasuredHeight();

                if (mPostion == POSTION_LEFT_BOTTOM || mPostion == POSTION_RIGHT_BOTTOM) {
                    ct = getMeasuredHeight() - cHeight - ct;
                }
                if (mPostion == POSTION_RIGHT_TOP || mPostion == POSTION_RIGHT_BOTTOM) {
                    cl = getMeasuredWidth() - cWidth - cl;
                }
                child.layout(cl, ct, cl + cWidth, ct + cHeight);

            }
        }
    }

    /**
     * 确定主button位置
     */
    private void layoutCbutton() {
        cButton = getChildAt(0);
        int l = 0;
        int t = 0;
        int width = cButton.getMeasuredWidth();
        int height = cButton.getMeasuredHeight();

        switch (mPostion) {
            case POSTION_LEFT_TOP:
                l = 0;
                t = 0;
                break;
            case POSTION_LEFT_BOTTOM:
                l = 0;
                t = getMeasuredHeight() - height;
                break;
            case POSTION_RIGHT_TOP:
                l = getMeasuredWidth() - width;
                t = 0;
                break;
            case POSTION_RIGHT_BOTTOM:
                l = getMeasuredWidth() - width;
                t = getMeasuredHeight() - height;
                break;
        }
        cButton.layout(l, t, l + width, t + height);
        cButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_button:
                roateButton(cButton, 0f, 360f, 300);
                toggleMenu(300);
                break;
        }
    }

    private void toggleMenu(int duration) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = getChildAt(i + 1);
            child.setVisibility(VISIBLE);
            int cl = (int) (mRandius * Math.sin(Math.PI / 2 / (getChildCount() - 2) * i));
            int ct = (int) (mRandius * Math.cos(Math.PI / 2 / (getChildCount() - 2) * i));

            int flagX = 1;
            int flagY = 1;

            if (mPostion == POSTION_LEFT_TOP || mPostion == POSTION_LEFT_BOTTOM) {
                flagX = -1;
            }
            if (mPostion == POSTION_LEFT_TOP || mPostion == POSTION_RIGHT_TOP) {
                flagY = -1;
            }
            AnimationSet animationSet = new AnimationSet(true);
            TranslateAnimation animation = null;

            if (mCurrentStatus == Status.CLOSE) {

                animation = new TranslateAnimation(cl * flagX, 0, ct * flagY, 0);
            } else {
                animation = new TranslateAnimation(0, cl * flagX, 0, ct * flagY);
            }
            animation.setFillAfter(true);
            animation.setDuration(duration);
            animation.setStartOffset((i * 100) / childCount);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    if (mCurrentStatus == Status.CLOSE){
                        child.setVisibility(GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            RotateAnimation rotateAnimation = new RotateAnimation(0,720,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(duration);
            rotateAnimation.setFillAfter(true);

            animationSet.addAnimation(rotateAnimation);
            animationSet.addAnimation(animation);

            child.startAnimation(animationSet);

        }
        changeStatus();
    }

    private void changeStatus() {
        mCurrentStatus = mCurrentStatus == Status.CLOSE ? Status.OPEN : Status.CLOSE;
        Log.e(TAG, "currentStatus==="+mCurrentStatus.toString());
    }

    private void roateButton(View cButton, float start, float end, int duration) {
        RotateAnimation animation = new RotateAnimation(start, end, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        cButton.startAnimation(animation);
    }

    public interface OnMenuClickListener {
        void onclick(int pos, String current);
    }

    public void setOnMenuClickLister(OnMenuClickListener onMenuClickLister) {
        this.clickListener = onMenuClickLister;
    }
}
