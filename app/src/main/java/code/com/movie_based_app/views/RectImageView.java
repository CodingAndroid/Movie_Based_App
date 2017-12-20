package code.com.movie_based_app.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lihui1 on 2017/12/15.
 */

public class RectImageView extends ImageView{
    public RectImageView(Context context) {
        super(context);
    }

    public RectImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int halfWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width / 3*2, widthMode);
        super.onMeasure(widthMeasureSpec, halfWidthMeasureSpec);
    }
}
