package code.com.movie_based_app.bean;

/**
 * Created by lihui1 on 2017/12/15.
 */

public class Music {

    public int type;

    public String title;

    public int imageId;

    public interface TYPE{
        int TYPE_GRID_THREE = 0x01;
        int TYPE_GRID_TWO = 0x02;
        int TYPE_LIST = 0x03;
        int TYPE_TITLE = 0x04;
    }
}
