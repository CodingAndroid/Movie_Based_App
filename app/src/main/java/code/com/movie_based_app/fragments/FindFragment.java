package code.com.movie_based_app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import code.com.movie_based_app.BuildConfig;
import code.com.movie_based_app.R;
import code.com.movie_based_app.adapter.NewsAdapter;
import code.com.movie_based_app.behavoir.NewsHeaderPagerBehavior;
import code.com.movie_based_app.views.SlidingTabLayout;

/**
 * Created by lihui1 on 2017/12/14.
 */

public class FindFragment extends Fragment implements NewsHeaderPagerBehavior.OnPagerStateListener{

    private static final String TAG = "FindFragment";

    private ViewPager mViewPager;

    private SlidingTabLayout mSlidingTabLayout;

    private RecyclerView mReContent;

    private List<String> data;

    private NewsHeaderPagerBehavior mPagerBehavior;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_find, container, false);
        mViewPager = (ViewPager) mView.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new MyViewPagerAdapter());
        mSlidingTabLayout = (SlidingTabLayout) mView.findViewById(R.id.sliding_tabs);
        int color = getResources().getColor(R.color.main_red);
        mSlidingTabLayout.setSelectedIndicatorColors(color);
        mSlidingTabLayout.setLayoutMode(1);
        mSlidingTabLayout.setViewPager(mViewPager);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.view_pager_margin));
        mPagerBehavior = (NewsHeaderPagerBehavior) ((CoordinatorLayout.LayoutParams)mView.findViewById(R.id.id_news_header_pager).getLayoutParams()).getBehavior();
        mPagerBehavior.setPagerStateListener(this);
        return mView;
    }

    private List<String> getData(){
        data = new ArrayList<>();
        for (int i = 0;i<20;i++){
            data.add("java"+i);
        }
        return data;
    }

    @Override
    public void onPagerClosed() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onPagerClosed: ");
        }
        Snackbar.make(mViewPager, "onPagerClosed", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPagerOpened() {
        Log.d(TAG, "onPagerOpened: ");
        Snackbar.make(mViewPager, "pager opened", Snackbar.LENGTH_SHORT).show();
    }


    class MyViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "选项卡"+position;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mReContent = new RecyclerView(getActivity());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mReContent.setLayoutManager(layoutManager);
            mReContent.setAdapter(new NewsAdapter(getData()));
            container.addView(mReContent);
            return mReContent;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    private <T extends View> List<T> getList(T...object){
        List<T> conViews = new ArrayList<>();
        int length = object.length;
        for (int i = 0; i<length; i++){
            conViews.add(object[i]);
        }
        return conViews;
    }

    private <K,V> Map<K,V> getMap(K key, V value){
        Map<K,V> itemMap = new HashMap<K,V>();
        itemMap.put(key, value);
        return itemMap;
    }

    private <T extends View> void configItemView(int index, List<T> contentViews, Map<TextView, ImageView>... views){
        int length = views.length, status;
        TextView key = null;
        ImageView value = null;
        Set set;
        for (int k = 0; k<length; k++){
            set = views[k].keySet();
            for (Object aSet : set){
                key = (TextView) aSet;
                value = views[k].get(key);
            }
            if (k==index){
                key.setTextColor(getResources().getColor(R.color.main_red));
                status = View.VISIBLE;
            } else {
                key.setTextColor(getResources().getColor(R.color.main_blue));
                status = View.GONE;
            }
            value.setVisibility(status);
            contentViews.get(k).setVisibility(status);
        }
    }
    /*
    configItemView(0,
     getList(container, container,container,container),
     getMap(first, iconone), getMap(first, iconone), getMap(first, iconone), getMap(first, iconone));
   **/
}
