package code.com.movie_based_app.service;

import code.com.movie_based_app.bean.MovieSubject;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lihui1 on 2017/12/24.
 */

public interface MovieService {
    /*获取豆瓣Top250榜单*/
        /*https://api.douban.com/v2/movie/top250?start=0&count=10*/
    @GET("top250")
    Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);
    /*获取正在热映电影列表*/
        /*https://api.douban.com/v2/movie/in_theaters*/
    @GET("in_theaters")
    Observable<MovieSubject> getHot();
}
