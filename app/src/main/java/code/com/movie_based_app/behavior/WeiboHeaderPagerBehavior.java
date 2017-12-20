package code.com.movie_based_app.behavior;

import android.content.Context;
import android.support.design.BuildConfig;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import java.lang.ref.WeakReference;

import code.com.movie_based_app.MovieApp;

/**
 * Created by lihui1 on 2017/12/19.
 */

/**
 * 头部的实现
 */

public class WeiboHeaderPagerBehavior extends ViewOffsetBehavior{

    public static final int STATE_OPENED = 0;
    public static final int STATE_CLOSED = 1;
    public static final int DURATION_SHORT = 300;
    public static final int DURATION_LONG = 600;

    private int mCurState = STATE_OPENED;
    private OnPageStateListener mOnPageStateListener;

    private OverScroller mOverScroller;

    private WeakReference<CoordinatorLayout> mParent;
    private WeakReference<View> mChild;

    public void setOnPageStateListener(OnPageStateListener onPageStateListener){
        this.mOnPageStateListener = onPageStateListener;
    }

    public WeiboHeaderPagerBehavior() {
        init();
    }


    public WeiboHeaderPagerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mOverScroller = new OverScroller(MovieApp.getAppContext());
    }

    @Override
    protected void layoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        super.layoutChild(parent, child, layoutDirection);
        mParent = new WeakReference<CoordinatorLayout>(parent);
        mChild = new WeakReference<View>(child);
    }

    /**
     * 1.确保只拦截垂直方向上的滚动事件, 且当前状态是打开的并且还可以继续向上收缩的时候还会拦截
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        if (BuildConfig.DEBUG){
            Log.d("TAG", "nestedScrollAxes"+nestedScrollAxes);
        }
        boolean canScroll = canScroll(child, 0);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0 && canScroll && !isClosed(child);
    }

    /**
     * 需要处理Fling事件, 在页面没有完全关闭的时候, 消费所有fling事件
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        boolean consumed = !isClosed(child);
        Log.d("TAG", "consumed="+consumed);
        return consumed;
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.d("TAG", "velocityY="+velocityY);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    /**
     * 区分页面状态是open还是close状态是通过Header是否移除屏幕来区分的,
     * 即child.getTranslationY() == getHeaderOffsetRange()
     * @param child
     * @return
     */
    private boolean isClosed(View child){
        boolean isClosed = child.getTranslationY() == getHeaderOffsetRange();
        return isClosed;
    }

    public boolean isClosed(){
        return mCurState == STATE_CLOSED;
    }

    private void changeState(int newState){
        if (mCurState != newState){
            mCurState = newState;
            if (mCurState == STATE_OPENED){
                if (mOnPageStateListener != null){
                    mOnPageStateListener.onPageOpened();
                }
            } else {
                if (mOnPageStateListener != null){
                    mOnPageStateListener.onPageClosed();
                }
            }
        }
    }

    /**
     * 2.拦截之后, 需要在滑动之前消耗事件, 并且移动Header, 让其向上便宜
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.d("TAG", "dy="+dy);
        float halfOfDis = dy;
        if (!canScroll(child, halfOfDis)){
            child.setTranslationY(halfOfDis > 0 ? getHeaderOffsetRange() : 0);
        } else {
            child.setTranslationY(child.getTranslationY() - halfOfDis);
        }
        consumed[1] = dy;
    }



    protected boolean canScroll(View child, float pendingDy){
        int pendingTranslationY = (int) (child.getTranslationY() - pendingDy);
        int headerOffsetRange = getHeaderOffsetRange();
        if (pendingTranslationY >= headerOffsetRange && pendingTranslationY <= 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        boolean closed = isClosed();
        Log.d("TAG", "closed="+closed);
        if (ev.getAction() == MotionEvent.ACTION_UP && !closed){
            handleActionUp(parent, child);
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    private void handleActionUp(CoordinatorLayout parent, View child) {
        if (BuildConfig.DEBUG){
            Log.d("TAG", "handleActionUp:");
        }
        if (mFlingRunnable != null){
            child.removeCallbacks(mFlingRunnable);
            mFlingRunnable = null;
        }
        mFlingRunnable = new FlingRunnable(parent, child);
        if (child.getTranslationY() < getHeaderOffsetRange() / 6.0f){
            mFlingRunnable.scrollToClosed(DURATION_SHORT);
        } else {
            mFlingRunnable.scrollToOpen(DURATION_SHORT);
        }
    }

    private int getHeaderOffsetRange() {
        return -200;
    }

    private void onFlingFinished(CoordinatorLayout coordinatorLayout, View layout) {
        changeState(isClosed(layout) ? STATE_CLOSED : STATE_OPENED);
    }

    public void openPager() {
        openPager(DURATION_LONG);
    }

    /**
     * @param duration open animation duration
     */
    public void openPager(int duration) {
        View child = mChild.get();
        CoordinatorLayout parent = mParent.get();
        if (isClosed() && child != null) {
            if (mFlingRunnable != null) {
                child.removeCallbacks(mFlingRunnable);
                mFlingRunnable = null;
            }
            mFlingRunnable = new FlingRunnable(parent, child);
            mFlingRunnable.scrollToOpen(duration);
        }
    }

    public void closePager() {
        closePager(DURATION_LONG);
    }

    /**
     * @param duration close animation duration
     */
    public void closePager(int duration) {
        View child = mChild.get();
        CoordinatorLayout parent = mParent.get();
        if (!isClosed()) {
            if (mFlingRunnable != null) {
                child.removeCallbacks(mFlingRunnable);
                mFlingRunnable = null;
            }
            mFlingRunnable = new FlingRunnable(parent, child);
            mFlingRunnable.scrollToClosed(duration);
        }
    }

    private FlingRunnable mFlingRunnable;

    private class FlingRunnable implements Runnable{

        private final CoordinatorLayout mParent;
        private final View mLayout;

        FlingRunnable(CoordinatorLayout parent, View layout){
            mParent = parent;
            mLayout = layout;
        }

        public void scrollToClosed(int duration) {
            float curTranslationY = ViewCompat.getTranslationY(mLayout);
            float dy = getHeaderOffsetRange() - curTranslationY;
            if (BuildConfig.DEBUG) {
                Log.d("TAG", "scrollToClosed:offest:" + getHeaderOffsetRange());
                Log.d("TAG", "scrollToClosed: cur0:" + curTranslationY + ",end0:" + dy);
                Log.d("TAG", "scrollToClosed: cur:" + Math.round(curTranslationY) + ",end:" + Math
                        .round(dy));
                Log.d("TAG", "scrollToClosed: cur1:" + (int) (curTranslationY) + ",end:" + (int) dy);
            }
            mOverScroller.startScroll(0, Math.round(curTranslationY - 0.1f), 0, Math.round(dy +
                    0.1f), duration);
            start();
        }

        public void scrollToOpen(int duration) {
            float curTranslationY = ViewCompat.getTranslationY(mLayout);
            mOverScroller.startScroll(0, (int) curTranslationY, 0, (int) -curTranslationY,
                    duration);
            start();
        }

        private void start() {
            if (mOverScroller.computeScrollOffset()) {
                mFlingRunnable = new FlingRunnable(mParent, mLayout);
                ViewCompat.postOnAnimation(mLayout, mFlingRunnable);
            } else {
                onFlingFinished(mParent, mLayout);
            }
        }
        @Override
        public void run() {
            if (mLayout != null && mOverScroller != null) {
                if (mOverScroller.computeScrollOffset()) {
                    if (BuildConfig.DEBUG) {
                        Log.d("TAG", "run: " + mOverScroller.getCurrY());
                    }
                    ViewCompat.setTranslationY(mLayout, mOverScroller.getCurrY());
                    ViewCompat.postOnAnimation(mLayout, this);
                } else {
                    onFlingFinished(mParent, mLayout);
                }
            }
        }
    }

    public interface OnPageStateListener{
        /**
         * 回调
         */
        void onPageClosed();

        void onPageOpened();
    }
}
