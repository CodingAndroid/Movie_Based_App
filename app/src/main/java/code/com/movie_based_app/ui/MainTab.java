package code.com.movie_based_app.ui;

import code.com.movie_based_app.R;
import code.com.movie_based_app.fragments.FindFragment;
import code.com.movie_based_app.fragments.HomeFragment;
import code.com.movie_based_app.fragments.MeFragment;
import code.com.movie_based_app.fragments.MovieFragment;

/**
 * Created by lihui1 on 2017/12/14.
 */

public enum MainTab {

    TAB_HOME(0, R.string.main_tab_home, R.drawable.tab_icon_home, HomeFragment.class),
    TAB_FIND(1, R.string.main_tab_find, R.drawable.tab_icon_find, FindFragment.class),
    TAB_MOVIE(2, R.string.main_tab_movie, R.drawable.tab_icon_movie, MovieFragment.class),
    TAB_ME(3, R.string.main_tab_me, R.drawable.tab_icon_user, MeFragment.class);

    private int index;

    private int nameRes;

    private int iconRes;

    private Class<?> clazz;

    MainTab(int index, int nameRes, int iconRes, Class<?> clazz) {
        this.index = index;
        this.nameRes = nameRes;
        this.iconRes = iconRes;
        this.clazz = clazz;
    }

    public int getIndex() {
        return index;
    }

    public int getNameRes() {
        return nameRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
