package code.com.movie_based_app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import code.com.movie_based_app.R;

/**
 * Created by lihui1 on 2018/1/10.
 */

public class CommonGridAdapter extends BaseAdapter{

    private ArrayList<Map<String, Object>> mList;
    private Context mContext;
    public LinearLayout.LayoutParams layoutParams;
    public int width;

    public CommonGridAdapter(ArrayList<Map<String, Object>> list, Context context) {
        this.mList = list;
        this.mContext = context;
        width = ((Activity)mContext).getWindowManager().getDefaultDisplay().getWidth();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return mList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, null);
            commonViewHolder = new CommonViewHolder();
            commonViewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.item_linear);
            commonViewHolder.imageView = (ImageView) convertView.findViewById(R.id.item_image);
            commonViewHolder.textView = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(commonViewHolder);
        }
        commonViewHolder = (CommonViewHolder) convertView.getTag();
        layoutParams = (LinearLayout.LayoutParams) commonViewHolder.linearLayout.getLayoutParams();
        int w = (width - 3) / 4;
        layoutParams.width = w;
        layoutParams.height = w;
        commonViewHolder.linearLayout.setLayoutParams(layoutParams);
        if (position < mList.size()) {
            commonViewHolder.imageView.setImageResource((Integer) mList.get(position).get("image_icons"));
            commonViewHolder.textView.setText((Integer) mList.get(position).get("titles"));
        }

        return convertView;
    }

    static class CommonViewHolder{
        public LinearLayout linearLayout;
        public ImageView imageView;
        public TextView textView;
    }
}
