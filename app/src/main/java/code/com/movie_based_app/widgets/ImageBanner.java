package code.com.movie_based_app.widgets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

import code.com.movie_based_app.R;

/**
 * Created by lihui1 on 2017/12/14.
 */

public class ImageBanner extends FrameLayout{

    private SliderLayout mSlider;

    public ImageBanner(@NonNull Context context) {
        super(context);
        initView();
    }

    public ImageBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ImageBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.banner_layout, this,true);
        mSlider = (SliderLayout) findViewById(R.id.slider);
        HashMap<String, Integer> url_images = new HashMap<>();
        url_images.put("直播|2017 Google 开发者大会", R.drawable.google);
        url_images.put("兼职达人", R.drawable.e);
        url_images.put("简书Android 3.2.0 公测", R.drawable.app);
        url_images.put("我有一个Flag，不知当立不当立", R.drawable.b);
        url_images.put("招聘专栏", R.drawable.a);
        url_images.put("摄影|一个红计划", R.drawable.c);
        url_images.put("花白课堂|写作的真相", R.drawable.ff);
        url_images.put("职场小说·匠人精神·行业故事", R.drawable.gg);

        for (String name : url_images.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView.description(name)
                    .image(url_images.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            mSlider.addSlider(textSliderView);
        }
        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider.setDuration(3000);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
    }
}
