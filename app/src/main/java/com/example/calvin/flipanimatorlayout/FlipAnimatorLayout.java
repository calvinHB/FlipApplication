package com.example.calvin.flipanimatorlayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by calvin on 17/2/21.
 */

public class FlipAnimatorLayout extends FrameLayout {

    private View firstView;
    private View secondView;
    private int status = 0;//view正反面 0:正面  1:反面
    private int animStatus = 0;//动画的状态 0:停止 1:进行中

    private ObjectAnimator animatorFirstGone,animatorSecondShow;
    private ObjectAnimator animatorFirstShow,animatorSecondGone;

    public FlipAnimatorLayout(Context context) {
        super(context);
        init();
    }

    public FlipAnimatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlipAnimatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //初始化
    private void init() {
        initTurnRoundAnim();
        initTurnBackAnim();
    }

    /**
     * 初始化翻转回到正面动画
     */
    private void initTurnBackAnim() {
        animatorSecondGone = ObjectAnimator.ofFloat(secondView, "rotationX", 0, -90);
        animatorFirstShow = ObjectAnimator.ofFloat(firstView, "rotationX", 90, 0);

        animatorSecondGone.setDuration(200);
        animatorFirstShow.setDuration(200);
        animatorSecondGone.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setAnimStatus(1);
                setStatus(0);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                secondView.setVisibility(View.GONE);
                ViewCompat.setRotationX(secondView, 0);
                firstView.setVisibility(View.VISIBLE);
                animatorFirstShow.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorFirstShow.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setAnimStatus(0);
                setStatus(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 初始化翻转到背面动画
     */
    private void initTurnRoundAnim() {
        animatorFirstGone = ObjectAnimator.ofFloat(firstView, "rotationX", 0, 90);
        animatorSecondShow = ObjectAnimator.ofFloat(secondView, "rotationX", -90, 0);

        animatorFirstGone.setDuration(200);
        animatorSecondShow.setDuration(200);
        animatorFirstGone.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                setAnimStatus(1);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                firstView.setVisibility(View.INVISIBLE);//为了获取该view的高度
//                oldView.setVisibility(View.GONE);
                ViewCompat.setRotationX(firstView, 0);
                secondView.setVisibility(View.VISIBLE);
                animatorSecondShow.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                setAnimStatus(0);
                setStatus(1);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSecondShow.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setAnimStatus(0);
                setStatus(1);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                setAnimStatus(0);
                setStatus(1);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 翻转到背面动画
     */
    public void turnRoundView() {

        if (firstView == null || secondView == null)
            return;

        if (animatorFirstGone.isRunning() || animatorSecondShow.isRunning())
            cancelShowAnim();
        else
            animatorFirstGone.start();
    }

    /**
     * 翻转回正面动画
     */
    public void turnBackView(){
        if (firstView == null || secondView == null)
            return;
        if (animatorFirstShow.isRunning() || animatorSecondGone.isRunning())
            cancelShowAnim();
        else
            animatorSecondGone.start();
    }

    /**
     * 取消翻转出来背面的动画
     */
    private void cancelShowAnim() {
        if (animatorFirstGone.isRunning())
            animatorFirstGone.cancel();
        if (animatorSecondShow.isRunning())
            animatorSecondShow.cancel();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("tag", "onmeasure="+getWidth()+", "+getHeight()+", "+getMeasuredWidth()+", "+getMeasuredHeight());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i("tag", "onfinish");
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAnimStatus() {
        return animStatus;
    }

    public void setAnimStatus(int animStatus) {
        this.animStatus = animStatus;
    }
}
