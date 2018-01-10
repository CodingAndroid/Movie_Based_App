package code.com.movie_based_app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

import code.com.movie_based_app.fragments.MovieFragment;
import code.com.movie_based_app.http.MovieLoader;

/**
 * Created by lihui1 on 2017/12/20.
 */

public class MovieApp extends Application{
    private static MovieApp mMovieApp;
    public static MovieLoader mMovieLoader;
    public static MovieApp getInstance(){
        return mMovieApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMovieApp = this;
        ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    public static MovieApp getAppContext(){
        return mMovieApp;
    }

    public static MovieLoader getMovieLoader(){
        if (mMovieLoader == null){
            mMovieLoader = new MovieLoader();
        }
        return mMovieLoader;
    }
}
