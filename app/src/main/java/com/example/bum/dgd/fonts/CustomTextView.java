package com.example.bum.dgd.fonts;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.TextView;

import com.example.bum.dgd.R;

/**
 * Created by Bum on 17-Jul-17.
 */

public class CustomTextView extends TextView {
    private static final int REGULAR=0;
    private static final int BOLD=1;
    private static final int ITALIC=2;
    private static final int BOLD_ITALIC=3;

    private final static SparseArray<Typeface> mTypefaces = new SparseArray<>(16);
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.CustomEdittex);

        int typefaceValue = values.getInt(R.styleable.CustomEdittex_typeface,REGULAR );
        values.recycle();

        setTypeface(obtainTypeface(context, typefaceValue));
    }
    private Typeface obtainTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        Typeface typeface = mTypefaces.get(typefaceValue);
        if (typeface == null) {
            typeface = createTypeface(context, typefaceValue);
            mTypefaces.put(typefaceValue, typeface);
        }
        return typeface;
    }
    private Typeface createTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        Typeface typeface;
        switch (typefaceValue) {
            case BOLD:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/DroidSerif-Bold.ttf");
                break;
            case REGULAR:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/DroidSerif-Regular.ttf");
                break;
            case BOLD_ITALIC:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/DroidSerif-BoldItalic.ttf");
                break;
            case ITALIC:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/DroidSerif-Italic.ttf");
            default:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Lora-Regular.ttf");
                throw new IllegalArgumentException("Unknown `typeface` attribute value " + typefaceValue);
        }
        return typeface;
    }
}
