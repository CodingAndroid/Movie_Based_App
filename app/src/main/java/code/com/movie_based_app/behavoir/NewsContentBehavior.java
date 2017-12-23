package code.com.movie_based_app.behavoir;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

import code.com.movie_based_app.BuildConfig;
import code.com.movie_based_app.MovieApp;
import code.com.movie_based_app.R;
import code.com.movie_based_app.helper.HeaderScrollingViewBehavior;

/**
 * Created by lihui1 on 2017/12/20.
 */
public class NewsContentBehavior extends HeaderScrollingViewBehavior{
    private static final String TAG = "NewsContentBehavior";

    public NewsContentBehavior() {
    }

    public NewsContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDependentViewChanged");
        }
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY((int) (-dependency.getTranslationY() / (getHeaderOffsetRange() * 1.0f) * getScrollRange(dependency)));

    }


    @Override
    protected View findFirstDependency(List<View> views) {
        for (int i = 0, z = views.size(); i < z; i++) {
            View view = views.get(i);
            if (isDependOn(view))
                return view;
        }
        return null;
    }

    /*列表页的向上偏移值*/
    @Override
    protected int getScrollRange(View v) {
        if (isDependOn(v)) {
            return Math.max(0, v.getMeasuredHeight() - getFinalHeight());
        } else {
            return super.getScrollRange(v);
        }
    }

    private int getHeaderOffsetRange() {
        return MovieApp.getAppContext().getResources().getDimensionPixelOffset(R.dimen.news_header_pager_offset);
    }

    private int getFinalHeight() {
        return MovieApp.getAppContext().getResources().getDimensionPixelOffset(R.dimen.news_tabs_height) + MovieApp.getAppContext().getResources().getDimensionPixelOffset(R.dimen.news_header_title_height);
    }

    /*依赖的判断*/
    private boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.id_news_header_pager;
    }
}
