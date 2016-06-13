package kr.pe.burt.android.lib.androidgradientimageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
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

    private float x = 0f;
    private float y = 0f;
    private float width = 1.0f;
    private float height = 1.0f;
    private float rotate = 0.0f;
    private int startColor = Color.parseColor("#00000000");
    private int endColor = Color.parseColor("#FF000000");
    private int middleColor = -1;

    private float startOffset = 0.0f;
    private float endOffset = 1.0f;
    private float middleOffset = 0.5f;

    public AndroidGradientImageView(Context context) {
        this(context, null);
    }

    public AndroidGradientImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AndroidGradientImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AndroidGradientImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if(attrs == null)
            return;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AndroidGradientImageViewAttrs);

        x = array.getFloat(R.styleable.AndroidGradientImageViewAttrs_giv_x, x);
        y = array.getFloat(R.styleable.AndroidGradientImageViewAttrs_giv_y, y);
        width = array.getFloat(R.styleable.AndroidGradientImageViewAttrs_giv_width, width);
        height= array.getFloat(R.styleable.AndroidGradientImageViewAttrs_giv_height, height);
        rotate = array.getFloat(R.styleable.AndroidGradientImageViewAttrs_giv_rotate, rotate);

        startColor = array.getColor(R.styleable.AndroidGradientImageViewAttrs_giv_startColor, startColor);
        endColor = array.getColor(R.styleable.AndroidGradientImageViewAttrs_giv_endColor, endColor);
        middleColor = array.getColor(R.styleable.AndroidGradientImageViewAttrs_giv_middleColor, middleColor);

        startOffset = array.getFloat(R.styleable.AndroidGradientImageViewAttrs_giv_startOffset, startOffset);
        endOffset = array.getFloat(R.styleable.AndroidGradientImageViewAttrs_giv_endOffset, endOffset);
        middleOffset = array.getFloat(R.styleable.AndroidGradientImageViewAttrs_giv_middleOffset, middleOffset);

        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float left = x * getWidth();
        float top = y * getHeight();

        float right = left + width * getWidth();
        float bottom = top + height * getHeight();

        int [] colors = null;
        float [] offsets = null;
        if(middleColor == -1) {
            colors = new int [] { startColor, endColor };
            offsets = new float[] { startOffset, endOffset };
        } else {
            colors = new int [] { startColor, middleColor, endColor };
            offsets = new float[] { startOffset, middleOffset, endOffset };
        }

        Shader gradient = new LinearGradient(
                left, top,
                right, bottom,
                colors,
                offsets,
                Shader.TileMode.CLAMP);

        Matrix m = new Matrix();
        m.setRotate(rotate, getWidth()/2, getHeight()/2);
        gradient.setLocalMatrix(m);

        Paint paint = new Paint();
        paint.setShader(gradient);
        canvas.drawRect(left, top, right, bottom, paint);
    }
}
