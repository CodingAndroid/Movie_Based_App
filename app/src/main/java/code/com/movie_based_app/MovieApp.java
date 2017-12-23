package code.com.movie_based_app;

import android.app.Application;

import code.com.movie_based_app.fragments.MovieFragment;

/**
 * Created by lihui1 on 2017/12/20.
 */

public class MovieApp extends Application{
    private static MovieApp mMovieApp;

    public static MovieApp getInstance(){
        return mMovieApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMovieApp = this;
    }

    public static MovieApp getAppContext(){
        return mMovieApp;
    }
}
