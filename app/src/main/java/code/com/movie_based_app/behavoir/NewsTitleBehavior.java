package code.com.movie_based_app.behavoir;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import code.com.movie_based_app.BuildConfig;
import code.com.movie_based_app.MovieApp;
import code.com.movie_based_app.R;

/**
 * Created by lihui1 on 2017/12/20.
 */

public class NewsTitleBehavior extends CoordinatorLayout.Behavior<View>{
    private static final String TAG = "UcNewsTitleBehavior";

    public NewsTitleBehavior() {
    }

    public NewsTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        // FIXME: 16/7/27 不知道为啥在XML设置-45dip,解析出来的topMargin少了1个px,所以这里用代码设置一遍
        ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).topMargin = -getTitleHeight();
        parent.onLayoutChild(child, layoutDirection);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "layoutChild:top" + child.getTop() + ",height" + child.getHeight());
        }
        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        int headerOffsetRange = getHeaderOffsetRange();
        int titleOffsetRange = getTitleHeight();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "offsetChildAsNeeded:" + dependency.getTranslationY());
        }
        if (dependency.getTranslationY() == headerOffsetRange) {
            child.setTranslationY(titleOffsetRange);
        } else if (dependency.getTranslationY() == 0) {
            child.setTranslationY(0);
        } else {
            child.setTranslationY((int) (dependency.getTranslationY() / (headerOffsetRange * 1.0f) * titleOffsetRange));
        }

    }

    private int getHeaderOffsetRange() {
        return MovieApp.getAppContext().getResources().getDimensionPixelOffset(R.dimen.news_header_pager_offset);
    }

    private int getTitleHeight() {
        return MovieApp.getAppContext().getResources().getDimensionPixelOffset(R.dimen.news_header_title_height);
    }


    private boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.id_news_header_pager;
    }
}
