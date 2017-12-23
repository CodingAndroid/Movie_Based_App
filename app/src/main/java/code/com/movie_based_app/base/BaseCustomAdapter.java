package code.com.movie_based_app.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by lihui1 on 2017/12/22.
 */

public abstract class BaseCustomAdapter<T> extends BaseAdapter{

    protected List<T> mDatas;
    protected Context mContext;
    protected int mLayoutId;

    public BaseCustomAdapter(List<T> datas, Context context, int layoutId) {
        this.mDatas = datas;
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas == null ? 0 : mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseGridViewHolder holder = BaseGridViewHolder.getViewHolder(mContext, convertView, parent, mLayoutId, position);

        T t = mDatas.get(position);

        //抽象出 ViewHolder 让用户去实现填充数据
        bindData(holder, t);

        return holder.getConvertView();
    }

    public abstract void bindData(BaseGridViewHolder holder, T t);
}
