package code.com.movie_based_app.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import code.com.movie_based_app.MovieApp;
import code.com.movie_based_app.R;
import code.com.movie_based_app.bean.Movie;

/**
 * Created by lihui1 on 2017/12/22.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private List<Movie> mMovies;
    public void  setMovies(List<Movie> list){
        mMovies = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moive_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.title.setText(movie.title);
        Glide.with(MovieApp.getAppContext())
                .load(movie.images.small)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView title;
        public ViewHolder(View itemView){
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.movie_image);
            title = (TextView) itemView.findViewById(R.id.movie_title);
        }
    }
}
