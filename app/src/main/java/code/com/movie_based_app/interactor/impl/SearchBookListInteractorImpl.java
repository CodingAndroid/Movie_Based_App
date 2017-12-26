package code.com.movie_based_app.interactor.impl;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import code.com.movie_based_app.ApiConfig;
import code.com.movie_based_app.Config;
import code.com.movie_based_app.interactor.SearchBookListInteractor;
import code.com.movie_based_app.listener.SearchRequestListener;
import code.com.movie_based_app.mvp.model.SearchBook;
import code.com.movie_based_app.utils.FastJsonUtil;
import okhttp3.Call;

/**
 * Created by lihui1 on 2017/12/26.
 */

public class SearchBookListInteractorImpl implements SearchBookListInteractor{

    private SearchRequestListener<List<SearchBook>> listener = null;

    public SearchBookListInteractorImpl(SearchRequestListener<List<SearchBook>> listener){
        this.listener = listener;
    }

    @Override
    public void getSearchBookList(String msg, final int start, int count) {
        OkHttpUtils.get().url(ApiConfig.SEARCH_BOOK)
                .addParams("q", msg)
                .addParams("start", start+"")
                .addParams("count", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String search_books = jsonObject.getString("books");
                            List<SearchBook> list = FastJsonUtil.getArrayJson(search_books, SearchBook.class);
                            if (list != null){
                                if (start == 0){
                                    listener.onSuccess(Config.REFRESH_DATA, list);
                                } else {
                                    listener.onSuccess(Config.LOADMORE_DATA, list);
                                }
                            } else {
                                listener.onError("没有数据");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
