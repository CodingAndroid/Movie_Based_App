package code.com.movie_based_app.mvp.presenter;

/**
 * Created by lihui1 on 2017/12/26.
 */

public interface SearchBookPresenter {

    void loadListData(String str, int start, int count);

    void onItemClickListener(int position);
}
