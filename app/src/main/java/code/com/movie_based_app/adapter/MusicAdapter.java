package code.com.movie_based_app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import code.com.movie_based_app.R;
import code.com.movie_based_app.bean.Music;
import code.com.movie_based_app.views.RectImageView;
import code.com.movie_based_app.views.SquareImageView;

/**
 * Created by lihui1 on 2017/12/15.
 */

public class MusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private List<Music> mList;

    private Context mContext;

    private LayoutInflater mInflater;

    @Override
    public void onClick(View v) {

    }

    /**
     * 点击事件
     */
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public MusicAdapter(List<Music> List, Context context) {
        this.mList = List;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder mViewHolder = null;
        if (viewType == Music.TYPE.TYPE_GRID_THREE){
            view = mInflater.inflate(R.layout.item_grid_three, parent, false);
            mViewHolder = new GridThreeViewHolder(view);
        } else if (viewType == Music.TYPE.TYPE_GRID_TWO){
            view = mInflater.inflate(R.layout.item_grid_two, parent, false);
            mViewHolder = new GridTwoViewHolder(view);
        } else if (viewType == Music.TYPE.TYPE_LIST){
            view = mInflater.inflate(R.layout.item_list, parent, false);
            mViewHolder = new ListViewHolder(view);
        } else if (viewType == Music.TYPE.TYPE_TITLE){
            view = mInflater.inflate(R.layout.item_title, parent, false);
            mViewHolder = new TitleViewHolder(view);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case Music.TYPE.TYPE_GRID_THREE:
                GridThreeViewHolder gHolder_three = (GridThreeViewHolder) holder;
                gHolder_three.tv_content.setText(mList.get(position).title);
                gHolder_three.iv_icon.setImageResource(mList.get(position).imageId);
                //点击事件
                gHolder_three.itemView.setOnClickListener(this);
                gHolder_three.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_GRID_TWO:
                GridTwoViewHolder gHolder_two = (GridTwoViewHolder) holder;
                gHolder_two.tv_content.setText(mList.get(position).title);
                gHolder_two.iv_icon.setImageResource(mList.get(position).imageId);
                //点击事件
                gHolder_two.itemView.setOnClickListener(this);
                gHolder_two.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_LIST:
                ListViewHolder lHolder = (ListViewHolder) holder;
                lHolder.tv_content.setText(mList.get(position).title);
                lHolder.iv_icon.setImageResource(mList.get(position).imageId);
                //点击事件
                lHolder.itemView.setOnClickListener(this);
                lHolder.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_TITLE:
                TitleViewHolder tHolder = (TitleViewHolder) holder;
                tHolder.tv_content.setText(mList.get(position).title);
                //点击事件
                tHolder.itemView.setOnClickListener(this);
                tHolder.itemView.setTag(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }



    class TitleViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_content;
        public TitleViewHolder(View view){
            super(view);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    class GridTwoViewHolder extends RecyclerView.ViewHolder {

        public RectImageView iv_icon;
        public TextView tv_content;

        public GridTwoViewHolder(View itemView) {
            super(itemView);
            iv_icon = (RectImageView) itemView.findViewById(R.id.iv_icon);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    class GridThreeViewHolder extends RecyclerView.ViewHolder {

        public SquareImageView iv_icon;
        public TextView tv_content;

        public GridThreeViewHolder(View itemView) {
            super(itemView);
            iv_icon = (SquareImageView) itemView.findViewById(R.id.iv_icon);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        public RectImageView iv_icon;
        public TextView tv_content;

        public ListViewHolder(View itemView) {
            super(itemView);
            iv_icon = (RectImageView) itemView.findViewById(R.id.iv_icon);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

}
