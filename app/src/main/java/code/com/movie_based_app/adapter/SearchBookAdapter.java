package code.com.movie_based_app.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import code.com.movie_based_app.R;
import code.com.movie_based_app.mvp.model.SearchBook;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by lihui1 on 2017/12/26.
 */

public class SearchBookAdapter extends CommonAdapter<SearchBook>{

    public SearchBookAdapter(Context context, int layoutId, List<SearchBook> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, SearchBook searchBook, int position) {
        ImageView iv = holder.getView(R.id.image);
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_author = holder.getView(R.id.tv_author);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_publisher = holder.getView(R.id.tv_publisher);
        TextView tv_price = holder.getView(R.id.tv_price);
        Glide.with(mContext).load(searchBook.getImage()).into(iv);
        tv_title.setText(searchBook.getTitle());
        tv_author.setText(searchBook.getAuthor()!=null&&searchBook.getAuthor().size()>0?searchBook.getAuthor().get(0):"无名");
        tv_time.setText("出版日期:"+searchBook.getPubdate());
        tv_publisher.setText("出版单位:"+searchBook.getPublisher());
        tv_price.setText("建议价格:"+searchBook.getPrice());
    }
}
