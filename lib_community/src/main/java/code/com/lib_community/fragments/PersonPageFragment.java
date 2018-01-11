package code.com.lib_community.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import code.com.lib_community.R;
import code.com.lib_community.widgets.SettingLayout;


/**
 * Created by lihui1 on 2018/1/10.
 */

public class PersonPageFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_person_page, container, false);
        ImageView mBackImg = (ImageView) mView.findViewById(R.id.back);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        SettingLayout mSetLayout = (SettingLayout) mView.findViewById(R.id.set_layout);
        SettingLayout.IClickListener listener = new SettingLayout.IClickListener() {
            @Override
            public void click(SettingLayout layout, SettingLayout.Item item) {
                if (item.titleStrId == R.string.me_column) {
                    Toast.makeText(getActivity(), "我的专栏", Toast.LENGTH_SHORT).show();

                } else if (item.titleStrId == R.string.me_ask) {
                    Toast.makeText(getActivity(), "我的提问", Toast.LENGTH_SHORT).show();

                } else if (item.titleStrId == R.string.me_answer) {
                    Toast.makeText(getActivity(), "我的回答", Toast.LENGTH_SHORT).show();

                } else if (item.titleStrId == R.string.me_collect) {
                    Toast.makeText(getActivity(), "我的收藏", Toast.LENGTH_SHORT).show();

                } else if (item.titleStrId == R.string.me_prasie) {
                    Toast.makeText(getActivity(), "我的点赞", Toast.LENGTH_SHORT).show();

                } else if (item.titleStrId == R.string.me_oppose) {
                    Toast.makeText(getActivity(), "我的反对", Toast.LENGTH_SHORT).show();

                } else {
                    //
                }
            }
        };
        SettingLayout.Item items[] = new SettingLayout.Item[]{
                new SettingLayout.Item(R.string.me_column, R.drawable.column, "12", true, SettingLayout.SEP.AFTERICON, listener),
                new SettingLayout.Item(R.string.me_ask, R.drawable.ask, "6", true, SettingLayout.SEP.AFTERICON, listener),
                new SettingLayout.Item(R.string.me_answer, R.drawable.answer, "56", true, SettingLayout.SEP.NO, listener),
                new SettingLayout.Item(R.string.me_collect, R.drawable.collect, "142", true, SettingLayout.SEP.AFTERICON, listener),
                new SettingLayout.Item(R.string.me_prasie, R.drawable.prase, "268", true, SettingLayout.SEP.AFTERICON, listener),
                new SettingLayout.Item(R.string.me_oppose, R.drawable.oppose, "2", true, SettingLayout.SEP.NO, listener)
        };
        for (SettingLayout.Item item : items){
            if (item.titleStrId == R.string.me_column){
                mSetLayout.addHeadTips("");
            }
            if (item.titleStrId == R.string.me_collect){
                mSetLayout.addHeadTips("");
            }
            mSetLayout.addItem(item);
        }
        return mView;
    }

}
