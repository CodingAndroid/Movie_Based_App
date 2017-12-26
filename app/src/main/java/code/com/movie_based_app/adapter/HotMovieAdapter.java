package code.com.movie_based_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import code.com.movie_based_app.MovieApp;
import code.com.movie_based_app.R;
import code.com.movie_based_app.activities.MovieDetailActivity;
import code.com.movie_based_app.base.BaseCustomAdapter;
import code.com.movie_based_app.base.BaseGridViewHolder;
import code.com.movie_based_app.bean.Movie;

/**
 * Created by lihui1 on 2017/12/23.
 */

public class HotMovieAdapter extends BaseCustomAdapter<Movie>{

    public HotMovieAdapter(List datas, Context context) {
        super(datas, context, R.layout.movie_grid_view);
    }

    @Override
    public void bindData(BaseGridViewHolder holder, final Movie movie) {
        holder.setText(R.id.tv2, movie.title)
                .setWeightAndHeight(R.id.container, 3, 4)
                .setImageResources(movie.images.small, R.id.img);
    }
}
