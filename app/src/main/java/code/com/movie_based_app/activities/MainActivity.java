package code.com.movie_based_app.activities;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import code.com.movie_based_app.R;
import code.com.movie_based_app.ui.MainTab;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setShowDividers(0);
        initTabs();
        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);
    }

    private void initTabs(){
        MainTab[] tabs = MainTab.values();
        for (MainTab tab : tabs){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getNameRes()));
            View indicator = LayoutInflater.from(this).inflate(R.layout.view_tab_main_indicator, null);
            ImageView icon = (ImageView) indicator.findViewById(R.id.tab_icon);
            icon.setImageResource(tab.getIconRes());
            TextView title = (TextView) indicator.findViewById(R.id.tab_titile);
            title.setText(getString(tab.getNameRes()));
            tabSpec.setIndicator(indicator);
            tabSpec.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            mTabHost.addTab(tabSpec, tab.getClazz(), null);
        }
    }
    @Override
    public void onTabChanged(String tabId) {
        final int tabCount = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < tabCount; i++) {
            View tab = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                tab.findViewById(R.id.tab_icon).setSelected(true);
                tab.findViewById(R.id.tab_titile).setSelected(true);
                changeActionBarTitle(i);
            } else {
                tab.findViewById(R.id.tab_icon).setSelected(false);
                tab.findViewById(R.id.tab_titile).setSelected(false);

            }
        }

        supportInvalidateOptionsMenu();
    }

    private void changeActionBarTitle(int i) {
    }
}
