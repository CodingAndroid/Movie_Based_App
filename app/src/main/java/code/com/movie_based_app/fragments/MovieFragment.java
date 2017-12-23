package code.com.movie_based_app.fragments;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import code.com.movie_based_app.adapter.MovieAdapter;
import code.com.movie_based_app.adapter.SpacesItemDecoration;
import code.com.movie_based_app.base.BaseCustomAdapter;
import code.com.movie_based_app.base.BaseGridViewHolder;
import code.com.movie_based_app.bean.Movie;
import code.com.movie_based_app.bean.MovieSubject;
import code.com.movie_based_app.http.Error;
import code.com.movie_based_app.http.ObjectLoader;
import code.com.movie_based_app.http.RetrofitManager;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by lihui1 on 2017/12/14.
 */

public class MovieFragment extends Fragment{
    private GridView sGridView, mGridView;
    private RecyclerView mMovieRecyclerView;
    private MovieAdapter mMovieAdapter;
    private SimpleAdapter mSimpleAdapter;
    private MovieLoader mMovieLoader;
    public int width;
    public LinearLayout.LayoutParams layoutParams;
    public int count = 12;
    public int counts = 12;
    public ArrayList<Map<String, Object>> data;

    private int sub_titles[] = {R.string.me_history, R.string.me_collection,R.string.me_offline,
            R.string.me_wallet,R.string.me_order,R.string.me_game,R.string.me_upload,R.string.me_subscribe,
            R.string.me_skin,R.string.me_movie,R.string.me_setting, R.string.me_feedback};
    private int sub_image_icons[] = {R.drawable.me_history, R.drawable.me_collection,R.drawable.me_offline,
            R.drawable.me_wallet,R.drawable.me_order, R.drawable.me_games,
            R.drawable.me_upload, R.drawable.me_subs, R.drawable.me_skin,
            R.drawable.me_movie,R.drawable.me_setting, R.drawable.me_feedback};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        sGridView = (GridView) view.findViewById(R.id.sub_grid);
        mGridView = (GridView) view.findViewById(R.id.grid);
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

        /*电影列表*/
        mMovieLoader = new MovieLoader();
        mMovieRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_movie);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mMovieRecyclerView.setLayoutManager(manager);
        mMovieAdapter = new MovieAdapter();
        mMovieRecyclerView.setAdapter(mMovieAdapter);
        mMovieRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        getMovieList();

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
     * 获取电影列表
     */
    private void getMovieList(){
        mMovieLoader.getMovie(0,6).subscribe(new Action1<List<Movie>>() {
            @Override
            public void call(List<Movie> movies) {
                mMovieAdapter.setMovies(movies);
                mMovieAdapter.notifyDataSetChanged();

//                mSimpleAdapter = new SimpleAdapter(movies, MovieApp.getAppContext());
//                mGridView.setAdapter(mSimpleAdapter);
//                mSimpleAdapter.notifyDataSetChanged();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("TAG","error message:"+throwable.getMessage());
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

        mMovieLoader.getHotMovie().subscribe(new Action1<List<Movie>>() {
            @Override
            public void call(List<Movie> movies) {
                mSimpleAdapter = new SimpleAdapter(movies, MovieApp.getAppContext());
                mGridView.setAdapter(mSimpleAdapter);
                mSimpleAdapter.notifyDataSetChanged();
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

    public class MovieLoader extends ObjectLoader{
        private MovieService mMovieService;

        public MovieLoader(){
            mMovieService = RetrofitManager.getInstance().create(MovieService.class);
        }

        /**
         * 获取Top250电影列表
         * @param start
         * @param count
         * @return
         */
        public Observable<List<Movie>> getMovie(int start, int count){
            return observe(mMovieService.getTop250(start,count))
                    .map(new Func1<MovieSubject, List<Movie>>() {
                        @Override
                        public List<Movie> call(MovieSubject movieSubject) {
                            return movieSubject.subjects;
                        }
                    });
        }

        public Observable<List<Movie>> getHotMovie(){
            return observe(mMovieService.getHot()).map(new Func1<MovieSubject, List<Movie>>() {
                @Override
                public List<Movie> call(MovieSubject movieSubject) {
                    return movieSubject.subjects;
                }
            });
        }
    }

    public interface MovieService{
        //获取豆瓣Top250 榜单
        @GET("top250")
        Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);
        /*获取正在热映电影列表*/
        @GET("in_theaters")
        Observable<MovieSubject> getHot();
    }

    class SimpleAdapter extends BaseCustomAdapter<Movie> {
        public SimpleAdapter(List list, Context context){
            super(list, context, R.layout.movie_grid_view);
        }


        @Override
        public void bindData(BaseGridViewHolder holder, Movie t) {
            Log.d("TAG", "---"+t.images.small);
            holder.setText(R.id.tv2, t.title)
                    .setImageResources(t.images.small, R.id.img).setWeightAndHeight(R.id.container, 3,4);
        }
    }

}
