package com.bupt.telis.neteasecomment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * Created by Telis on 2015/6/10.
 */
public class CircleImageView extends ImageView {
    private int resId;
    private BitmapFactory.Options options;
    private int radius;
    private Paint maskPaint;
    private Paint srcPaint;
    private Canvas tmp;
    private SoftReference<Bitmap> src;
    //    private Bitmap srcBitmap;
    private Bitmap bitmap;
    private int length;

    public CircleImageView(Context context) {
        this(context, null);
    }


    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CircleImageView_background_src:
                    resId = a.getResourceId(attr, 0);
                    //                    options = new BitmapFactory.Options();
                    break;
            }
        }
        a.recycle();
        init();
    }


    private void init() {
        maskPaint = new Paint();
        maskPaint.setColor(Color.BLUE);
        maskPaint.setAntiAlias(true);
        srcPaint = new Paint();
        srcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (options == null) {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), resId, options);
            int height = options.outHeight;
            int width = options.outWidth;
            int min = Math.min(height, width);
            options.inSampleSize = min / length;
            options.inJustDecodeBounds = false;
        }
        if (src == null) {
            src = new SoftReference<>(BitmapFactory.decodeResource(getResources(), resId,
                    options));
        }
        if (src.get() == null) {
            src = new SoftReference<>(BitmapFactory.decodeResource(getResources(), resId,
                    options));
        }
        radius = length / 2;
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_4444);
        }
        tmp = new Canvas(bitmap);
        tmp.drawCircle(radius, radius, radius, maskPaint);
        tmp.drawBitmap(src.get(), radius - src.get().getWidth() / 2, radius -
                src.get()
                        .getHeight() / 2, srcPaint);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    public void setBackgroundResId(int backgroundResId) {
        this.resId = backgroundResId;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        length = Math.min(height, width);
        setMeasuredDimension(length, length);
    }

}
