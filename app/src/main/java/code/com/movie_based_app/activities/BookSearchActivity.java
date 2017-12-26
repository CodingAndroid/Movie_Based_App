package code.com.movie_based_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import code.com.movie_based_app.R;
import code.com.movie_based_app.adapter.SearchBookAdapter;
import code.com.movie_based_app.adapter.SpacesItemDecoration;
import code.com.movie_based_app.mvp.model.SearchBook;
import code.com.movie_based_app.mvp.presenter.Impl.SearchBookPresenterImpl;
import code.com.movie_based_app.mvp.view.SearchBookView;

public class BookSearchActivity extends AppCompatActivity implements SearchBookView{

    private XRecyclerView rv_list;
    private List<SearchBook> bookList = new ArrayList<>();
    private SearchBookAdapter adapter;
    private SearchBookPresenterImpl bookPresenter;
    private int start = 0;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        key = getIntent().getStringExtra("key");

        bookPresenter = new SearchBookPresenterImpl(BookSearchActivity.this,this);

        rv_list = (XRecyclerView) findViewById(R.id.rv_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(manager);
        rv_list.addItemDecoration(new SpacesItemDecoration(10));
        adapter = new SearchBookAdapter(this,R.layout.search_book_item,bookList);
        rv_list.setAdapter(adapter);

        rv_list.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                start = 0;
                bookPresenter.loadListData(key,start,10);
            }

            @Override
            public void onLoadMore() {
                start +=10;
                bookPresenter.loadListData(key,start,10);
            }
        });
        bookPresenter.loadListData(key,start,10);
    }

    @Override
    public void refreshListData(List<SearchBook> searchBookData) {
        rv_list.refreshComplete();
        bookList.clear();
        bookList.addAll(searchBookData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addMoreListData(List<SearchBook> searchBookData) {
        rv_list.loadMoreComplete();
        bookList.addAll(searchBookData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
