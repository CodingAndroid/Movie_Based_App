package code.com.movie_based_app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

import code.com.movie_based_app.R;
import code.com.movie_based_app.views.SlidingTabLayout;

/**
 * Created by lihui1 on 2017/12/14.
 */

public class FindFragment extends Fragment{

    private ViewPager mViewPager;

    private SlidingTabLayout mSlidingTabLayout;

    private TextView mContent;



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
        mSlidingTabLayout.setViewPager(mViewPager);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.view_pager_margin));
        return mView;
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
            mContent = new TextView(getActivity());
            mContent.setText("hello");
            container.addView(mContent);
            return mContent;
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
