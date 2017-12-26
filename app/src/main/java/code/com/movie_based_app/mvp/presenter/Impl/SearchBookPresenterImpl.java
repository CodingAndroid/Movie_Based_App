package code.com.movie_based_app.mvp.presenter.Impl;

import android.content.Context;

import java.util.List;

import code.com.movie_based_app.Config;
import code.com.movie_based_app.interactor.impl.SearchBookListInteractorImpl;
import code.com.movie_based_app.listener.SearchRequestListener;
import code.com.movie_based_app.mvp.model.SearchBook;
import code.com.movie_based_app.mvp.presenter.SearchBookPresenter;
import code.com.movie_based_app.mvp.view.SearchBookView;

/**
 * Created by lihui1 on 2017/12/26.
 */

public class SearchBookPresenterImpl implements SearchBookPresenter, SearchRequestListener<List<SearchBook>>{

    private Context mContext;

    private SearchBookView mSearchBookView;

    private SearchBookListInteractorImpl mSearchBookListInteractor;

    public SearchBookPresenterImpl(Context context, SearchBookView searchBookView){
        this.mContext = context;
        this.mSearchBookView =  searchBookView;
        mSearchBookListInteractor = new SearchBookListInteractorImpl(this);
    }
    @Override
    public void loadListData(String str, int start, int count) {
        mSearchBookListInteractor.getSearchBookList(str, start, count);
    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override
    public void onSuccess(int tag, List<SearchBook> data) {
        if (tag == Config.REFRESH_DATA){
            mSearchBookView.refreshListData(data);
        } else if (tag == Config.LOADMORE_DATA){
            mSearchBookView.addMoreListData(data);
        }
    }

    @Override
    public void onError(String msg) {
        mSearchBookView.showMessage(msg);
    }

    @Override
    public void onException(String msg) {
        mSearchBookView.showMessage(msg);
    }
}
