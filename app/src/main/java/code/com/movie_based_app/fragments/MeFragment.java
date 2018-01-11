package code.com.movie_based_app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.ProcessingInstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.com.lib_community.fragments.PersonPageFragment;
import code.com.movie_based_app.MovieApp;
import code.com.movie_based_app.R;
import code.com.movie_based_app.activities.PersonPageActivity;
import code.com.movie_based_app.adapter.CommonGridAdapter;
import code.com.movie_based_app.widgets.LoginDialog;


/**
 * Created by lihui1 on 2017/12/14.
 */

public class MeFragment extends Fragment{

    private GridView mGridView;
    private RelativeLayout mContainer;

    public ArrayList<Map<String, Object>> data;
    private int titles[] = {R.string.me_history, R.string.me_collection,R.string.me_offline,
            R.string.me_wallet,R.string.me_order,R.string.me_game,R.string.me_upload,R.string.me_subscribe,
            R.string.me_skin,R.string.me_movie,R.string.me_setting, R.string.me_feedback};
    private int image_icons[] = {R.drawable.me_history, R.drawable.me_collection,R.drawable.me_offline,
            R.drawable.me_wallet,R.drawable.me_order, R.drawable.me_games,
            R.drawable.me_upload, R.drawable.me_subs, R.drawable.me_skin,
            R.drawable.me_movie,R.drawable.me_setting, R.drawable.me_feedback};

    private Button mBtn_Login;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_me, container, false);
        mGridView = (GridView) view.findViewById(R.id.main_grid);
        mContainer = (RelativeLayout) view.findViewById(R.id.rl_container);
        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //person_page
                Intent intent = new Intent(getActivity(), PersonPageActivity.class);
                startActivity(intent);
                //如果是用的v4的包，则用getActivity().getSuppoutFragmentManager();
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                Fragment fragment = new PersonPageFragment();
//                fm.beginTransaction().replace(R.id.container, fragment).commit();
            }
        });
        mBtn_Login = (Button) view.findViewById(R.id.login);
        mBtn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialog loginDialog = new LoginDialog(getActivity(), R.style.Dialog_Login);
                loginDialog.show();
                WindowManager windowManager = getActivity().getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams lp = loginDialog.getWindow().getAttributes();
                lp.width = (int)(display.getWidth()); //设置宽度
                loginDialog.getWindow().setAttributes(lp);
            }
        });
        mGridView.setAdapter(new CommonGridAdapter(getData(), getActivity()));
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
}
