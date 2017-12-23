package code.com.movie_based_app.http;

import code.com.movie_based_app.bean.MovieSubject;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lihui1 on 2017/12/23.
 */

public abstract class MovieService {
    //获取豆瓣Top250 榜单
    @GET("top250")
    abstract Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count") int count);
}
