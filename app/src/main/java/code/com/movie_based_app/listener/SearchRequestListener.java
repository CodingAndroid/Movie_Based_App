package code.com.movie_based_app.listener;

/**
 * Created by lihui1 on 2017/12/26.
 */

public interface SearchRequestListener<T> {

    void onSuccess(int tag, T data);

    void onError(String msg);

    void onException(String msg);
}
