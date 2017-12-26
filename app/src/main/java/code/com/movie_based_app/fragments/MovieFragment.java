package code.com.movie_based_app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.com.movie_based_app.MovieApp;
import code.com.movie_based_app.R;
import code.com.movie_based_app.activities.BookSearchActivity;
import code.com.movie_based_app.activities.MovieDetailActivity;
import code.com.movie_based_app.activities.TopMovieDetailActivity;
import code.com.movie_based_app.adapter.HotMovieAdapter;
import code.com.movie_based_app.adapter.MovieAdapter;
import code.com.movie_based_app.adapter.SpacesItemDecoration;
import code.com.movie_based_app.bean.Movie;
import code.com.movie_based_app.http.Error;
import code.com.movie_based_app.listener.AutoLoadListener;
import rx.functions.Action1;

/**
 * Created by lihui1 on 2017/12/14.
 */

public class MovieFragment extends Fragment{
    private GridView sGridView, mGridView;
    private RecyclerView mMovieRecyclerView;
    private MovieAdapter mMovieAdapter;
    private HotMovieAdapter mHotMovieAdapter;
    public int width;
    public LinearLayout.LayoutParams layoutParams;
    public int count = 12;
    public int counts = 12;
    public ArrayList<Map<String, Object>> data;

    private EditText mEdit_Search;
    private Button mBtn_Search;

    private int sub_titles[] = {R.string.me_history, R.string.me_collection,R.string.me_offline,
            R.string.me_wallet,R.string.me_order,R.string.me_game,R.string.me_upload,R.string.me_subscribe,
            R.string.me_skin,R.string.me_movie,R.string.me_setting, R.string.me_feedback};
    private int sub_image_icons[] = {R.drawable.me_history, R.drawable.me_collection,R.drawable.me_offline,
            R.drawable.me_wallet,R.drawable.me_order, R.drawable.me_games,
            R.drawable.me_upload, R.drawable.me_subs, R.drawable.me_skin,
            R.drawable.me_movie,R.drawable.me_setting, R.drawable.me_feedback};

    AutoLoadListener.AutoLoadCallback callback = new AutoLoadListener.AutoLoadCallback() {
        @Override
        public void upToLoad() {
            Log.d("TAG", "***");
        }
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        sGridView = (GridView) view.findViewById(R.id.sub_grid);
        mGridView = (GridView) view.findViewById(R.id.grid);
        AutoLoadListener autoLoadListener = new AutoLoadListener(callback);
        mGridView.setOnScrollListener(autoLoadListener);
        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();

        switch (count % 3){
            case 0:
                counts = count;
                break;
            case 1:
                counts = count+3;
                break;
            case 2:
                counts = count+2;
                break;
        }
        sGridView.setAdapter(new GridAdapter(getData()));
        mMovieRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_movie);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mMovieRecyclerView.setLayoutManager(manager);
        mMovieAdapter = new MovieAdapter();
        mMovieRecyclerView.setAdapter(mMovieAdapter);
        mMovieRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        getMovieList();

        mEdit_Search = (EditText) view.findViewById(R.id.edit_search);
        mBtn_Search = (Button) view.findViewById(R.id.btn_search);
        mBtn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookSearchActivity.class);
                intent.putExtra("key", mEdit_Search.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }

    public ArrayList<Map<String, Object>> getData(){
        data = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i<12; i++){
            map = new HashMap<>();
            map.put("sub_image_icons", sub_image_icons[i]);
            map.put("sub_titles", sub_titles[i]);
            data.add(map);
        }
        return data;
    }

    class ViewHolder{
        public LinearLayout linearLayout;
        public ImageView imageView;
        public TextView textView;
    }
    class GridAdapter extends BaseAdapter {
        private ArrayList<Map<String, Object>> mList;

        public GridAdapter(ArrayList<Map<String, Object>> list) {
            this.mList = list;
        }

        @Override
        public int getCount() {
            return counts;
        }

        @Override
        public Object getItem(int position) {
            return counts;
        }

        @Override
        public long getItemId(int position) {
            return counts;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_gridview, null);
                viewHolder = new ViewHolder();
                viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.item_linear);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.item_image);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.item_text);
                convertView.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) convertView.getTag();
            layoutParams = (LinearLayout.LayoutParams) viewHolder.linearLayout.getLayoutParams();
            int w = (width - 2) / 3;
            layoutParams.width = w;
            layoutParams.height = (width - 3) / 6;
            viewHolder.linearLayout.setLayoutParams(layoutParams);
            if (position < count) {
                viewHolder.imageView.setImageResource((Integer) mList.get(position).get("sub_image_icons"));
                viewHolder.textView.setText((Integer) mList.get(position).get("sub_titles"));
            }

            return convertView;
        }
    }

    /**
     * 获取Top250电影列表
     */
    private void getMovieList(){
        MovieApp.getMovieLoader().getMovie(0,6).subscribe(new Action1<List<Movie>>() {
            @Override
            public void call(List<Movie> movies) {
                mMovieAdapter.setMovies(movies);
                mMovieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(getActivity(), TopMovieDetailActivity.class);
                        startActivity(intent);
                    }
                });
                mMovieAdapter.notifyDataSetChanged();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if(throwable instanceof Error){
                    Error fault = (Error) throwable;
                    if(fault.getErrorCode() == 404){
                        //错误处理
                    }else if(fault.getErrorCode() == 500){
                        //错误处理
                    }else if(fault.getErrorCode() == 501){
                        //错误处理
                    }
                }
            }
        });
        /**
         * 获取热映电影列表
         */
        MovieApp.getMovieLoader().getHotMovie().subscribe(new Action1<List<Movie>>() {
            @Override
            public void call(final List<Movie> movies) {
                mHotMovieAdapter = new HotMovieAdapter(movies, MovieApp.getAppContext());
                mGridView.setAdapter(mHotMovieAdapter);
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                        intent.putExtra("id", movies.get(position).id);
                        intent.putExtra("title", movies.get(position).title);
                        startActivity(intent);
                    }
                });
                mHotMovieAdapter.notifyDataSetChanged();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if(throwable instanceof Error){
                    Error fault = (Error) throwable;
                    if(fault.getErrorCode() == 404){
                        //错误处理
                    }else if(fault.getErrorCode() == 500){
                        //错误处理
                    }else if(fault.getErrorCode() == 501){
                        //错误处理
                    }
                }
            }
        });
    }
}
