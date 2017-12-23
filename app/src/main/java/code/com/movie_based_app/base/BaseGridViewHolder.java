package code.com.movie_based_app.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import code.com.movie_based_app.MovieApp;

/**
 * Created by lihui1 on 2017/12/22.
 */

public class BaseGridViewHolder {

    private final View mConvertView;

    private SparseArray<View> mViews;

    private int mPosition;

    public BaseGridViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mPosition = position;
        this.mConvertView.setTag(this);
    }

    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public static BaseGridViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position){
        if (convertView == null){
            return new BaseGridViewHolder(context, parent, layoutId, position);
        } else {
            BaseGridViewHolder holder = (BaseGridViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    //获取 convertView
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 设置控件以及监听（采用链式编程方法）
     */
    /**
     * 设置 TextView 的值
     * @param viewId
     * @param text
     * @return
     */
    public BaseGridViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseGridViewHolder setText(int viewId, Float text)
    {
        TextView tv = getView(viewId);
        tv.setText(text.toString());
        return this;
    }

    /**
     * 设置TImageView的值
     * @param viewId
     * @param resId
     * @return
     */
    public BaseGridViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public BaseGridViewHolder setImageResources(String img_str, int viewId){
        ImageView view = getView(viewId);
        Glide.with(MovieApp.getAppContext())
                .load(img_str)
                .into(view);
        return this;
    }

    public BaseGridViewHolder setWeightAndHeight(int viewId, int m, int n){
        LinearLayout l = getView(viewId);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) l.getLayoutParams();
        /**/
        WindowManager wm = (WindowManager) MovieApp.getAppContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        layoutParams.width = (width-2) / m;
        layoutParams.height = (height-1) / n;
        l.setLayoutParams(layoutParams);
        return this;
    }
    /**
     * 设置是否可见
     * @param viewId
     * @param visible
     * @return
     */
    public BaseGridViewHolder setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置tag
     * @param viewId
     * @param tag
     * @return
     */
    public BaseGridViewHolder setTag(int viewId, Object tag)
    {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public BaseGridViewHolder setTag(int viewId, int key, Object tag)
    {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }
    /**
     * 设置 Checkable
     * @param viewId
     * @param checked
     * @return
     */
    public BaseGridViewHolder setChecked(int viewId, boolean checked)
    {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 点击事件
     */
    public BaseGridViewHolder setOnClickListener(int viewId,View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 触摸事件
     */
    public BaseGridViewHolder setOnTouchListener(int viewId,View.OnTouchListener listener)
    {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * 长按事件
     */
    public BaseGridViewHolder setOnLongClickListener(int viewId,View.OnLongClickListener listener)
    {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}
