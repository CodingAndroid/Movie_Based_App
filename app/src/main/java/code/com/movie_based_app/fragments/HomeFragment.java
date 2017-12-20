package code.com.movie_based_app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import code.com.movie_based_app.R;
import code.com.movie_based_app.adapter.MusicAdapter;
import code.com.movie_based_app.adapter.SpacesItemDecoration;
import code.com.movie_based_app.bean.Music;

/**
 * Created by lihui1 on 2017/12/14.
 */

public class HomeFragment extends Fragment{

    private RecyclerView mRecyclerView;

    private GridLayoutManager mLayoutManager;

    private MusicAdapter mAdapter;

    private static List<Music> mList;

    /**
     * 模拟本地数据
     */
    static {
        mList = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "推荐歌单";
            mList.add(music);
        }

        for (int i = 0; i < 6; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.ic_cover;
            music.title = "先不要降温！我没钱买衣服";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_recom;
            music.title = "推荐MV";
            mList.add(music);
        }

        for (int i = 0; i < 4; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_TWO;
            music.imageId = R.drawable.ic_recom;
            music.title = "Perfect Day";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "精选专栏";
            mList.add(music);
        }

        for (int i = 0; i < 3; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_LIST;
            music.imageId = R.drawable.ic_cover;
            music.title = "去看《银翼杀手2049》前，你应该知道的三件事";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_new;
            music.title = "最新音乐";
            mList.add(music);
        }

        for (int i = 0; i < 6; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.ic_new;
            music.title = "[BGM]一定听过的神级背景配乐";
            mList.add(music);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcy);
        mLayoutManager = new GridLayoutManager(getActivity(), 6);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mList.get(position).type;
                if (type == Music.TYPE.TYPE_GRID_THREE){
                    return 2;
                } else if (type == Music.TYPE.TYPE_GRID_TWO){
                    return 3;
                } else if (type == Music.TYPE.TYPE_LIST){
                    return 6;
                } else if (type == Music.TYPE.TYPE_TITLE){
                    return 6;
                }
                return 0;
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(2));
        mAdapter = new MusicAdapter(mList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
