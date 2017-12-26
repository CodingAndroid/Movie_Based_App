package code.com.movie_based_app.mvp.view;

import java.util.List;

import code.com.movie_based_app.mvp.model.SearchBook;

/**
 * Created by lihui1 on 2017/12/26.
 */

public interface SearchBookView {

    void refreshListData(List<SearchBook> searchBookData);

    void addMoreListData(List<SearchBook> searchBookData);

    void showMessage(String message);
}
