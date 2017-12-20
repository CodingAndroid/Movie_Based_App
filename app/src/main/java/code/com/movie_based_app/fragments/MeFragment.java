package code.com.movie_based_app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.ProcessingInstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.com.movie_based_app.R;


/**
 * Created by lihui1 on 2017/12/14.
 */

public class MeFragment extends Fragment{

    private GridView mGridView;
    public int width;
    public LinearLayout.LayoutParams layoutParams;
    public int sum = 12;
    public int sums = 12;

    public ArrayList<Map<String, Object>> data;
    private int titles[] = {R.string.huabei, R.string.transfer_accounts,R.string.re_charge,
            R.string.tickets,R.string.yu_ebao,R.string.express,R.string.credit_card,R.string.ant_fortune,
            R.string.tao_ticket,R.string.bike_sharing,R.string.urban_service, R.string.more};
    private int image_icons[] = {R.drawable.huabei, R.drawable.transfer_accounts,R.drawable.recharge,
            R.drawable.tickets,R.drawable.yu_e_bao, R.drawable.express,
            R.drawable.credit_card, R.drawable.ant_fortune, R.drawable.tao_tickets,
            R.drawable.bike_sharing,R.drawable.uraban_service, R.drawable.more};



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        mGridView = (GridView) view.findViewById(R.id.main_grid);


        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        switch (sum % 4){
            case 0:
                sums = sum;
                break;
            case 1:
                sums = sum+3;
                break;
            case 2:
                sums = sum+2;
                break;
            case 3:
                sums = sum+1;
                break;
        }
        mGridView.setAdapter(new MyAdapter(getData()));
        return view;
    }

    public ArrayList<Map<String, Object>> getData(){
        data = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i<12; i++){
            map = new HashMap<>();
            map.put("image_icons", image_icons[i]);
            map.put("titles", titles[i]);
            data.add(map);
        }
        return data;
    }

    class Handler{
        public LinearLayout linearLayout;
        public ImageView imageView;
        public TextView textView;
    }
    class MyAdapter extends BaseAdapter {
        private ArrayList<Map<String, Object>> mList;

        public MyAdapter(ArrayList<Map<String, Object>> list) {
            this.mList = list;
        }

        @Override
        public int getCount() {
            return sums;
        }

        @Override
        public Object getItem(int position) {
            return sums;
        }

        @Override
        public long getItemId(int position) {
            return sums;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Handler handler;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_gridview, null);
                handler = new Handler();
                handler.linearLayout = (LinearLayout) convertView.findViewById(R.id.item_linear);
                handler.imageView = (ImageView) convertView.findViewById(R.id.item_image);
                handler.textView = (TextView) convertView.findViewById(R.id.item_text);
                convertView.setTag(handler);
            }
            handler = (Handler) convertView.getTag();
            layoutParams = (LinearLayout.LayoutParams) handler.linearLayout.getLayoutParams();
            int w = (width - 3) / 4;
            layoutParams.width = w;
            layoutParams.height = w;
            handler.linearLayout.setLayoutParams(layoutParams);
            if (position < sum) {
                handler.imageView.setImageResource((Integer) mList.get(position).get("image_icons"));
                handler.textView.setText((Integer) mList.get(position).get("titles"));
            }

            return convertView;
        }
    }

}
