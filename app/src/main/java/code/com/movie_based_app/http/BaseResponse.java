package code.com.movie_based_app.http;

/**网络请求结果的基类
 * Created by lihui1 on 2017/12/22.
 */

public class BaseResponse<T> {

    public int status;

    public String message;

    public T data;

    public boolean isSuccess(){
        return status == 200;
    }
}
