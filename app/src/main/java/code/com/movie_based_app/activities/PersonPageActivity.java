package code.com.movie_based_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import code.com.movie_based_app.R;
import code.com.movie_based_app.utils.UscreenUtil;
import code.com.movie_based_app.widgets.SettingLayout;

public class PersonPageActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageView mBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_page);
        ButterKnife.bind(this);
        SettingLayout mSetLayout = (SettingLayout) findViewById(R.id.set_layout);
        SettingLayout.IClickListener listener = new SettingLayout.IClickListener() {
            @Override
            public void click(SettingLayout layout, SettingLayout.Item item) {
                switch (item.titleStrId){
                    case R.string.me_column:
                        Toast.makeText(PersonPageActivity.this, "我的专栏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.string.me_ask:
                        Toast.makeText(PersonPageActivity.this, "我的提问", Toast.LENGTH_SHORT).show();
                        break;
                    case R.string.me_answer:
                        Toast.makeText(PersonPageActivity.this, "我的回答", Toast.LENGTH_SHORT).show();
                        break;
                    case R.string.me_collect:
                        Toast.makeText(PersonPageActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.string.me_prasie:
                        Toast.makeText(PersonPageActivity.this, "我的点赞", Toast.LENGTH_SHORT).show();
                        break;
                    case R.string.me_oppose:
                        Toast.makeText(PersonPageActivity.this, "我的反对", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
        SettingLayout.Item items[] = new SettingLayout.Item[]{
                new SettingLayout.Item(R.string.me_column, R.drawable.column, "12", true, SettingLayout.SEP.AFTERICON, listener),
                new SettingLayout.Item(R.string.me_ask, R.drawable.ask, "6", true, SettingLayout.SEP.AFTERICON, listener),
                new SettingLayout.Item(R.string.me_answer, R.drawable.answer, "56", true, SettingLayout.SEP.NO, listener),
                new SettingLayout.Item(R.string.me_collect, R.drawable.collect, "142", true, SettingLayout.SEP.AFTERICON, listener),
                new SettingLayout.Item(R.string.me_prasie, R.drawable.prase, "268", true, SettingLayout.SEP.AFTERICON, listener),
                new SettingLayout.Item(R.string.me_oppose, R.drawable.oppose, "2", true, SettingLayout.SEP.AFTERICON, listener)
        };
        for (SettingLayout.Item item : items){
            if (item.titleStrId == R.string.me_column){

            }
            if (item.titleStrId == R.string.me_collect){
                mSetLayout.addHeadTips("");
            }
            mSetLayout.addItem(item);
        }
    }

    @OnClick(R.id.back)
    public void back(){
        //back

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
