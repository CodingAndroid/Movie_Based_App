package code.com.movie_based_app.widgets;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by lihui1 on 2018/1/8.
 */

/**
 * http://blog.csdn.net/Afanbaby/article/details/51649474
 */
public class FaceDrawable extends Drawable{

    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF mRectF;

    public FaceDrawable(Bitmap bitmap){
        this.mBitmap = bitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint.setShader(shader);
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle(getIntrinsicWidth(), getIntrinsicHeight(), getIntrinsicWidth(), mPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
