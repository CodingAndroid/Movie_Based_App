package code.com.movie_based_app.http;

/**
 * Created by lihui1 on 2017/12/22.
 */

public class Error extends RuntimeException{
    private int errorCode;

    public Error(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
