package kr.pe.burt.android.lib.androidgradientimageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by burt on 2016. 6. 13..
 */
public class AndroidGradientImageView extends ImageView {
    public AndroidGradientImageView(Context context) {
        super(context);
    }

    public AndroidGradientImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AndroidGradientImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AndroidGradientImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[] {0x00000000,0xAA00ff00, 0xFFFF0000});
        gd.setCornerRadius(0f);
        gd.setDither(true);
        gd.draw(canvas);

        //Matrix m = new Matrix();
        //m.setRotate(45, getWidth()/2, getHeight()/2);
        //Shader shader = new LinearGradient()
        //shader.setLocalMatrix(m);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(100);
        //paint.setShader(shader);
        canvas.drawLine(0, 0, 100, 100, paint);
        canvas.drawBitmap(shapeDrawableToBitmap(gd, getWidth()/2, getHeight()/2, getWidth(), getHeight()), 0, 0, null);
    }

    private Bitmap shapeDrawableToBitmap(Drawable drawable, int left, int top, int width, int height) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(left, top, width, height);
        drawable.draw(canvas);
        return bitmap;
    }
}
