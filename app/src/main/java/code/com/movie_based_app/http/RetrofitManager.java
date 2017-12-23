package code.com.movie_based_app.http;

import java.util.concurrent.TimeUnit;

import code.com.movie_based_app.ApiConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lihui1 on 2017/12/22.
 */

public class RetrofitManager {

    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;
    private RetrofitManager(){
        /*创建OKHttpClient*/
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);

        /*创建Retrofit*/
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    /*获取RetrofitManager实例*/
    public static RetrofitManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /*获取对应的Service*/
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
