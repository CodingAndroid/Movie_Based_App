package code.com.movie_based_app.http;

import java.util.List;

import code.com.movie_based_app.bean.Movie;
import code.com.movie_based_app.bean.MovieSubject;
import code.com.movie_based_app.service.MovieService;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lihui1 on 2017/12/24.
 */

public class MovieLoader extends ObjectLoader{

    private MovieService mMovieService;

    public MovieLoader(){
        mMovieService = RetrofitManager.getInstance().create(MovieService.class);
    }

    /**
     * 获取Top250电影列表
     * @param start
     * @param count
     * @return
     */
    public Observable<List<Movie>> getMovie(int start, int count){
        return observe(mMovieService.getTop250(start,count))
                .map(new Func1<MovieSubject, List<Movie>>() {
                    @Override
                    public List<Movie> call(MovieSubject movieSubject) {
                        return movieSubject.subjects;
                    }
                });
    }

    public Observable<List<Movie>> getHotMovie(){
        return observe(mMovieService.getHot()).map(new Func1<MovieSubject, List<Movie>>() {
            @Override
            public List<Movie> call(MovieSubject movieSubject) {
                return movieSubject.subjects;
            }
        });
    }
}
